<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>
<c:import url="../header.html" />
<div class="login-box">
    <h2>Connexion</h2>
    <form method="post" name="connexion" onsubmit="valider_connexion()">
   	<div class="user-box">
    <input type="text" name="pseudo" id="pseudo" pattern="\w*" title="Les caractères spéciaux sauf '_' ne sont pas acceptés" value="" required><label for="pseudo">PSEUDO</label></div>
  
    <div class="user-box"><input type="password" name="password" id="password"  minlength="6" title="6 caractère minimum" required><label for="password">MOT DE PASSE</label></div>
     
   
    <input type="submit" class="bouton_valid" title="Connexion" value="Se connecter">
    </form>
    <a class="alt_co" href="https://mira2.univ-st-etienne.fr/~rg06871s/projet/account?auth=signup">Créer un compte</a></div>
</body>

<script>
var ws;

function connectToWebSocket(){
	ws = new WebSocket("ws://" + window.location.host +"/ComptAmiWeb/CompteAmi");
	console.log("connecté");
	ws.onerror = function (error) {
		console.log('WebSocket Error ', error);
		alert('WebSocket Error ', error);
	};
	

}

connectToWebSocket();


function valider(){
	var pseudo = document.inscription.pseudo.value;
	var email = document.inscription.email.value;
	var password = document.inscription.password.value;
	var confirm_password = document.inscription.password_confirm.value;
	
	if (pseudo.length < 3){
		alert("Pseudo trop court");
	}
	
	if (password != confirm_password){
		alert("Password non égal (gros con)");
	}
	if (!email.match(/[a-z0-9_\-\.]+@[a-z0-9_\-\.]+\.[a-z]+/i)) {
	    alert(email + " n'est pas une adresse valide");
	}
	send_inscription(pseudo, email, password);
}

function valider_connexion(){
	var pseudo = document.connexion.pseudo.value;
	var password = document.connexion.password.value;
	send_connexion(pseudo, password);
}


function send_connexion(pseudo, password){
	var data = {"requete":"connexion", "pseudo":pseudo, "password":password};
	data = JSON.stringify(data);
	ws.send(data);
	/*
	ws.onmessage = function (e) {
		console.log(e.data);
	};*/

}

function send_inscription(pseudo, email, password){
	var data = {"requete":"inscription","pseudo":pseudo,"email":email, "password":password};
    data = JSON.stringify(data);
    ws.send(data);
}
</script>
</html>