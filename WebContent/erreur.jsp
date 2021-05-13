 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<c:import url="header.html" />
<%	String raison = (String) session.getAttribute("raison"); %>
${raison}

<c:import url="footer.html" />

</body>
</html>