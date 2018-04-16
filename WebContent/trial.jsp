<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bravura.gt.*"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="java.util.Iterator"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Insert title here</title>

</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Bravura Solutions</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Cotact</a></li>
      <li><a href="#">Sonata</a></li>
    </ul>
  </div>
</nav>
<div style="margin:30px;">
	<h3><i> A list of Different teams and the number of members currently in them . Also features a delete team button to delete the entire team from database along with the members currently working in them and an add new team button to create a new team with atleast one member .</i> </h3>

</div>

	<%!
private void myFuncHeadStart(javax.servlet.jsp.JspWriter myOut)
{  
  try{ myOut.println("<div style='background-color: #1f1f14;margin : -10px; height : 30px'><h4><center>Bravura Solutions</center></h4></div>"); } 
  catch(Exception eek) { }
}
%>

								<%!
private void myFuncDivStart(javax.servlet.jsp.JspWriter myOut)
{  
  try{ myOut.println("<div style='background-color:#1f1f14;margin : 12px;'>"); } 
  catch(Exception eek) { }
}
%>

<%!
private void myFuncpost(String Bits, javax.servlet.jsp.JspWriter myOut,String t,String n)
{  
  try{ myOut.println("<div style = 'display:inline-block; margin-left = 80px;'><form action ='TeamHandler' method = 'post'><input type='hidden' value='"+t+"' name ='team' ><input type='hidden' value='"+n+"' name ='member' ><input type = 'submit' class='btn btn-info' value= '"+Bits+"'></form></div>"); } 
  catch(Exception eek) { }
}
%>
<%! 
private void myFundDel(javax.servlet.jsp.JspWriter myOut,String _team){
try{ myOut.println("<div style = 'display:inline-block; margin-left=50px;'><i class='material-icons' style='font-size:18px;color:red'>create</i><form action ='TeamHandler' method = 'post'><input type='hidden' value='"+_team+"' name ='teamdel' ><input type = 'submit' value= 'Delete Team?' class='btn btn-danger' ></form></div><br><br><br>"); } 
  catch(Exception eek) { }
}
%>

<%!
private void myFuncAdder(javax.servlet.jsp.JspWriter myOut)
{  
  try{ myOut.println("<div><form action ='TeamHandler' method = 'post'><input type='hidden' value='adderTeam' name ='teamadder' ><input type = 'submit' value= 'Add New Team' class='btn btn-success'></form></div><br><br>"); } 
  catch(Exception eek) { }
}
%>

	<%!
private void myFuncDivEnd(javax.servlet.jsp.JspWriter myOut)
{  
  try{ myOut.println("</div>"); } 
  catch(Exception eek) { }
}
%>


<% ArrayList<String> ar = new ArrayList<String>();
					ar =(ArrayList<String>) request.getAttribute("a");
					Iterator it = ar.iterator();
					ArrayList<String> ar2 = new ArrayList<String>();
					ar2 =(ArrayList<String>) request.getAttribute("b");
					Iterator it2 = ar2.iterator();
					
					
					%>
				
					<% 
					// myFuncHeadStart(out);
					myFuncDivStart(out); %>
					<div style="margin: 40px;">
					<%
					
					while(it.hasNext()){
						String r = it.next().toString();
						String n = it2.next().toString();
						myFuncpost("Team Name :"+r+" Number of members: "+n,out,r,n);
						myFundDel(out,r);
						
					} 
					
					myFuncAdder(out);
					%>

					</div>
					<% 
					
					myFuncDivEnd(out);
					
					%>
				
						
						


	<footer style="background-color:#1f1f14; height: 40px;"><font style="color: white"><center>Bravura Solutions Limited</center></font></footer>					
						

</body>
</html>