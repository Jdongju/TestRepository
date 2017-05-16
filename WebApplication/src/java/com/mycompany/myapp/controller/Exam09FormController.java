package com.mycompany.myapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Exam09FormController {

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/form/exam01", method = RequestMethod.GET)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드
	public String joinForm() {
		return "form/exam01";
	}

	@RequestMapping(value = "/form/exam01", method = RequestMethod.POST)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드 @RequestParam은 생략
	public String join(String mid, String mname, String mpassword,
					@RequestParam(defaultValue = "0") int mage, //요청파라미터는 전부 문자이기 때문에 디폴트를 줄시 0으로 주어야한다.
					String[] mskill, String mbirth) {
		System.out.println("mid " + mid);
		System.out.println("mname " + mname);
		System.out.println("mpassword " + mpassword);
		System.out.println("mage " + mage);
		System.out.println("mskill " + Arrays.toString(mskill));
		System.out.println("mbirth " + mbirth);
		return "form/exam01";
	}

	@RequestMapping(value = "/form/exam02", method = RequestMethod.GET)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드
	public String joinForm2() {
		return "form/exam02";
	}

	@RequestMapping(value = "/form/exam02", method = RequestMethod.POST)  // /form/exam01은 value 속성, 뒤의것은  요청방식 메소드
	public String join2(String mid, String mname, String mpassword,
					@RequestParam(defaultValue = "0") int mage, //요청파라미터는 전부 문자이기 때문에 숫자에 디폴트를 줄시 0으로 주어야한다.
					String[] mskill, String mbirth, MultipartFile attach) {

		//파일의 정보 얻기
		String fileName = attach.getOriginalFilename();
		String contentType = attach.getContentType();
		long fileSize = attach.getSize();

		//파일을 서버 하드디스크에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/" + fileName);
		File file = new File(realPath);
		try {
			attach.transferTo(file);
		} catch (IOException ex) {
			Logger.getLogger(Exam09FormController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalStateException ex) {
			Logger.getLogger(Exam09FormController.class.getName()).log(Level.SEVERE, null, ex);
		}

		System.out.println("fileName " + fileName);
		System.out.println("contentType " + contentType);
		System.out.println("fileSize " + fileSize);
		return "form/exam02";
	}

	@RequestMapping("file/exam03")
	public void download(HttpServletResponse response) throws IOException {
//		return "file/download"; //다운로드는 페이지를 요청한것이 아니다. 그래서 리턴페이지가 필요없당.

//		응답 HTTP 헤더행을 추가
//		1)파일이름(옵션)
		String fileName="Penguins.jpg";
		response.addHeader("Content-Disposition", "attachment; filename=\""+fileName+ "\""); //첨부파일이기 때문에 저장 다이얼로그를 띄우는 등의 행동을 해야한다.
		//역슬래시(\") 넣는 이유는 공백을 읽어들이도록 하기 위해서이다.,
//		2)파일 종류(필수)
		response.addHeader("Content-Type", "image/jpeg");
//		3) 파일의 크기(옵션)
		File file= new File(servletContext.getRealPath("/WEB-INF/upload/Penguins.jpg")); //절대경로를 얻는다.
		long fileSize=file.length();
		response.addHeader("Content-Length", String.valueOf(fileSize));//스트링으로 받아야하기 때문에 String.valueOf를 쓴다.
		//응답 HTTP본문에 파일 데이터를 출력
		OutputStream os = response.getOutputStream();
		FileInputStream fis=new FileInputStream(file);
//		byte[] data= new byte[1024];
//		int readBytes=-1;
//		while(true){
//			readBytes= fis.read(data);
//			os.write(data, 0, readBytes);
//		}
//		os.flush();
		
		//위의것을 하나로 만든 메소드
		FileCopyUtils.copy(fis, os);
		os.flush();
		fis.close();
		os.close();
	}
}
