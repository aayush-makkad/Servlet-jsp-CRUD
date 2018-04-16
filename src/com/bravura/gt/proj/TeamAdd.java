package com.bravura.gt.proj;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class TeamAdd
 */
@WebServlet(description = "Add Teams", urlPatterns = { "/TeamAdd" })
public class TeamAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String DriverLoad = "com.mysql.jdbc.Driver";
	private static final String ConString = "jdbc:mysql://localhost:3306/servlet_project";
	private static final String userName = "root";
	private static final String passWord = "root";
	private static final String tableName1 = "teamMembers";
	private static final String tableName2 = "teams";
    
	String team = "def";
	String mem = "def";
	String des = "def"; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		team = request.getParameter("team");
		mem = request.getParameter("member");
		des = request.getParameter("des");
		System.out.println("in serv");
		System.out.println(team + mem + des);
		
		
			
			 adder(team,mem,des);
			 response.sendRedirect("done.html");
			 
			// System.out.println(c);
			
			
		
		
		
	}
	
	public void adder(String _team,String _member,String _des){
		int aa = 2;
		try{
		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st1 = (Statement) con.createStatement();
		Statement st2 = (Statement) con.createStatement();
		
		st1.execute("insert into "+tableName2+"(team_name) values('"+_team+"')");
		st2.execute("insert into "+tableName1+"(team_name,mem_1,designation_1) values('"+_team+"','"+_member+"','"+_des+"')");
	} 
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

}
