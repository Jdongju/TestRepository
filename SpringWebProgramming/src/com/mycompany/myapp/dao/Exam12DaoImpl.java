package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl.class);
	
	Connection conn= null;
	//insert는 보통 Primary jl
	@Override
	public int insert1(Exam12Board board) {
		int bno=-1;
//		Connection conn = null;
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
			//테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
//			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// 오라클일 경우 Sequence 외부객체로 자동증가값을 얻기 때문에 다음과 같이 지정.
			PreparedStatement pstmt=conn.prepareStatement(sql, new String[]{"bno"});
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBwriter());
			pstmt.setString(4, board.getBpassword());
			pstmt.setString(5, board.getBoriginalfilename());
			pstmt.setString(6, board.getBsavedfilename());
			pstmt.setString(7, board.getBfilecontent());
			pstmt.executeUpdate();
			ResultSet rs= pstmt.getGeneratedKeys();
			rs.next();
			bno=rs.getInt(1);
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
	public String insert2(Exam12Member member) {
		String mid=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			conn=DriverManager.getConnection(url,"iotuser", "iot12345");
			LOGGER.info("연결성공");
			// SQL 작성
			String sql = "insert into member ";
			sql += "(mid, mname, mpassword, mdate, mtel,memail,mage,maddress, moriginalfilename, msavedfilename, mfilecontent) ";
			sql += "values ";
			sql += "(?, ?, ?,sysdate, ?,?,?,?,?,?,?)";
			// SQL문을 전송해서 실행
			//테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
//			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// 오라클일 경우 Sequence 외부객체로 자동증가값을 얻기 때문에 다음과 같이 지정.
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMpassword());
			pstmt.setString(4, member.getMtel());
			pstmt.setString(5,member.getMemail());
			pstmt.setInt(6, member.getMage());
			pstmt.setString(7, member.getMaddress());
			pstmt.setString(8,  member.getMoriginalfilename());
			pstmt.setString(9,  member.getMsavedfilename());
			pstmt.setString(10,  member.getMfilecontent());
			pstmt.executeUpdate();
			pstmt.close();
			mid=member.getMid();
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
	public static void main(String[] args) {
		Exam12DaoImpl test = new Exam12DaoImpl();
		Exam12Member member = new Exam12Member();
	
//		LOGGER.info("추가된 행위 bon: "+bno);
	}

	
	
}
