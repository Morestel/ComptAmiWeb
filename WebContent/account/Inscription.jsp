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
	<form onsubmit="valider()" name ="inscription" method="get" class="box_form" >
      <label for="pseudo">PSEUDO</label><input type="text" name="pseudo" id="pseudo" pattern="\w*" title="Les caractères spéciaux sauf '_' ne sont pas acceptés">
 	  <label for"email">EMAIL</label><input type="text" name="email" id="email" required></label>
      <label for="password">MOT DE PASSE</label><input type="password" name="password" id="password" required>
      <label for="password_confirm">CONFIRMER LE MOT DE PASSE</label><input type="password" name="password_confirm" id="password_confirm" required>
    
      <input type="submit" class="bouton_valid" title="Valider" value="Valider">
      <a href="connexion.php">Vous avez déja un compte ?</a>
      </form>
      
      
  
</body>
<script type="text/javascript" src="../js/script.js"></script>
<script>
var ws = null;


function connectToWebSocket(){
	ws = new WebSocket("ws://localhost:8080/ComptAmiWeb/CompteAmi");
	console.log("connecté");
	ws.onclose = function(){
	    setTimeout(function(){start("ws://localhost:8080/ComptAmiWeb/CompteAmi")}, 5000);
	};
	ws.onclose = function(){
        setTimeout(connectToWebSocket, 1000);
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

function send_inscription(pseudo, email, password){
	var data = {"requete":"inscription","pseudo":pseudo,"email":email, "password":password};
    data = JSON.stringify(data);
    ws.send(data);

}

</script>

</html>