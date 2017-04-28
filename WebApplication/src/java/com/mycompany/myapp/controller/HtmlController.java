package com.mycompany.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

	@RequestMapping("/html/exam01") //context까지 생략되어있음 http://localhost:8080/WebApplication
	public String html() {
		return "html/exam01";	// /WEB-INF/views/html.jsp라는 의미
	}

}
