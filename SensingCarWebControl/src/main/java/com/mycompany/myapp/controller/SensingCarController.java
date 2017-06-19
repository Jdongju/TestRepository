package com.mycompany.myapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.myapp.service.SensingCarService;

import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SensingCarController {
	
	private static final Logger logger = LoggerFactory.getLogger(SensingCarController.class);
	
	@Autowired
	private SensingCarService sensingCarService;
	
	//AJAX에서 호출하기 때문에 JSON응답을 준다.
	@RequestMapping("/ultrasonicsensor/")
	public void ultrasonicsensor(
			@RequestParam(defaultValue="90") int angle,
			HttpServletResponse response) throws Exception{
//		sensingCarService.changeUltrasonicSensorAngle(angle);
//		Thread.sleep(500);
//		int distance= sensingCarService.getUltrasonicSensorDistance();
		int distance=100;
		
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("distance", distance);
		String json= jsonObject.toString();
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter printWriter= response.getWriter();
		printWriter.write(json);
		printWriter.flush();
		printWriter.close();
	}
	
}
