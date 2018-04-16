package com.bravura.gt.proj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//class Demo{
//	 private String name;
//	 
//	    public void setName(String name) {
//	        this.name = name;
//	    }
//	 
//	    public String getName() {
//	        return name;
//}

/**
 * Servlet implementation class dashboard
 */
@WebServlet(description = "team names dashboard", urlPatterns = { "/dashboard" })
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String type = "default";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
  
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 type = request.getParameter("retrieve");
		 //System.out.println(type.toString());
		 
		 

	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		if(type.equals("retri")){
			
			response.sendRedirect("tryattr");
			//PrintWriter pr = response.getWriter();
		//	Str"<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>Insert title here</title></head><body><table id="messages" border="1"><tr><th>Team Name</th><th>Member Names</th></table></body></html>";
		
		
		}
			
		
	}

}

