var ws;

function connectToWebSocket(){
	ws = new WebSocket(`ws://localhost:8080/ComptAmiWeb/CompteAmi`);
	console.log("connecté");
	ws.onerror = function (error) {
		console.log('WebSocket Error ', error);
		alert('WebSocket Error ', error);
	};
	ws.onclose = function(){
		ws.close();
	}
	ws.onmessage = function (e) {
		var obj = JSON.parse(e.data);
		console.log(e.data);
		console.log(obj.reponse);
		
		switch(obj.reponse){
		case "budget":
			var bd = document.querySelector("#aff_budget").innerHTML += obj.budget;
			break;
			
		case "connexion":
		console.log(obj.connexion);
			if (obj.connexion == true){
				document.querySelector("#id_pseudo").value = obj.id;
				alert("Valide");
				
			}
			else{
				alert("Erreur saisie, veuillez recommencer");
			}
			break
		default:
			console.log("Connait pas");
			break;
		}
		
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
	
	else if (password != confirm_password){
		alert("Non concordence entre mdp");
	}
	else if (!email.match(/[a-z0-9_\-\.]+@[a-z0-9_\-\.]+\.[a-z]+/i)) {
	    alert(email + " n'est pas une adresse valide");
	}
	else{
		send_inscription(pseudo, email, password);
		recup_serv();
	}
}

function valider_connexion(){
	var pseudo = document.connexion.pseudo.value;
	var password = document.connexion.password.value;
	console.log(pseudo);
	console.log(password);
	send_connexion(pseudo, password);
	recup_serv();
}

function valider_creerEvent(Id_user){
	var intitule = document.creer_event.intitule.value;
	var description = document.creer_event.description.value;
	var budget = document.creer_event.budget.value;
	var start = document.creer_event.start.value;
	var end = document.creer_event.start.value;
	send_event(intitule, description, budget, start, end, Id_user);
	recup_serv();
}

function ajouter_participant(id_event){
	var pseudo = document.participant.ajout.value;
	send_participant(pseudo, id_event);
	recup_serv();
}

function demander_budget(id_event){
	send_id_event(id_event);
	recup_serv();
}

async function recup_serv(){
	try{
		let response = await fetch(document.location.href);
		console.log(response.status);
		if (response.status === 200){
			let data = await response.text();
		}
		}catch(err){
			console.log(err);
		}
}

function send_connexion(pseudo, password){
	var data = {"requete":"connexion", "pseudo":pseudo, "password":password};
	data = JSON.stringify(data);
	console.log(data);
	ws.send(data);
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

function send_id_event(id_event){
	var data = {"requete":"budget","id_event":id_event};
    data = JSON.stringify(data);
    ws.onopen = () => ws.send(data);
}
