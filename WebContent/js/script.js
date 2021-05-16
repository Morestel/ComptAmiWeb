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
			
		case "connexion":
			if (obj.connexion == true){
				document.querySelector("#id_pseudo").value = obj.id;
				alert("Valide");
				
			}
			else{
				alert("Erreur saisie, veuillez recommencer");
			}
			break
			
		case "message":
			location.reload();
			break;
			
		case "participe":
			if (obj.participe == false){
				alert("Utilisateur inconnu");
			}
			break;
			
		case "creation_event":
			if (obj.creation_event == true){
				alert("Event créé");
				setTimeout(function(){
				document.location.href="http://localhost:8080/ComptAmiWeb/"; 
				}, 2000);
			}
			break;
			
		case "transaction":
			if (obj.validite == false){
				alert("Erreur lors du paiement");
			}
			else{
				location.reload();
			}
			break;
		default:
			
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
	var end = document.creer_event.end.value;
	send_event(intitule, description, budget, start, end, Id_user);
	recup_serv();
}

function ajouter_participant(id_event){
	var pseudo = document.participant.ajout.value;
	send_participant(pseudo, id_event);
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


function ajouter_message(id_user, id_event){
	var texte = document.envoieMess.messageArea.value;
	console.log(id_event);
	console.log(id_user);
	send_message(id_user, id_event, texte);

	recup_serv();
}

function retirer(id_event, id_user){
	var a_payer = document.querySelector("#payer").value;
	send_payer(a_payer, id_event, id_user);
	recup_serv();
}

function delete_event(id_event){
	send_delete(id_event);
	location.reload();
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

function send_message(id_user, id_event, texte){
	var data = {"requete":"message", "id_user":id_user, "id_event":id_event, "texte":texte};
    data = JSON.stringify(data);
    ws.send(data);
}

function send_payer(a_payer, id_event, id_user){
	var data = {"requete":"transaction", "id_event":id_event, "id_user":id_user, "montant":a_payer};
	data = JSON.stringify(data);
	ws.send(data);
}	

function send_delete(id_event){
	var data = {"requete":"delete", "id_event":id_event};
	data = JSON.stringify(data);
	ws.send(data);
}

