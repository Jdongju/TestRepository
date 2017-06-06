package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl.class);
	
	Connection conn = null;
	
	// insert는 보통 Primary key
	@Override
	public int boardInsert(Exam12Board board) {
		int bno = -1;
		// Connection conn = null;
		try {
			// JDBC 드라이버 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결객체 얻기
			// DriverManger: 로딩된 드라이버 클래스를 관리해준다.
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "insert into board ";
			sql += "(bno, btitle, bcontent, bwriter, bdate,bpassword,bhitcount,boriginalfilename, bsavedfilename, bfilecontent)";
			sql += "values ";
			sql += "(board_bno_seq.nextval, ?, ?, ?,sysdate,?, 0, ?,?,?)";
			// SQL문을 전송해서 실행
			// 테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
			// PreparedStatement pstmt =
			// conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// 오라클일 경우 Sequence 외부객체로 자동증가값을 얻기 때문에 다음과 같이 지정.
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBwriter());
			pstmt.setString(4, board.getBpassword());
			pstmt.setString(5, board.getBoriginalfilename());
			pstmt.setString(6, board.getBsavedfilename());
			pstmt.setString(7, board.getBfilecontent());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			bno = rs.getInt(1);
			pstmt.close();
			LOGGER.info("행 추가성공");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bno;
	}
	
	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "select bno, btitle, bwriter, bdate, bhitcount "; // 마지막에
																			// 하나씩
																			// 띄어쓰기
																			// 해주어야
																			// 한다.
																			// from을
																			// 인식
																			// 하기
																			// 위해
			sql += "from board ";
			sql += "order by bno asc ";
			
			// SQL문을 전송해서 실행
			// 테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
			// PreparedStatement pstmt =
			// conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// 오라클일 경우 Sequence 외부객체로 자동증가값을 얻기 때문에 다음과 같이 지정.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(3));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				list.add(board);
			}
			rs.close();
			
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public int boardCountAll() {
		int count = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "select count(*) from board";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			ResultSet rs = pstmt.executeQuery();
			rs.next();// 일단 first로 이동
			count = rs.getInt(1); // 첫번째 bno리턴
			rs.close();
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Board> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "select * ";
			sql += "from ( ";
			sql += "select rownum as r, bno, btitle, bwriter, bdate, bhitcount ";
			sql += " from ( ";
			sql += " select bno, btitle, bwriter, bdate, bhitcount from board order by bno asc ";
			sql += ") ";
			sql += "where rownum<=? ";
			sql += ") ";
			sql += "where r>=? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo * rowsPerPage));
			pstmt.setInt(2, ((pageNo - 1) * rowsPerPage + 1));
			pstmt.executeQuery(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(4));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				list.add(board);
			}
			rs.close();
			
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public Exam12Board boardSelectByBno(int bno) {
		Exam12Board board = new Exam12Board();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "select * from board where bno=? "; // 마지막에 하나씩 띄어쓰기
																// 해주어야 한다.
																// from을 인식 하기
																// 위해
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
				
			}
			rs.close();
			
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
	}
	
	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		int count = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "update board set bhitcount=? where bno=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bhitcount); // sql문 첫번째에 들어갈 값
			pstmt.setInt(2, bno);// sql문 두번쨰에 들어갈 값.
			pstmt.executeUpdate(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void boardUpdate(Exam12Board board) {
		int count = 0;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql;
			if (board.getBoriginalfilename() != null) {
				sql = "update board set btitle=?,bcontent=?, bpassword=?,bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=? where bno=?";
			} else {
				sql = "update board set btitle=?,bcontent=?, bpassword=?, bdate=sysdate where bno=?";
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBtitle()); // sql문 첫번째에 들어갈 값
			pstmt.setString(2, board.getBcontent());// sql문 두번쨰에 들어갈 값.
			pstmt.setString(3, board.getBpassword());// sql문 두번쨰에 들어갈 값.
			if (board.getBoriginalfilename() != null) {
				pstmt.setString(4, board.getBoriginalfilename());
				pstmt.setString(5, board.getBsavedfilename());
				pstmt.setString(6, board.getBfilecontent());
				pstmt.setInt(7, board.getBno());
			} else {
				pstmt.setInt(4, board.getBno());
			}
			pstmt.executeUpdate(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void boardDelete(int bno) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql="delete from board where bno=?";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno); // sql문 첫번째에 들어갈 값
			
			pstmt.executeUpdate(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member member) {
		String mid = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "insert into member ";
			sql += "(mid, mname, mpassword, mdate, mtel,memail,mage,maddress, moriginalfilename, msavedfilename, mfilecontent) ";
			sql += "values ";
			sql += "(?, ?, ?,sysdate, ?,?,?,?,?,?,?)";
			// SQL문을 전송해서 실행
			// 테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
			// PreparedStatement pstmt =
			// conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// 오라클일 경우 Sequence 외부객체로 자동증가값을 얻기 때문에 다음과 같이 지정.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMpassword());
			pstmt.setString(4, member.getMtel());
			pstmt.setString(5, member.getMemail());
			pstmt.setInt(6, member.getMage());
			pstmt.setString(7, member.getMaddress());
			pstmt.setString(8, member.getMoriginalfilename());
			pstmt.setString(9, member.getMsavedfilename());
			pstmt.setString(10, member.getMfilecontent());
			pstmt.executeUpdate();
			pstmt.close();
			mid = member.getMid();
			LOGGER.info("행 추가성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mid;
	}
	
	@Override
	public List<Exam12Member> memberSelectAll() {
		List<Exam12Member> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");
			String sql = "select mid, mname, mpassword, mdate, mtel, memail, mage, maddress ";
			sql += "from member ";
			sql += "order by mid desc ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMdate(rs.getDate("mdate"));
				member.setMtel(rs.getString("mtel"));
				member.setMemail(rs.getString("memail"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				list.add(member);
			}
			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public int memberCountAll() {
		int count = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");
			String sql = "select count(*) from member";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LOGGER.info(String.valueOf(count));
		return count;
	}
	
	@Override // 몇행부터 몇행까지 실행할 것인가?
	public List<Exam12Member> memberSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");
			String sql = "select * ";
			sql += "from ( ";
			sql += "select rownum as r, mid, mname, mpassword, mdate, mtel, memail, mage, maddress ";
			sql += "from ( ";
			sql += "select mid, mname, mpassword, mdate, mtel, memail, mage, maddress from member order by mid desc";
			sql += ") ";
			sql += "where rownum<=?";
			sql += ") ";
			sql += "where r>=? ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo * rowsPerPage));
			pstmt.setInt(2, ((pageNo - 1) * rowsPerPage + 1));
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMdate(rs.getDate("mdate"));
				member.setMtel(rs.getString("mtel"));
				member.setMemail(rs.getString("memail"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				list.add(member);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	@Override
	public Exam12Member memberSelectByMid(String mid) {
		Exam12Member member= new Exam12Member();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "select * from member where mid=? "; // 마지막에 하나씩 띄어쓰기
																// 해주어야 한다.
																// from을 인식 하기
																// 위해
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMdate(rs.getDate("mdate"));
				member.setMtel(rs.getString("mtel"));
				member.setMemail(rs.getString("memail"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				member.setMoriginalfilename(rs.getString("moriginalfilename"));
				member.setMsavedfilename(rs.getString("msavedfilename"));
				member.setMfilecontent(rs.getString("mfilecontent"));
				
			}
			rs.close();
			
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	
	public static void main(String[] args) {
		Exam12DaoImpl test = new Exam12DaoImpl();

		for(int i=1; i<=100; i++){
		  Exam12Board board= new Exam12Board();
		  board.setBtitle("제목"+i);
		  board.setBcontent("내용" +i);
		  board.setBwriter("홍길동");
		  board.setBpassword("12345");
		  board.setBoriginalfilename("member"+i+".png");
		  board.setBsavedfilename("a44555.png");
		  board.setBfilecontent("image/png");
		  int bno=test.boardInsert(board);
		  LOGGER.info("추가된 행의 bno: "+bno);
	  }
		/*for (int i = 1; i <= 100; i++) {
			Exam12Member member = new Exam12Member();
			member.setMid("Id" + i);
			member.setMname("홍길동");
			member.setMpassword("12345");
			member.setMtel("010-548-6678");
			member.setMemail("dj9110@naver.com");
			member.setMage(i);
			member.setMaddress("샤울");
			member.setMoriginalfilename("a.png");
			member.setMsavedfilename("a123123.png");
			member.setMfilecontent("image/png");
			String mid = test.memberInsert(member);
			LOGGER.info("추가된 행의 mid: " + mid);
		}*/
		/*
		 * List<Exam12Board> list= test.boardSelectPage(2, 10);
		 * for(Exam12Board board : list){
		 * LOGGER.info(board.getBtitle());
		 * }
		 */
		// List<Exam12Member> list= test.memberSelectPage(2, 10);
		// for(Exam12Member member: list){
		// LOGGER.info(member.getm);
		// }
		
	}

	@Override
	public void memberUpdate(Exam12Member member) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql;
			if (member.getMoriginalfilename() != null) {
				sql = "update member set mid=?, mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=?,  moriginalfilename=?, msavedfilename=?, mfilecontent=? where mid=?";
			} else {
				sql = "update member set mid=?, mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=? where mid=?";
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid()); // sql문 첫번째에 들어갈 값
			pstmt.setString(2, member.getMname());// sql문 두번쨰에 들어갈 값.
			pstmt.setString(3, member.getMpassword());// sql문 두번쨰에 들어갈 값.
			pstmt.setString(4, member.getMtel());
			pstmt.setString(5, member.getMemail());
			pstmt.setInt(6, member.getMage());
			pstmt.setString(7, member.getMaddress());
			if (member.getMoriginalfilename() != null) {
				pstmt.setString(8, member.getMoriginalfilename());
				pstmt.setString(9, member.getMsavedfilename());
				pstmt.setString(10, member.getMfilecontent());
				pstmt.setString(11, member.getMid());
			} else {
				pstmt.setString(8, member.getMid());
			}
			pstmt.executeUpdate(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void memberDelete(String mid) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql="delete from member where mid=?";
			LOGGER.info("삭제SQL");
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid); // sql문 첫번째에 들어갈 값
			LOGGER.info("setSTring");
			pstmt.executeUpdate(); // Select를 실행할때는 executeUpdate(행추가)가 아니라
									// executeQuery
			LOGGER.info("executeUpdate");
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결끊기
			try {
				conn.close();
				LOGGER.info("연결 끊김");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	


	
	
}
