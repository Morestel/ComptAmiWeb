 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
    <%@page import="compteami.GestionMessages" %>
    <%@page import="compteami.Message" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>

<%	String raison = (String) session.getAttribute("raison"); %>

<c:import url="header.html" />

<div class="main">
      <div class="main_content">
      		<div class="erreur">
				${raison}
			</div>
		</div>
</div>
<c:import url="footer.html" />

</body>
</html>