<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<%  
String name=request.getParameter("val");  
if(name==null||name.trim().equals("")){  

}else{  
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_project","root","root");  
PreparedStatement ps=con.prepareStatement("select * from teamMembers where team_name like '"+name+"%'");  
ResultSet rs=ps.executeQuery();  
  
if(!rs.isBeforeFirst()) {      
 out.println("<p>No Record Found!</p>");   
}else{  
	out.println("<br><br>");
	out.println("<div style='background-color: #aaff80; height: 50px; margin: 20px'><center>Results for "+name+" are : </center></div>");
out.print("<div id='toPrint'><table border='10' bordercolor = 'white' cellpadding='2' width='100%'>");  
out.print("<tr><th bgcolor='white'>Id</th><th bgcolor='white'>Name</th><th bgcolor='white'>Member</th><th bgcolor='white'>Member</th><th bgcolor='white'>Member</th><th bgcolor='white'>Designation</th><th bgcolor='white'>Designation</th></tr>");  
while(rs.next()){  
out.print("<tr><td bgcolor = 'white'>"+rs.getString(1)+"</td><td bgcolor='white'>"+rs.getString(2)+"</td><td bgcolor='white'>"+rs.getString(3)+"</td><td bgcolor='white'>"+rs.getString(4)+"</td><td bgcolor='white'>"+rs.getString(5)+"</td><td bgcolor='white'>"+rs.getString(6)+"</td><td bgcolor='white'>"+rs.getString(7)+"</td></tr>");  
}  
out.print("</table></div>");  
}
con.close();  
}catch(Exception e){out.print(e);}  
} 
%>  