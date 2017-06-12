package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
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
public class Exam12DaoImpl3 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl3.class);
	
	// DataSource가 아니기 때문에 main에서 행추가 불가능
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	Connection conn = null;
	
	// insert는 보통 Primary key
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int boardInsert(Exam12Board board) {
	
		int rows= sqlSessionTemplate.insert("board.insert", board);
		/*board는 board.xml의 namesapce, insert는 id, parameter는 board(dto)*/
		return board.getBno();
	}
	
	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list=sqlSessionTemplate.selectList("board.selectAll");
		return list;
	}
	
	@Override
	public int boardCountAll() {
		int count=sqlSessionTemplate.selectOne("board.countAll");
		return count;
	}
	
	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", (pageNo-1)*rowsPerPage+1);
		map.put("endNum", (pageNo * rowsPerPage));
		List<Exam12Board> list= sqlSessionTemplate.selectList("board.selectByPage", map);
		return list;
	}
	
	@Override
	public Exam12Board boardSelectByBno(int bno) {
		Exam12Board board= sqlSessionTemplate.selectOne("board.selectByBno", bno);
		return board;
	}
	
	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("bhitcount", bhitcount);
		map.put("bno", bno);
		sqlSessionTemplate.update("board.updateBhitcount", map);
	}
	
	@Override
	public void boardUpdate(Exam12Board board) {
		sqlSessionTemplate.update("board.update",board);
	}
	
	@Override
	public void boardDelete(int bno) {
		sqlSessionTemplate.delete("board.delete",bno);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member member) {
		sqlSessionTemplate.insert("member.insert", member);
		return member.getMid();
	}
	
	@Override
	public List<Exam12Member> memberSelectAll() {
		List<Exam12Member> list= sqlSessionTemplate.selectList("member.selectAll");
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
		Exam12DaoImpl3 test = new Exam12DaoImpl3();
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
