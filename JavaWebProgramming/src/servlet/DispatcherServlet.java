package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("DispatcherServlet init() 실행"); //서블릿이 한번 만들어질때 실행
		String name1=config.getInitParameter("name1"); 
		String name2=config.getInitParameter("name2");
		System.out.println(name1);
		System.out.println(name2);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)//서비스는 클라이언트가 요청할때마다 실행
			throws ServletException, IOException {
		System.out.println("DispatcherServlet service() 실행");
	}
}
