package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener { //context(App식별자)가 deploy될때를 감지하다가 deploy될때 메소드를 실행한다.
	@Override
	public void contextInitialized(ServletContextEvent event) { //deploy할때 실행
		System.out.println("ContextLoaderListener contextInitialized() 실행");
		String key1=event.getServletContext().getInitParameter("key1"); //웹App의 초기 파라미터값을 얻는다.
		System.out.println(key1);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {//undeploy할때 실행
		System.out.println("ContextLoaderListener contextDestroyed() 실행");
	}
	
}
