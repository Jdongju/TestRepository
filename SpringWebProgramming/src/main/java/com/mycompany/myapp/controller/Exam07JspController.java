package com.mycompany.myapp.controller;

import com.mycompany.myapp.dto.Exam07Board;
import com.mycompany.myapp.dto.Exam07Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class Exam07JspController {

	@RequestMapping("/exam01")
	public String exam01() {
		return "jsp/exam01";
	}

	@RequestMapping("/exam02")
	public String exam02() {
		return "jsp/exam02";
	}

	@RequestMapping("/exam03")
	public String exam03() {
		return "jsp/exam03";
	}

	@RequestMapping("/exam04")
	public String exam04(HttpServletRequest request) {
		request.setAttribute("name2", "홍길동"); //이 데이터를 JSP에 전달하고 싶다.
		// request라는 객체는 톰캣이 만들어서준다.
		//jsp가 request를 받으면 EL을 이용해서 데이터를 얻는다.
		//request
		request.setAttribute("member2", new Exam07Member("홍길동", 30));
		return "jsp/exam04";
	}

	//Spring에서 데이터전달로 많이 쓰는 방법은 5번이다.
	@RequestMapping("/exam05")
	public String exam05(Model model) {	//요청매핑메소드. 
//		model을 매개변수로 하는 이유는 컨트롤러에서 데이터를 JSP에 전달하기 위해서.
//request대신 model을 사용하는것.
		model.addAttribute("name3", "홍길동");
		model.addAttribute("member3", new Exam07Member("홍길동", 30));

		Exam07Board board = new Exam07Board();
		board.setBno(1);
		board.setBtitle("오늘은 휴가 전날");
		board.setBcontent("휴가떄 과제할 내용을 준비해야합니다.");
		board.setBwriter("감자바");
		board.setBdate(new Date());

		model.addAttribute("board", board);

		List<Exam07Board> list = new ArrayList<Exam07Board>();
		for (int i = 1; i <= 10; i++) {
			Exam07Board b = new Exam07Board();
			b.setBno(i);
			b.setBtitle("제목"+i);
			b.setBcontent("내용입니다. 장비가 와야할텐데..."+i);
			b.setBwriter("글쓴이"+i);
			b.setBdate(new Date());
			list.add(b);
		}
		model.addAttribute("list", list);
		return "jsp/exam05";
	}
}
