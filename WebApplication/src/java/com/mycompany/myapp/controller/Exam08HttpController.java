package com.mycompany.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/http")
public class Exam08HttpController {

	@RequestMapping(value = "/exam01", method = RequestMethod.GET)
	public String exam01Get() {
		System.out.println("exam01()Get.....");
		return "http/exam01";
	}

	@RequestMapping(value = "/exam01", method = RequestMethod.POST)
	public String exam01POST() {
		System.out.println("exam01()Post.....");
		return "http/exam01";
	}
	//2번보다는 3번이 중요
	@RequestMapping("/exam02")
	public String exam02(HttpServletRequest request, Model model) {
		//request는 요청 http의 정보를 갖고 있다.
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		String type = request.getParameter("type");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String[] hobby = request.getParameterValues("hobby");

		String userAgent = request.getHeader("User-Agent");
		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			userAgent = "IE11 이하 브라우저";
		} else if (userAgent.contains("Edge")) {
			userAgent = "엣지 브라우저";
		} else if (userAgent.contains("Chrome")) {
			userAgent = "크롬 브라우저";
		}
		model.addAttribute("method", method);
		model.addAttribute("uri", uri);
		model.addAttribute("queryString", queryString); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에서 $()로 찾는다.
		model.addAttribute("type", type); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에서 $()로 찾는다.
		model.addAttribute("bno", bno); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에서 $()로 찾는다.
		model.addAttribute("userAgent", userAgent); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에서 $()로 찾는다.
		model.addAttribute("hobby", hobby); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에서 $()로 찾는다.
		return "http/exam02";
	}

	@RequestMapping("/exam03")
	public String exam03(
					@RequestParam String type,
					@RequestParam int bno,
					String[] hobby,//home.jsp의 hobby와 일치하면 baeball이란 값을 hobby에 넣어준다.
					@RequestHeader ("User-Agent") String userAgent,
					Model model) {
		model.addAttribute("type", type);// 데이터를 진짜로 넘겨줄때는 Model View Cotroller 중 Model로 넘겨준다.
		model.addAttribute("bno", bno); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에서 $()로 찾는다.
		model.addAttribute("userAgent", userAgent); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에서 $()로 찾는다.
		model.addAttribute("hobby", hobby); //앞의것은 키, 뒤의것은 속성 키를 바탕으로 jsp에
		return "http/exam03";
	}
}
