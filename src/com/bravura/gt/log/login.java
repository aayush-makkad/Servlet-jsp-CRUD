package com.bravura.gt.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class login
 */
@WebServlet(description = "login handler", urlPatterns = { "/login" })
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String user,pass;  
     private static final String DriverLoad = "com.mysql.jdbc.Driver";
 	private static final String ConString = "jdbc:mysql://localhost:3306/servlet_project";
 	private static final String userName = "root";
 	private static final String passWord = "root";
 	private static final String tableName = "access";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 user = request.getParameter("username");
		pass = request.getParameter("password");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		boolean check = verify(user,pass);
		PrintWriter pr = response.getWriter();
		
		response.setContentType("text/html");
   	 String htmlres = "<html>";
   	 if(check)
   	 {
   	 //htmlres += "<h1> Success "+user+" </h1>";
   	 
   	String site = "dashboard.html" ; 
   	response.setStatus(response.SC_MOVED_TEMPORARILY); 
   	response.setHeader("Location", site);  
   	 
   	 }
   	 else
   		 htmlres += "<h1> Failed "+user+" </h1>";
   	 htmlres +="</html>";
   	 pr.println(htmlres);	
	}
	
	private static boolean verify(String u , String p){
		
		int i=0;
		 try{
		     Class.forName(DriverLoad);
		     Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		     Statement st = (Statement) con.createStatement();
		   ResultSet rs =  st.executeQuery("select count(*) from access where username = '"+u+"' and password = '"+p+"'");
		     while(rs.next()){
		    	 i = rs.getInt(1);
		     }
		     if(i==1)
		    	 return true;
		     else
		    	 return false;
		     }
		     catch(Exception e){
		    	 e.printStackTrace();
		    	 return false;
		     }
		
	}

}
