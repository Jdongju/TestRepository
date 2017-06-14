package com.mycompany.myapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//스프링에서 트랜잭션을 처리하기 위해서는 발생되는 예외가 반드시 RuntimeException만 가능
@Component
@ControllerAdvice // 컨트롤러마다 공통으로 발생하는 예외를 처리하는 객체라는 뜻

public class NoAccountExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoAccountExceptionHandler.class);
	
	// 메시지 출력 필요없는 경우
	@ExceptionHandler
	public String handleNoAccountException(NoAccountException e, Model model) {
		LOGGER.info("실행");
		model.addAttribute("reason", e.getMessage());
		return "transaction/exam02";
	}
}
