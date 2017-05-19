package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	
	private String characterSet;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {         //필터 처음 생성될때
		System.out.println("CharacterEncodingFilter init()");
		characterSet=filterConfig.getInitParameter("encoding");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) //필터요청될때
			throws IOException, ServletException {
		//전처리: 서블릿이 실행하기 전에 처리(요청처리 전)
		System.out.println("CharacterEncodingFilter doFilter()- 전처리");
		request.setCharacterEncoding(characterSet);
		//-----------------------------------------------------------------------
		filterChain.doFilter(request, response);
		//-----------------------------------------------------------------------
		//후처리 : 서블릿이 실행한 후에 처리(요청처리 후, 응답을 보내기 전)
		System.out.println("CharacterEncodingFilter doFilter()- 후처리");
		
	}
	@Override
	public void destroy() { //필터가 제거될때
		System.out.println("CharacterEncodingFilter destroy()");
	}
}
