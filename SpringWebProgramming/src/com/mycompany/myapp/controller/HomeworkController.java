package com.mycompany.myapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.service.Exam12Service;

@Controller
public class HomeworkController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeworkController.class);

	@Autowired
	private Exam12Service service;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/homework/exam01")
	public String exam01() {
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

	@RequestMapping(value = "/homework/exam02", method = RequestMethod.GET)
	public String exam02Get() {
		return "homework/exam02";
	}

	@RequestMapping(value = "/homework/exam02", method = RequestMethod.POST)
	public String exam02Post(Exam12Board board) throws IllegalStateException, IOException {
		// 첨부파일에 대한 정보를 컬럼값으로 설정
		board.setBoriginalfilename(board.getBattach().getOriginalFilename());
		board.setBfilecontent(board.getBattach().getContentType());
		String fileName = new Date().getTime() + "-" + board.getBoriginalfilename();
		board.setBsavedfilename(fileName);
		// 첨부파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");
		System.out.println(realPath);
		File file = new File(realPath + fileName);
		board.getBattach().transferTo(file);

		// 서비스 객체에 요청 처리 요청
		service.boardWrite(board);

		LOGGER.info(realPath);

		return "redirect:/homework/exam05";
	}

	@RequestMapping(value = "/homework/exam03", method = RequestMethod.GET)
	public String exam03Get() {
		return "homework/exam03";
	}

	@RequestMapping(value = "/homework/exam03", method = RequestMethod.POST)
	public String exam03Post(Exam12Member member) throws IllegalStateException, IOException {
		member.setMoriginalfilename(member.getMattach().getOriginalFilename());
		member.setMfilecontent(member.getMattach().getContentType());
		String fileName = new Date().getTime() + "-" + member.getMoriginalfilename();
		member.setMsavedfilename(fileName);
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");
		File file = new File(realPath + fileName);
		member.getMattach().transferTo(file);

		service.memberJoin(member);

		return "redirect:/";
	}

	@RequestMapping("/homework/exam04")
	public String exam04(Model model) {
		List<Exam12Board> list = service.boardListAll();
		model.addAttribute("list", list); // 객체 전달.
		return "homework/exam04";
	}

	@RequestMapping("/homework/exam05") // 넘어오는 값이 없을 경우 기본값을 1로한다.
	public String exam05(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		// 한 페이지를 구성하는 행 수
		int rowsPerPage = 10;
		// 한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 7;
		// 총 행수
		// int totalRows=100;
		int totalRows = service.boardTotalRows();
		// 전체 페이지수
		int totalPageNo = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
		// 전체 그룹수
		int totalGroupNo = (totalPageNo / pagesPerGroup) + ((totalPageNo % pagesPerGroup != 0) ? 1 : 0);
		// 현재그룹번호
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		// 현재 그룹의 시작 페이지 번호
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		// 현재 그룹의 마지막 페이지 번호
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if (groupNo == totalGroupNo) {
			endPageNo = totalPageNo;
		}
		// 현재 페이지의 행의 데이터 가져오기
		List<Exam12Board> list = service.boardListPage(pageNo, rowsPerPage);

		// view(jsp)로 넘겨줄 데이터
		model.addAttribute("list", list); // 객체 전달.
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);

		// view 이름 리턴
		return "homework/exam05";
	}

	@RequestMapping("/homework/exam05Detail")
	public String exam05Detail(int bno, Model model) {
		Exam12Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "homework/exam05Detail";
	}

	@RequestMapping("/homework/exam05CheckBpassword")
	public String exam05CheckBpassword(int bno, String bpassword, Model model) {
		String result = service.boardCheckBpassword(bno, bpassword);
		model.addAttribute("result", result);
		return "homework/exam05CheckBpassword";
	}

	@RequestMapping(value = "/homework/exam05Update", method = RequestMethod.GET)
	public String exam05UpdateGet(int bno, Model model) {
		Exam12Board board = service.getBoard(bno);
		model.addAttribute("board", board);

		return "homework/exam05Update";
	}

	@RequestMapping(value = "/homework/exam05Update", method = RequestMethod.POST)
	public String exam05UpdatePost(Exam12Board board) {
		// 첨부파일 변경 여부 검사
		if (!board.getBattach().isEmpty()) {
			// 첨부파일에 대한 정보를 컬럼값으로 설정
			board.setBoriginalfilename(board.getBattach().getOriginalFilename());
			board.setBfilecontent(board.getBattach().getContentType());
			String fileName = new Date().getTime() + "-" + board.getBoriginalfilename();
			board.setBsavedfilename(fileName);
			board.setBoriginalfilename(board.getBsavedfilename());
			// 첨부파일을 서버 로컬 시스템에 저장
			String realPath = servletContext.getRealPath("/resources/image/"); 
//					getRealPath("/WEB-INF/upload/");
			File file = new File(realPath + fileName);
//			File file = new File("c:/upload/" + fileName);
			System.out.println(realPath + fileName);
			try {
				board.getBattach().transferTo(file);
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 게시물 수정 처리
		service.boardUpdate(board);
		return "redirect:/homework/exam05Detail?bno=" + board.getBno();
	}

	@RequestMapping("/homework/exam05Delete")
	public String exam05Delete(int bno) {
		service.boardDelete(bno);
		return "redirect:/homework/exam05";
	}

	@RequestMapping("/homework/file/exam03")
	public void download(HttpServletResponse response, @RequestHeader("User-Agent") String userAgent, Exam12Board board)
			throws IOException {
		// return "file/download"; //다운로드는 페이지를 요청한것이 아니다. 그래서 리턴페이지가 필요없당.

		// 응답 HTTP 헤더행을 추가
		// 1)파일이름(옵션)
		// String fileName ="펭귄.jpg";
		String fileName = board.getBattach().getOriginalFilename();
		String encodingFileName;
		if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("edge")) { // 파일이름이
																											// 한글일
																											// 경우
																											// 파일이름
																											// 인코딩이
																											// 필요하다.
			encodingFileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		System.out.println(encodingFileName);

		response.addHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\""); // 첨부파일이기
																										// 때문에
																										// 저장
																										// 다이얼로그를
																										// 띄우는
																										// 등의
																										// 행동을
																										// 해야한다.
		// 역슬래시(\") 넣는 이유는 공백을 읽어들이도록 하기 위해서이다.,
		// 2)파일 종류(필수)
		response.addHeader("Content-Type", "image/jpeg");
		// 3) 파일의 크기(옵션)
		File file = new File(servletContext.getRealPath("/WEB-INF/WebContent/upload/" + fileName)); // 절대경로를
																							// 얻는다.
		long fileSize = file.length();
		response.addHeader("Content-Length", String.valueOf(fileSize));// 스트링으로
																		// 받아야하기
																		// 때문에
																		// String.valueOf를
																		// 쓴다.

		// 응답 HTTP본문에 파일 데이터를 출력
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		// byte[] data= new byte[1024];
		// int readBytes=-1;
		// while(true){
		// readBytes= fis.read(data);
		// os.write(data, 0, readBytes);
		// }
		// os.flush();

		// 위의것을 하나로 만든 메소드
		FileCopyUtils.copy(fis, os);
		os.flush();
		fis.close();
		os.close();
	}



}
