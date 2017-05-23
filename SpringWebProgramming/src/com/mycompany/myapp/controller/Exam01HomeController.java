package com.mycompany.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam01HomeController {
	
	//이 클래스라 로거임을 선언
	private static final Logger LOGGER= LoggerFactory.getLogger(Exam01HomeController.class);

	
	
	
	@RequestMapping("/")
	public String home() {
	
		LOGGER.info("/요청처리함");
		return "home";
	}

}
