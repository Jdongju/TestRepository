package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl2 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl2.class);
	
	// DataSource가 아니기 때문에 main에서 행추가 불가능
	@Autowired
	private JdbcTemplate jdbcTemplate;
	Connection conn = null;
	
	// insert는 보통 Primary key
	@Override
	public int boardInsert(Exam12Board board) {
		
		final String sql = "insert into board " +
				"(bno, btitle, bcontent, bwriter, bdate,bpassword,bhitcount,boriginalfilename, bsavedfilename, bfilecontent)"
				+
				"values " +
				"(board_bno_seq.nextval, ?, ?, ?,sysdate,?, 0, ?,?,?)";
		/*
		 * jdbcTemplate.update(sql,
		 * board.getBtitle(), board.getBcontent(), board.getBwriter(),
		 * board.getBpassword(),
		 * board.getBoriginalfilename(),
		 * board.getBsavedfilename(),board.getBfilecontent());
		 */
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });
				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.setString(4, board.getBpassword());
				pstmt.setString(5, board.getBoriginalfilename());
				pstmt.setString(6, board.getBsavedfilename());
				pstmt.setString(7, board.getBfilecontent());
				return pstmt;
			}
		};
		
		// 키값을 저장하는 객체
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// update시 psc의 내용대로 실행하고 키값 bno를 key holder에 저장한다.
		jdbcTemplate.update(psc, keyHolder);
		int bno = keyHolder.getKey().intValue();
		LOGGER.info(String.valueOf(bno));
		
		return bno;
	}
	
	@Override
	public List<Exam12Board> boardSelectAll() {
		/*마지막에하나씩띄어쓰기해주어야한다.from을인식하기위해*/
		String sql = "select bno, btitle, bwriter, bdate, bhitcount "; 
		sql += "from board ";
		sql += "order by bno asc ";
		
		/*
		 * SQL문을 전송해서 실행
		 * 테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
		 * PreparedStatement pstmt
		 * =conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		 * 오라클일 경우 Sequence 외부객체로 자동증가값을 얻기 때문에 다음과 같이 지정.
		 */
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		
		List<Exam12Board> list = jdbcTemplate.query(sql, rowMapper);
		return list;
	}
	
	@Override
	public int boardCountAll() {
		String sql = "select count(*) from board";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}
	
	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		String sql = "select * ";
		sql += "from ( ";
		sql += "select rownum as r, bno, btitle, bwriter, bdate, bhitcount ";
		sql += " from ( ";
		sql += " select bno, btitle, bwriter, bdate, bhitcount from board order by bno asc ";
		sql += ") ";
		sql += "where rownum<=? ";
		sql += ") ";
		sql += "where r>=? ";
		
		// 위 sql문에서 ?에 들어갈 값을 args에서 입력
		Object args[] = { (pageNo * rowsPerPage), ((pageNo - 1) * rowsPerPage + 1) };
		
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		
		List<Exam12Board> list = jdbcTemplate.query(sql, args, rowMapper);
		
		return list;
	}
	
	@Override
	public Exam12Board boardSelectByBno(int bno) {
		/* 마지막에 하나씩 띄어쓰기 해주어야 한다. from을 인식 하기위해 */
		String sql = "select * from board where bno=? ";
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
				return board;
			}
		};
		Exam12Board board = jdbcTemplate.queryForObject(sql, rowMapper, bno);
		return board;
	}
	
	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		String sql = "update board set bhitcount=? where bno=?";
		jdbcTemplate.update(sql, bhitcount, bno);
	}
	
	@Override
	public void boardUpdate(Exam12Board board) {
		String sql;
		if (board.getBoriginalfilename() != null) {
			sql = "update board set btitle=?,bcontent=?, bpassword=?,bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=? where bno=?";
			int updatedRows = jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(),
					board.getBoriginalfilename(), board.getBsavedfilename(), board.getBfilecontent(), board.getBno());
		} else {
			sql = "update board set btitle=?,bcontent=?, bpassword=?, bdate=sysdate where bno=?";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(), board.getBno());
		}
		
	}
	
	@Override
	public void boardDelete(int bno) {
		String sql = "delete from board where bno=?";
		// 몇개 지워졌는지 리턴
		int deletedRows = jdbcTemplate.update(sql, bno);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member member) {
		
		// 시퀀스를 안써서 mid를 알고있기 때문에 키홀더 필요없음
		String sql = "insert into member ";
		sql += "(mid, mname, mpassword, mdate, mtel,memail,mage,maddress, moriginalfilename, msavedfilename, mfilecontent) ";
		sql += "values ";
		sql += "(?, ?, ?,sysdate, ?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(
				sql,
				member.getMid(), member.getMname(), member.getMpassword(), member.getMtel(),
				member.getMemail(), member.getMage(), member.getMaddress(),
				member.getMoriginalfilename(), member.getMsavedfilename(), member.getMfilecontent());
		
		return member.getMid();
	}
	
	@Override
	public List<Exam12Member> memberSelectAll() {
		String sql = "select mid, mname, mpassword, mdate, mtel, memail, mage, maddress ";
		sql += "from member ";
		sql += "order by mid desc ";
		
		RowMapper<Exam12Member> rowMapper = new RowMapper<Exam12Member>() {
			
			// 행의 갯수만큼 리스트에 추가.
			@Override
			public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMdate(rs.getDate("mdate"));
				member.setMtel(rs.getString("mtel"));
				member.setMemail(rs.getString("memail"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				return member;
			}
		};
		List<Exam12Member> list = jdbcTemplate.query(sql, rowMapper);
		
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
		String sql = "select * ";
		sql += "from ( ";
		sql += "select rownum as r, mid, mname, mpassword, mdate, mtel, memail, mage, maddress ";
		sql += "from ( ";
		sql += "select mid, mname, mpassword, mdate, mtel, memail, mage, maddress from member order by mid desc";
		sql += ") ";
		sql += "where rownum<=?";
		sql += ") ";
		sql += "where r>=? ";
		
		Object args[] = { (pageNo * rowsPerPage), ((pageNo - 1) * rowsPerPage + 1) };
		RowMapper<Exam12Member> rowMapper = new RowMapper<Exam12Member>() {
			
			@Override
			//rowNum은 현재까지 추가한 행의 숫자를 리턴하기 위해 사용된다.
			public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMdate(rs.getDate("mdate"));
				member.setMtel(rs.getString("mtel"));
				member.setMemail(rs.getString("memail"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				return member;
			}
		};
		List<Exam12Member> list = jdbcTemplate.query(sql, args, rowMapper);
		
		return list;
	}
	
	@Override
	public Exam12Member memberSelectByMid(String mid) {
			String sql = "select * from member where mid=? ";  
			RowMapper<Exam12Member> rowMapper= new RowMapper<Exam12Member>() {
				@Override
				public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Member member = new Exam12Member();
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
					return member;
				}
			};
			
			Exam12Member member= jdbcTemplate.queryForObject(sql, rowMapper, mid);
		return member;
	}
	
	public static void main(String[] args) {
		Exam12DaoImpl2 test = new Exam12DaoImpl2();
		Exam12Member member = new Exam12Member();
		member.setMid("Id" + 159);
		member.setMname("홍길동");
		member.setMpassword("12345");
		member.setMtel("010-548-6678");
		member.setMemail("dj9110@naver.com");
		member.setMage(158);
		member.setMaddress("샤울");
		member.setMoriginalfilename("a.png");
		member.setMsavedfilename("a123123.png");
		member.setMfilecontent("image/png");
		String mid = test.memberInsert(member);
		LOGGER.info("추가된 행의 mid: " + mid);
		/*for (int i = 1; i <= 100; i++) {
			Exam12Board board = new Exam12Board();
			board.setBtitle("제목" + i);
			board.setBcontent("내용" + i);
			board.setBwriter("홍길동");
			board.setBpassword("12345");
			board.setBoriginalfilename("member" + i + ".png");
			board.setBsavedfilename("a44555.png");
			board.setBfilecontent("image/png");
			int bno = test.boardInsert(board);
			LOGGER.info("추가된 행의 bno: " + bno);
		}*/
		/*
		 * for (int i = 1; i <= 100; i++) {
		 * Exam12Member member = new Exam12Member();
		 * member.setMid("Id" + i);
		 * member.setMname("홍길동");
		 * member.setMpassword("12345");
		 * member.setMtel("010-548-6678");
		 * member.setMemail("dj9110@naver.com");
		 * member.setMage(i);
		 * member.setMaddress("샤울");
		 * member.setMoriginalfilename("a.png");
		 * member.setMsavedfilename("a123123.png");
		 * member.setMfilecontent("image/png");
		 * String mid = test.memberInsert(member);
		 * LOGGER.info("추가된 행의 mid: " + mid);
		 * }
		 */
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
			String sql;
			if (member.getMoriginalfilename() != null) {
				sql = "update member set mid=?, mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=?,  moriginalfilename=?, msavedfilename=?, mfilecontent=? where mid=?";
				int updatedRows=jdbcTemplate.update(sql, member.getMid(),member.getMpassword(), member.getMtel(), member.getMemail(), member.getMage(), member.getMaddress(), member.getMoriginalfilename(), member.getMsavedfilename(), member.getMfilecontent(), member.getMid());
			} else {
				sql = "update member set mid=?, mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=? where mid=?";
				jdbcTemplate.update(sql, member.getMid(),member.getMpassword(), member.getMtel(), member.getMemail(), member.getMage(), member.getMaddress(), member.getMid());
			}
	}
	
	@Override
	public void memberDelete(String mid) {
			String sql = "delete from member where mid=?";
			int deletedRows=jdbcTemplate.update(sql, mid);
	}
}
