package com.tinet.core.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *springConfig-core.xml 文件中 配置多个数据源，
 *在具体应用时，可以灵活选择使用哪个数据源
 * @author wangguiyu
 *
 */

public class testDynamicData {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-core.xml");  
		BaseDAO dao = (BaseDAO) context.getBean("baseDAO", BaseDAOImpl.class);  
		try {  
		    JdbcTemplate jt=dao.getJdbcTemplate();//没有注入数据源，默认为springConfig-core.xml中配置的数据源dataSource
		    List<Map<String,Object>> lists=jt.queryForList("select * from ccic");
		    for(Map<String,Object> map:lists){
		    	System.out.println("id="+map.get("id")+",ccic_url="+map.get("ccic_url")
		    			+",ccic_ip="+map.get("ccic_ip")+",ccic_port="+map.get("ccic_port")
		    			+",ccic_user="+map.get("ccic_user")+",ccic_pwd="+map.get("ccic_pwd"));
		    }
		    
		    //TestTransAction ta=(TestTransAction) new TestTransActionImpl();
		   // ta.addTransAction(jt);
		    
		    
			System.out.println("**************切换数据源********************");
			
		    DatabaseContextHolder.setDBType("1");//切换数据源id  
		    JdbcTemplate jt1=dao.getJdbcTemplate();//获得javaTemplate
		   
		    
		    List<Map<String,Object>> lists1=jt1.queryForList("select * from account limit 1");//读写数据库
		    for(Map<String,Object> map:lists1){	
		    	System.out.println("@@account::id = "+map.get("id"));
		    }
		    
		    //----测试声明式事务
		    //IAccount ia=(IAccount) new AccountServiceImpl();
		    //ia.saveTransAction(jt1);
		    
		    
		   
		      
		} catch (Exception e) {  
		    e.printStackTrace();  
		} 

	}

}
