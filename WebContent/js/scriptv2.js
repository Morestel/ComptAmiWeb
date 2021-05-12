var ws;

function connectToWebSocket(){
	ws = new WebSocket("ws://localhost:8080/ComptAmiWeb/CompteAmi");
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
	ws.onmessage = function (e) {
		console.log(e.data);
	};

}

function send_inscription(pseudo, email, password){
	var data = {"requete":"inscription","pseudo":pseudo,"email":email, "password":password};
    data = JSON.stringify(data);
    ws.send(data);
}