package com.bravura.gt.proj;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class FinalUpdate
 */
@WebServlet("/FinalUpdate")
public class FinalUpdate extends HttpServlet {
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
    
	
	String oldTeam = "def";
	String _newTeam = "def";
	String name = "def";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		oldTeam = request.getParameter("member");
		_newTeam = request.getParameter("newteam");
		name = request.getParameter("naa");
		System.out.println(oldTeam);
		System.out.println(_newTeam);
		System.out.println(name);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int _maxNew = 1;
		int _maxOld = 1;
		ResultSet rs21 = MemberNumber(_newTeam);
		ResultSet rs22 = MemberNumber(oldTeam);
				try {
					rs21.next();
					rs22.next();
					_maxNew = rs21.getInt(1);
					_maxOld = rs22.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		int _oldLoc = getMem(_maxOld,name);
		System.out.println(_oldLoc);
		System.out.println(_maxNew);
		int _newLoc = _maxNew+1;
		if(alterneeded(_newLoc)){
			AlterTable(_newLoc,_maxNew);
		}
		
		String de = getDesig(name,_oldLoc);
		System.out.println(de);
		insertNewloc(name,de,_newLoc,_newTeam);
		updater(_maxOld,_oldLoc,oldTeam);
		
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
	public boolean alterneeded(int _setter){
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
	public String getDesig(String _name,int _loc){
		String ret = " ";
		try{
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			ResultSet re = st.executeQuery("select designation_"+_loc+" from teamMembers where mem_"+_loc+" ='"+_name+"'");
			re.next();
			ret = re.getString(1);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
		
		
	}
	
	public boolean insertNewloc(String _name,String _des,int newLoc,String _team){
		boolean flag = false;
		try{
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement) con.createStatement();
			st.execute("update teamMembers set mem_"+newLoc+" = '"+_name+"',designation_"+newLoc+" = '"+_des+"' where team_name = '"+_team+"'");
			Statement st2 = (Statement) con.createStatement();
			st2.execute("update teams set member_numbers = "+newLoc+" where team_name = '"+_team+"'");
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
		
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
