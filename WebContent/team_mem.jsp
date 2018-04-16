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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<%!
private void myFuncpost(String Bits, javax.servlet.jsp.JspWriter myOut,String t,String n,String _team)
{  
  try{ myOut.println("<div style = 'display:inline-block; margin-left = 80px;'><form action ='UpdaterNet' method = 'post'><input type = 'hidden' name='te' value='"+_team+"'><input type='hidden' value='"+t+"' name ='team2' ><input type='hidden' value='"+n+"' name ='member' ><input type = 'submit' value= '"+Bits+"' class='btn btn-info'></form></div>"); } 
  catch(Exception eek) { }
}
%>

<%!
private void myFuncDel(String Bits, javax.servlet.jsp.JspWriter myOut,String t,String n,String _team)
{  
  try{ myOut.println("<div style = 'display:inline-block; margin-left=50px;'><form action ='MemDel' method = 'post'><input type = 'hidden' name='te' value='"+_team+"'><input type='hidden' value='"+t+"' name ='team' ><input type='hidden' value='"+n+"' name ='member' ><input type = 'submit' value= 'Delete Member?' class='btn btn-danger'></form></div><br><br><br>"); } 
  catch(Exception eek) { }
}
%>

<%!
private void myFuncAdder(javax.servlet.jsp.JspWriter myOut)
{  
  try{ myOut.println("<div><form action ='TeamHandler' method = 'post'><input type='hidden' value='adderMem' name ='teamadder' ><input type = 'submit' value= 'Add New Member' class='btn btn-success'></form></div><br><br>"); } 
  catch(Exception eek) { }
}
%>

<div style="margin: 30px;">
<h2>Click on the member name to shift that member to another team </h2>
</div>

<div style="margin: 20px; background-color: #1f1f14; height: 500px; padding: 40px;">
<% ArrayList<String> ar = new ArrayList<String>();
					ar =(ArrayList<String>) request.getAttribute("a");
					Iterator it = ar.iterator();
					ArrayList<String> ar2 = new ArrayList<String>();
					ar2 =(ArrayList<String>) request.getAttribute("b");
					Iterator it2 = ar2.iterator();
					
					
					%>
					
					<% 
					String value=(String)request.getAttribute("team");
					while(it.hasNext()){
						String r = it.next().toString();
						String n = it2.next().toString();
						myFuncpost("Name :"+r+" Designation: "+n,out,r,n,value);
						myFuncDel("Name :"+r+" Designation: "+n,out,r,n,value);
						
					} 
					
					
					%>
					
					<% 
					myFuncAdder(out);
					
					
					%>
</div>
				
<footer style="background-color:#1f1f14; height: 40px;"><font style="color: white"><center>Bravura Solutions Limited</center></font></footer>
</body>
</html>