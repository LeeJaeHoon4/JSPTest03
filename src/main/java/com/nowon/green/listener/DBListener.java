package com.nowon.green.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * Application Lifecycle Listener implementation class DBListener
 *
 */
@WebListener
public class DBListener implements ServletContextListener {


    public DBListener() {
        // TODO Auto-generated constructor stub
    }


    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
	    	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle/green");
	    	Connection conn = ds.getConnection();
	    	System.out.println("DBCP설정 완료");
	    	//ServletContext 객체를 생성해서 선언해놓으면 지금 sc의 lifecycle이 서버의 생성 시점과 서버의 소멸 시점과 거의 동일하기 때문에 공유 메모리 처럼 쓸수있다.
	    	// sc 객체를 다른 파일 에서도 꺼내 쓸수 있다.
	    	ServletContext sc = sce.getServletContext();
	    	ServletContext sc2 = sce.getServletContext();
	    	//Connection 객체를 등록함
	    	sc.setAttribute("conn", conn);
	    	sc2.setAttribute("ds", ds);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}
