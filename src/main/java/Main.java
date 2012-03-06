import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main( String[] args )
    {
    	 ApplicationContext context = 
    		new ClassPathXmlApplicationContext("spring-config.xml");
    	 
    	 TestBean bean = (TestBean) context.getBean("mybean");
    	 System.out.println("driverClassName: "+bean.getDriverClassName());
    	 System.out.println("url: "+bean.getUrl());
    	 System.out.println("username: "+bean.getUsername());
    	 System.out.println("password: "+bean.getPassword());

    }

}
