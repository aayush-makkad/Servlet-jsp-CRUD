<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bravura.gt.*"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="java.util.Iterator"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<h3><i> A list of teams other than the team the employee is already a part of, shows available options of the teams this member can be shifted to. all his data will be migrated to the new team . </i> </h3>

</div>
<div style="background-color: #1f1f14; height: 650px; margin-top: 20px; padding: 8px;">
<%!
private void myFuncpost(String Bits, javax.servlet.jsp.JspWriter myOut,String t,String n)
{  
  try{ myOut.println("<div style='margin: 16px;'><form action ='FinalUpdate' method = 'post'><input type='hidden' value='"+t+"' name ='naa' ><input type='hidden' value='"+n+"' name ='member' ><input type='hidden' name='newteam' value= '"+Bits+"'><input  type = 'submit' class='btn btn-info' value= '"+Bits+"' style='background-color: rgba(200,0,0,0.2);'></form></div><br><br>"); } 
  catch(Exception eek) { }
}
%>



<%!
private void myFuncAdder(javax.servlet.jsp.JspWriter myOut)
{  
  try{ myOut.println("<div><form action ='TeamHandler' method = 'post'><input type='hidden' value='adderTeam' name ='teamadder' ><input type = 'submit' value= 'Add New Member' style='background-color: rgba(200,0,0,0.2);'></form></div><br><br>"); } 
  catch(Exception eek) { }
}
%>

<% ArrayList<String> ar = new ArrayList<String>();
					ar =(ArrayList<String>) request.getAttribute("options");
					Iterator it = ar.iterator();
					
					
					
					%>
					
					<% 
					String emp = (String)request.getAttribute("nameMember");
					String value=(String)request.getAttribute("currentTeam");
					while(it.hasNext()){
						String r = it.next().toString();
						
						myFuncpost(r,out,emp,value);
						
						
					} 
					
					
					%>
					
</div>
<br>
<footer style="background-color:#1f1f14; height: 40px;"><font style="color: white"><center>Bravura Solutions Limited</center></font></footer>						
				

</body>
</html>