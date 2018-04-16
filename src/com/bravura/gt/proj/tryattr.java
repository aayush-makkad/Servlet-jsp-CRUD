package com.bravura.gt.proj;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class tryattr
 */
@WebServlet("/tryattr")
public class tryattr extends HttpServlet {
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
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tryattr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<String> ar = new ArrayList<String>();
		ArrayList<String> ar2 = new ArrayList<String>();
		rs = teamRetrieve();
		rs2 = MemberNumber();
		try {
			while(rs.next()){
				ar.add(rs.getString(1).toString());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs2.next()){
				ar2.add(rs2.getString(1).toString());
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String s = "teuihfjsdhvkfb";
		request.setAttribute("a",ar);
		request.setAttribute("b", ar2);
		RequestDispatcher view = getServletContext().getRequestDispatcher("/trial.jsp");
		view.forward(request,response);
		// System.out.println(request.getAttribute("a").toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
public ResultSet teamRetrieve(){
		
		try{
		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st = (Statement) con.createStatement();
		rs = st.executeQuery(sqlRetrieveTeams+" order by member_numbers");
		
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return rs;
		
	}

public ResultSet MemberNumber(String s){
	try{
		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st = (Statement) con.createStatement();
		rs2 = st.executeQuery(sqlRetrieveMemberNumbers+" where team_name = "+s);
		
	}
	catch(Exception e){
		e.printStackTrace();
		
	}
	return rs2;
}
public ResultSet MemberNumber(){
	try{
		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st = (Statement) con.createStatement();
		rs2 = st.executeQuery(sqlRetrieveMemberNumbers+" order by member_numbers");
		
	}
	catch(Exception e){
		e.printStackTrace();
		
	}
	return rs2;
}

}
