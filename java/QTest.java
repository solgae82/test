package com.solgae.scheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
*	quartz 스케쥴러 + DB연동 예제
*/
public class QTest implements Job{

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try{

			DataSource bean = getDataSource();
			con = bean.getConnection();
			
		
			System.out.println("=====>excute:"+cal(con));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	private DataSource getDataSource() throws Exception{
		ServletContext sc= ContextLoaderListener.getCurrentWebApplicationContext().getServletContext();
		ApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		DataSource bean = (DataSource) springContext.getBean("dataSource");
		
		return bean;
	}
	
	private Connection getConnection(DataSource bean) throws SQLException{
		return (Connection) bean.getConnection();
	}
	
	private int cal(Connection con){
		String sql = "select (2*?)  , (3*?) ";
		int num = 0;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 6);
			pstmt.setInt(2, 5);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int t_num1 = rs.getInt(1);
				int t_num2 = rs.getInt(2);
				
				num = t_num1 + t_num2;
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try{
				if(rs != null) rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(pstmt != null) pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			try{
				if(con != null) con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
			
		}
		return num;
	}

}
