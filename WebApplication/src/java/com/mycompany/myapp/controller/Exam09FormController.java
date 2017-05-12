package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class Exam09FormController {
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value="/form/exam01", method=RequestMethod.GET)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드
	public String joinForm(){
		return "form/exam01";
	}
	@RequestMapping(value="/form/exam01", method=RequestMethod.POST)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드 @RequestParam은 생략
	public String join(String mid, String mname, String mpassword,
					@RequestParam(defaultValue = "0") int mage, 	//요청파라미터는 전부 문자이기 때문에 디폴트를 줄시 0으로 주어야한다.
					String[] mskill, String mbirth){
		System.out.println("mid " +mid);
		System.out.println("mname " +mname);
		System.out.println("mpassword " +mpassword);
		System.out.println("mage " +mage);
		System.out.println("mskill " +Arrays.toString(mskill));
		System.out.println("mbirth " +mbirth);
		return "form/exam01";
	}
	
	@RequestMapping(value="/form/exam02", method=RequestMethod.GET)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드
	public String joinForm2(){
		return "form/exam02";
	}
	@RequestMapping(value="/form/exam02", method=RequestMethod.POST)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드
	public String join2(String mid, String mname, String mpassword,
					@RequestParam(defaultValue = "0") int mage, 	//요청파라미터는 전부 문자이기 때문에 디폴트를 줄시 0으로 주어야한다.
					String[] mskill, String mbirth, MultipartFile attach){
		
		//파일의 정보 얻기
		String fileName=attach.getOriginalFilename();
		String contentType=attach.getContentType();
		long fileSize=attach.getSize();
		
		//파일을 서버 하드디스크에 저장
		String realPath=servletContext.getRealPath("/WEB-INF/upload/"+fileName);
		File file = new File(realPath);
		try {
			attach.transferTo(file);
		} catch (IOException ex) {
			Logger.getLogger(Exam09FormController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalStateException ex) {
			Logger.getLogger(Exam09FormController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		System.out.println("fileName "+ fileName);
		System.out.println("contentType "+ contentType);
		System.out.println("fileSize "+ fileSize);
		return "form/exam02";
	}
	
	
}
