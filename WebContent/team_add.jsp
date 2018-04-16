<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.bravura.gt.*"%>
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
<div style="margin:22px;">
	<h3><i>To add a new team, just provide the new team's name, a member's name who'd be a part of the team and his/her's designation in the team/company. </i> </h3>

</div>
<div style="background-color: #1f1f14; height: 500px; margin-top: -100px;">

<div style="margin:110px; padding: 20px;">

<form action="TeamAdd" method="post" style="margin-top: 70px;">
	<h4><font style="color: white;">Team Name :</font></h4><input class="form-control" id="focusedInput" type="text" name="team" style=" width: 400px;" required>
	<h4><font style="color: white;">Member Name :</font></h4><input class="form-control" id="focusedInput" type="text" name="member" style="width: 400px;" required>
	<h4><font style="color: white;">Designation :</font></h4><input class="form-control" id="focusedInput" type="text" name="des" style="width: 400px;" required>
	<br>
	<input class="btn btn-success" type="submit" name="save" value="Add" style="width: 100px;">
</form>
</div>

</div><br>
<footer style="background-color:#1f1f14; height: 40px;"><font style="color: white"><center>Bravura Solutions Limited</center></font></footer>	
</body>
</html>