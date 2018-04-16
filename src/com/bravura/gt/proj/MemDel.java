package com.bravura.gt.proj;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class MemDel
 */
@WebServlet("/MemDel")
public class MemDel extends HttpServlet {
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
    
	String _team = "def";
	String _name = "def";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemDel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getParameter("team")!=null){
			_team = request.getParameter("te");
			_name = request.getParameter("team");
		}
		System.out.println(_name);
		System.out.println(_team.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int _num = 0;
		//System.out.println(_team);
		ResultSet rsm = MemberNumber(_team);
		try {
			rsm.next();
			_num = Integer.parseInt(rsm.getString(1));
			System.out.println(_num);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int a = getMem(_num,_name);
		System.out.println(a);
		boolean b = updater(_num,a,_team);
		System.out.println(b);
		response.sendRedirect("done.html");
		
		
		
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
	
	
	public int getMem(int _max,String _s){
		boolean flag = false;
		int i = 0;
		try{
			
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			for(i=1;i<_max+1;i++){
				rs = st.executeQuery("select * from teamMembers where  mem_"+i+" = '"+_s+"'");
				if(rs.next()){
					flag = true;
					break;
				}
			}
		
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(flag){
			return i;
		}
		else
			return 1;
		
		
	}
	
	public boolean updater(int _max,int _selection,String _s){
		int toset = _max-1;
		boolean flag = false;
		try{
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			Statement st2 = (Statement) con.createStatement();
			Statement st3 = (Statement) con.createStatement();
			st.execute("update teamMembers set mem_"+_selection+" = mem_"+_max+" where team_name = '"+_s+"'");
			st3.execute("update teamMembers set mem_"+_max+" = NULL where team_name = '"+_s+"'");
			st2.execute("update teams set member_numbers = "+toset+" where team_name = '"+_s+"'");
			flag = true;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
		
	}
	

}
