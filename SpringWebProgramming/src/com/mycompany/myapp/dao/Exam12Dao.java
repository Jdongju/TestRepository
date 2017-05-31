package com.mycompany.myapp.dao;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Dao {
	public int boardInsert(Exam12Board board);
	public List<Exam12Board>boardSelectAll();
	public List<Exam12Board>boardSelectPage(int pageNo, int rowsPerPage);
	public int boardCountAll();
	////////////////////////////////////////////////////////////////////////////////////////////
	
	public String memberInsert(Exam12Member member);
	public List<Exam12Member>memberSelectAll();
	public int memberCountAll();
	public List<Exam12Member>memberSelectPage(int pageNo, int rowsPerPage);
}
