<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import ="java.util.ArrayList"%>
     <%@ page import ="com.bravura.gt.proj.*"%>
<%@ page import ="java.util.List"%>   
<%@ page import ="java.util.Iterator"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<i class="material-icons" style="font-size:48px; color:#0066ff;">cloud</i>
<i class="material-icons" style="font-size:60px;color:#0066ff;">cloud</i>
<% int b =(int) request.getAttribute("a");
	System.out.println(b);
%>



</body>
</html>