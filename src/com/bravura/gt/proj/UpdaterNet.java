package com.bravura.gt.proj;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class UpdaterNet
 */
@WebServlet("/UpdaterNet")
public class UpdaterNet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String DriverLoad = "com.mysql.jdbc.Driver";
	private static final String ConString = "jdbc:mysql://localhost:3306/servlet_project";
	private static final String userName = "root";
	private static final String passWord = "root";
	private static final String tableName = "teams";
	private static final String sqlRetrieveTeams = "select team_name from "+tableName;
	private static final String sqlRetrieveMemberNumbers = "select member_numbers from "+tableName;
	private static ResultSet rs = null;
	private static ResultSet rs2 = null;
	
       
	String team = "def";
	String name = "def";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdaterNet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		team = request.getParameter("te");
		name = request.getParameter("team2");
		System.out.println(team);
		System.out.println(name);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		ResultSet rm = teamRetrieve();
		ArrayList<String> ar = new ArrayList<String>();
		try {
			while(rm.next()){
				ar.add(rm.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("options", ar);
		request.setAttribute("currentTeam", team);
		request.setAttribute("nameMember", name);
		RequestDispatcher view = getServletContext().getRequestDispatcher("/available.jsp");
		view.forward(request,response);
		
		
	}
	
public ResultSet teamRetrieve(){
		
		try{
		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st = (Statement) con.createStatement();
		rs = st.executeQuery(sqlRetrieveTeams+" where not team_name = '"+team+"'");
		
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return rs;
		
	}

}
