
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="compteami.GestionMessages" %>
    
    <!-- UseBean -->
   	<jsp:useBean id="listeMessage" class="compteami.GestionMessages" scope="page"></jsp:useBean>
   	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evenement</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>

<c:import url="header.html" />
<%! String id_evenement; %>
 
 
	<jsp:useBean id="listMessage" class="compteami.GestionMessages" ></jsp:useBean>
	<c:if test="${ not empty param.event}">
	<c:set target="${listMessage}" property="id_event" value="${ param.event }" />
	<% 
	listMessage.loadMessage(listMessage.getId_event());
	%>
	</c:if>
	<c:if test="${empty param.event }">
	<p>Y'a r</p>
	</c:if>
  
  



<c:import url="footer.html" />

</body>
</html>