package com.solgae.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * root : /web 컨텍스트
 */
@WebServlet(description = "cors 테스트", urlPatterns = { "/cors" })
public class Cors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=utf-8");
		
		response.getWriter().append("방식:").append(request.getParameter("cors_mode"));
		System.out.println("----------GET,POST");
		
		String origin = request.getHeader("Origin");				
		response.addHeader("Access-Control-Allow-Origin", origin) ;
		response.addHeader("Access-Control-Allow-Credentials", "true"); 
		response.addHeader("Vary", "Origin");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("----------OPTIONS");
		/*
		String origin = request.getHeader("Origin");				
		response.addHeader("Access-Control-Allow-Origin", origin) ;
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS") ;
		response.addHeader("Access-Control-Allow-Headers", "SPORT, Content-Type") ;	
		response.addIntHeader("Access-Control-Max-Age", 1); // 캐쉬 초 설정
		response.addHeader("Access-Control-Allow-Credentials", "true");
		*/
		String origin = request.getHeader("Origin");
		String accessControlRequestHeaders = request.getHeader("Access-Control-Request-Headers");
		
		response.addHeader("Access-Control-Allow-Origin", origin) ;
		response.addHeader("Access-Control-Allow-Methods", "*") ;
		response.addHeader("Access-Control-Allow-Headers", accessControlRequestHeaders) ;	
		response.addIntHeader("Access-Control-Max-Age", 1); // 캐쉬 초 설정
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Vary", "Origin");
	}

}
