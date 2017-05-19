package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener { //context(App�ĺ���)�� deploy�ɶ��� �����ϴٰ� deploy�ɶ� �޼ҵ带 �����Ѵ�.
	@Override
	public void contextInitialized(ServletContextEvent event) { //deploy�Ҷ� ����
		System.out.println("ContextLoaderListener contextInitialized() ����");
		String key1=event.getServletContext().getInitParameter("key1"); //��App�� �ʱ� �Ķ���Ͱ��� ��´�.
		System.out.println(key1);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {//undeploy�Ҷ� ����
		System.out.println("ContextLoaderListener contextDestroyed() ����");
	}
	
}
