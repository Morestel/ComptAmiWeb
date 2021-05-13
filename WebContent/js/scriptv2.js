var ws;

function connectToWebSocket(){
	ws = new WebSocket("ws://localhost:8080/ComptAmiWeb/CompteAmi");
	console.log("connecté");
	ws.onerror = function (error) {
		console.log('WebSocket Error ', error);
		alert('WebSocket Error ', error);
	};
	ws.onclose = function(){
		ws.close();
	}


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

function valider_creerEvent(Id_user){
	var intitule = document.creer_event.intitule.value;
	var description = document.creer_event.description.value;
	var budget = document.creer_event.budget.value;
	var start = document.creer_event.start.value;
	var end = document.creer_event.start.value;
	send_event(intitule, description, budget, start, end, Id_user);
}

function ajouter_participant(id_event){
	var pseudo = document.participant.ajout.value;
	send_participant(pseudo, id_event);
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

function send_event(intitule, description, budget, start, end, id_user){
	var data = {"requete":"creation_event", "intitule":intitule, "description":description, "budget":budget, "start":start, "end":end, "id_user": id_user};
	data = JSON.stringify(data);
	ws.send(data);
}

function send_participant(pseudo, id_event){
	var data = {"requete":"participant","pseudo":pseudo, "event":id_event};
    data = JSON.stringify(data);
    ws.send(data);
}
