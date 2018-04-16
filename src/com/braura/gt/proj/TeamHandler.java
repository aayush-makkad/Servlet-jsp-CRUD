package com.braura.gt.proj;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class TeamHandler
 */
@WebServlet(description = "Handles team related operations", urlPatterns = { "/TeamHandler" })
public class TeamHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String DriverLoad = "com.mysql.jdbc.Driver";
	private static final String ConString = "jdbc:mysql://localhost:3306/servlet_project";
	private static final String userName = "root";
	private static final String passWord = "root";
	private static final String tableName = "teamMembers";
	private static final String sqlRetrieveTeams = "select team_name from "+tableName;
	private static final String sqlRetrieveCurrentMembers = "select current_project from "+tableName;
	
	private static ResultSet rs = null;
	private static ResultSet rs2 = null;
	
	 String va="def";
	  String need = "def";
	  String mem = "def";
	  String delTeam = "def";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 
		if(request.getParameter("teamadder")!=null)
		{
		 need = request.getParameter("teamadder");
		 
		 System.out.println("just add");
		 request.setAttribute("team",va);
			if(need.equals("adderTeam"))
			{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/team_add.jsp");
			view.forward(request,response);
			}
			else{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/member_add.jsp");
				view.forward(request,response);
			}
		}
		 if(request.getParameter("team")!=null){
			 va = request.getParameter("team");
			 mem = request.getParameter("member");
			 
		 }
		 if(request.getParameter("teamdel")!=null){
			 delTeam = request.getParameter("teamdel");
		 }

			
			
		
			System.out.println(va.toString());
			System.out.println(need.toString());
			System.out.println(mem.toString());
			
			
			
			
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		ArrayList<String> ar = new ArrayList<String>();
		ArrayList<String> ar2 = new ArrayList<String>();
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("team")!=null){
			
			
			//	if(!need.toString().equals("def")){
					try {
						ar = getMem(va,Integer.parseInt(mem));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			//	}
				@SuppressWarnings("rawtypes")
				Iterator it = ar.iterator();
				while(it.hasNext()){
					System.out.println(it.next().toString());
					
				}
			
				
				//	if(!need.toString().equals("def")){
						try {
							ar2 = getDes(va,Integer.parseInt(mem));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				//	}
					@SuppressWarnings("rawtypes")
					Iterator it2 = ar2.iterator();
					while(it2.hasNext()){
						System.out.println(it2.next().toString());
						
					}
					request.setAttribute("a", ar);
					request.setAttribute("b", ar2);
					request.setAttribute("team",va);
					
					RequestDispatcher view = getServletContext().getRequestDispatcher("/team_mem.jsp");
					view.forward(request,response);
			
		}
		if(request.getParameter("teamadder")!=null){
			request.setAttribute("team",va);
			RequestDispatcher rd = request.getRequestDispatcher("/team_add.jsp");
			rd.forward(request,response);
		}
		if(request.getParameter("teamdel")!=null){
			
			if(delteam(delTeam)){
				System.out.println("done deletion of"+delTeam);
				out.println("done deletion of "+delTeam);
				response.sendRedirect("done.html");
			}
				
			
		}
		
		
	}
	
	
	public boolean delteam(String _s){
		
		boolean flag = false;
		try{
			
			Class.forName(DriverLoad);
			Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
			Statement st = (Statement)con.createStatement();
			Statement st2 = (Statement)con.createStatement();
			st.execute("delete from teams where team_name = '"+_s+"'");
			st2.execute("delete from teamMembers where team_name = '"+_s+"'");
			flag = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	public ArrayList<String> getMem(String s,int num) throws SQLException, ClassNotFoundException{

		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st = (Statement)con.createStatement();
		ArrayList<String> ar = new ArrayList<String>();
		for(int i=1;i<num+1;i++){

			 rs = st.executeQuery("select mem_"+i+" from "+tableName+" where team_name = '"+s+"'");
			 rs.next();
			ar.add((String)rs.getString(1));


		}

		return ar;

	}
	
	public ArrayList<String> getDes(String s,int num) throws SQLException, ClassNotFoundException{

		Class.forName(DriverLoad);
		Connection con = (Connection) DriverManager.getConnection(ConString,userName,passWord);
		Statement st = (Statement)con.createStatement();
		ArrayList<String> ar2 = new ArrayList<String>();
		for(int i=1;i<num+1;i++){

			 rs2 = st.executeQuery("select designation_"+i+" from "+tableName+" where team_name = '"+s+"'");
			 rs2.next();
			ar2.add((String)rs2.getString(1));


		}

		return ar2;

	}

}
