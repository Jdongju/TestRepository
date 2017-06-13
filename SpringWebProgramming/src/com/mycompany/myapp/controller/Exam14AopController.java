package com.mycompany.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam14AopController {
	
	//로그인이 없어도되는 메소드
	@RequestMapping("/aop/exam01")
	public String exam01(HttpSession session){
		session.setAttribute("mid", "xxxx");
		return "aop/exam01";
	}
	
	//로그인이 있어야하는 메소드
	@RequestMapping("/aop/exam02Write")
	public String exam02Write(HttpSession session){
		session.setAttribute("mid", "xxxx");
		return "aop/exam02";
	}
	
	@RequestMapping("/aop/exam02Update")
	public String exam02Update(){
		return "aop/exam02";
	}
}
