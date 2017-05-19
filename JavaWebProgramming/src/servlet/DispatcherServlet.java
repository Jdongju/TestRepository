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
		System.out.println("DispatcherServlet init() ����"); //�������� �ѹ� ��������� ����
		String name1=config.getInitParameter("name1"); 
		String name2=config.getInitParameter("name2");
		System.out.println(name1);
		System.out.println(name2);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)//���񽺴� Ŭ���̾�Ʈ�� ��û�Ҷ����� ����
			throws ServletException, IOException {
		System.out.println("DispatcherServlet service() ����");
	}
}