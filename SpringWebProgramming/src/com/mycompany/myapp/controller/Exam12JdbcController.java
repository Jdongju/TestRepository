package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.service.Exam12Service;

@Controller
public class Exam12JdbcController {
	private static final Logger LOGGER= LoggerFactory.getLogger(Exam12JdbcController.class);
	
	@Autowired
	private Exam12Service service;
	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping("/jdbc/exam01")
	public String exam01(){
		Exam12Board board = new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("홍길동");
		board.setBpassword("12345");
		board.setBoriginalfilename("a.png");
		board.setBsavedfilename("a.png");
		board.setBfilecontent("image/png");
		
		service.boardWrite(board);
		return "redirect:/";
	}
	
	@RequestMapping(value="/jdbc/exam02", method=RequestMethod.GET)
	public String exam02Get(){
		return "jdbc/exam02";
	}
	@RequestMapping(value="/jdbc/exam02", method=RequestMethod.POST)
	public String exam02Post(Exam12Board board) throws IllegalStateException, IOException{
		//첨부파일에 대한 정보를 컬럼값으로 설정
		board.setBoriginalfilename(board.getBattach().getOriginalFilename());
		board.setBfilecontent(board.getBattach().getContentType());
		String fileName=new Date().getTime()+"-"+board.getBoriginalfilename();
		board.setBsavedfilename(fileName);
		//첨부파일을 서버 로컬 시스템에 저장
		String realPath=servletContext.getRealPath("/WEB-INF/upload/");
		File file= new File(realPath+fileName);
		board.getBattach().transferTo(file);
		
		
//		서비스 객체에 요청 처리 요청
		service.boardWrite(board);
		
		LOGGER.info(realPath);
		
		return "redirect:/";
	}
	@RequestMapping(value="/jdbc/exam03", method=RequestMethod.GET)
	public String exam03Get(){
		return "jdbc/exam03";
	}
	
	
	@RequestMapping(value="/jdbc/exam03", method=RequestMethod.POST)
	public String exam03Post(Exam12Member member) throws IllegalStateException, IOException{
		member.setMoriginalfilename(member.getMattach().getOriginalFilename());
		member.setMfilecontent(member.getMattach().getContentType());
		String fileName= new Date().getTime()+"-"+member.getMoriginalfilename();
		member.setMsavedfilename(fileName);
		String realPath=servletContext.getRealPath("/WEB-INF/upload/");
		File file= new File(realPath+fileName);
		member.getMattach().transferTo(file);
		
		service.memberJoin(member);
		
		return "redirect:/";
	}
	
	@RequestMapping("/jdbc/exam04")
	public String exam04(Model model){
		List<Exam12Board> list= service.boardListAll();
		model.addAttribute("list", list); //객체 전달.
		return "jdbc/exam04";
	}
	
	@RequestMapping("/jdbc/exam05")  //넘어오는 값이 없을 경우 기본값을 1로한다.
	public String exam05(@RequestParam(defaultValue="1")int pageNo, Model model){
		//한 페이지를 구성하는 행 수
		int rowsPerPage=10;
		//한 그룹을 구성하는 페이지 수
		int pagesPerGroup=7;
		//총 행수
//		int totalRows=100;
		int totalRows=service.boardTotalRows();
		//전체 페이지수
		int totalPageNo=(totalRows/rowsPerPage)+((totalRows%rowsPerPage!=0)?1:0);
		//전체 그룹수
		int totalGroupNo=(totalPageNo/pagesPerGroup)+ ((totalPageNo%pagesPerGroup!=0)?1:0);
		//현재그룹번호
		int groupNo=(pageNo-1)/pagesPerGroup+1;
		//현재 그룹의 시작 페이지 번호
		int startPageNo=(groupNo-1)*pagesPerGroup+1;
		//현재 그룹의 마지막 페이지 번호
		int endPageNo=startPageNo+pagesPerGroup-1;
		if(groupNo==totalGroupNo){
			endPageNo=totalPageNo;
		}
		//현재 페이지의 행의 데이터 가져오기
		List<Exam12Board> list= service.boardListPage(pageNo, rowsPerPage);
		
		//view(jsp)로 넘겨줄 데이터
		model.addAttribute("list", list); //객체 전달.
		model.addAttribute("pagesPerGroup",pagesPerGroup);
		model.addAttribute("totalPageNo",totalPageNo);
		model.addAttribute("totalGroupNo",totalGroupNo);
		model.addAttribute("groupNo",groupNo);
		model.addAttribute("startPageNo",startPageNo);
		model.addAttribute("endPageNo",endPageNo);
		model.addAttribute("pageNo",pageNo);
		
		
		//view 이름 리턴
		return "jdbc/exam05";
	}
	
	@RequestMapping("/jdbc/exam06")
	public String exam06(@RequestParam(defaultValue="1")int pageNo, Model model){
		int rowsPerPage=10;
		int pagesPerGroup=5;
		int totalRows=service.memberTotalRows();
		int totalPageNo=(totalRows/rowsPerPage)+((totalRows%rowsPerPage!=0)?1:0);
		int totalGroupNo=(totalPageNo/pagesPerGroup)+((totalPageNo%pagesPerGroup!=0)?1:0);
		int groupNo=(pageNo-1)/pagesPerGroup+1;
		int startPageNo=(groupNo-1)*pagesPerGroup+1;
		int endPageNo=startPageNo+pagesPerGroup-1;
		if(groupNo==totalGroupNo){
			endPageNo=totalPageNo;
		}
		
		List<Exam12Member> list=service.memberListPage(pageNo, rowsPerPage);
		
		model.addAttribute("list",list);
		model.addAttribute("pagesPerGroup",pagesPerGroup);
		model.addAttribute("totalpageNo",totalPageNo);
		model.addAttribute("totalGroupNo",totalGroupNo);
		model.addAttribute("groupNo",groupNo);
		model.addAttribute("startPageNo",startPageNo);
		model.addAttribute("endPageNo",endPageNo);
		model.addAttribute("pageNo",pageNo);
		
		return "jdbc/exam06";
		
	}
}
