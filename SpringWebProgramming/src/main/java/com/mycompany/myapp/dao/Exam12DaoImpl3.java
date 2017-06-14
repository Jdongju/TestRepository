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
		int count=sqlSessionTemplate.selectOne("member.countAll");
		return count;
	}
	
	@Override // 몇행부터 몇행까지 실행할 것인가?
	public List<Exam12Member> memberSelectPage(int pageNo, int rowsPerPage) {
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("startNum", (pageNo-1)*rowsPerPage+1);
		map.put("endNum", (pageNo * rowsPerPage));
		List<Exam12Member> list= sqlSessionTemplate.selectList("member.selectByPage", map);
		return list;
	}
	
	@Override
	public Exam12Member memberSelectByMid(String mid) {
		Exam12Member member=sqlSessionTemplate.selectOne("selectByMid", mid);
		
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
		sqlSessionTemplate.update("member.update",member);
	
	}
	
	@Override
	public void memberDelete(String mid) {
		sqlSessionTemplate.delete("member.delete",mid);
	}
}
