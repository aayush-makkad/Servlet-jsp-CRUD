package com.bravura.gt.proj;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class MemAdd
 */
@WebServlet("/MemAdd")
public class MemAdd extends HttpServlet {
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
       
	String team;
	String mem;
	String des;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		team = (String) request.getParameter("team");
		mem = request.getParameter("mem");
		des = request.getParameter("des");
	//	System.out.println(team+mem+des);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int _currentMemNum = 0;
 		rs = MemberNumber(team);
		try{
		rs.next();
		_currentMemNum = rs.getInt(1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		int _newMemNum = 0;
		if(_currentMemNum > 0){
			_newMemNum = _currentMemNum + 1;
		}
		System.out.println(_currentMemNum);
		if(alterneeded(team,_newMemNum)){
			AlterTable(_newMemNum,_currentMemNum);
		}
		else{
			System.out.println("naah");
		}
		
		if(insertinto(team,mem,des,_newMemNum))
		{
			System.out.println("Inserted");
		}
		else{
			System.out.println("Nahhhh");
		}
		if(finalUp(_newMemNum,team))
		{
			System.out.println("Inserted");
			response.sendRedirect("done.html");
		}
		else{
			System.out.println("Nahhhh");
			RequestDispatcher rd = request.getRequestDispatcher("/dashboard");
			rd.forward(request,response);
		}
		
		
		
	}
	
	public ResultSet MemberNumber(String s){
		try{
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			rs2 = st.executeQuery(sqlRetrieveMemberNumbers+" where team_name = '"+s+"'");
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return rs2;
	}
	
	
	
	public boolean AlterTable(int newMem,int oldMem){
		boolean flag = false;
		try{
		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st = (Statement) con.createStatement();
		st.execute("ALTER TABLE `teamMembers` ADD COLUMN `mem_"+newMem+"` VARCHAR(45) NULL DEFAULT NULL AFTER `mem_"+oldMem+"`,ADD COLUMN `designation_"+newMem+"` VARCHAR(45) NULL AFTER `designation_"+oldMem+"`");
		flag = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
		
	}
	
	public boolean alterneeded(String _team,int _setter){
		ResultSet rs = null;
		
		boolean flag = false;
		try{
			
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			rs = st.executeQuery("select max(member_numbers) from teams");
			rs.next();
			int get = rs.getInt(1);
			if(get<=_setter){
				flag = true;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean insertinto(String _team,String _name,String _desig,int _mem){
		boolean flag = false;
		try{
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			st.execute("update teamMembers set mem_"+_mem+"= '"+_name+"', designation_"+_mem+" = '"+_desig+"' where team_name = '"+_team+"'");
			flag = true;
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		return flag;
	}
	
	public boolean finalUp(int _set,String _team){
		
		boolean flag = false;
		try{
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			st.execute("update teams set member_numbers = "+_set+" where team_name = '"+_team+"'");
			flag =true;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
		
	}
	
	
	
	
	
	
	
	

}
