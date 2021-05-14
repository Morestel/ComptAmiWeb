package compteami;

import java.io.IOException;

import java.util.ArrayList;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;
import java.sql.Date;




@ServerEndpoint("/CompteAmi")
public class Serveur extends Thread{

	static ArrayList<String> listePseudo = new ArrayList<>();
	static ArrayList<Session> listeSession = new ArrayList<Session>();
	static boolean lancer = false;
	
	public static ArrayList<Session> getListeSession() {
		return Serveur.listeSession;
	}
	
		
	public void run() {
		final int PORT = 8001;
		try {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("En attende de client...");
        while(true){
            Socket s = server.accept();
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream());
                String ligne;
                ligne = in.readLine();
                //gerer_requete(ligne);
            }catch(IOException e){}
            s.close();
		
	}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public synchronized void handleOpen(Session s) {
		listeSession.add(s);
		Connexion c = new Connexion();
		listePseudo = c.ChargerPseudo();
		System.out.println("Client connecté");
		
		if (!lancer) {
			start();
			lancer = true;
		}

	}
	

	@OnMessage
	public String handleMessage(Session session, String message) {
		JSONObject obj = new JSONObject();
		System.out.println("Message reçu :" + message);
		
		Connexion c = new Connexion();
		JSONObject liste = new JSONObject(message);
		String requete = liste.getString("requete");
		
		String pseudo;
		String password;
		String email;
		Utilisateur user;
		int id_evt;
		int usr;
		
		switch(requete) {
			case "inscription":
				password = liste.getString("password").toString();
				pseudo = liste.getString("pseudo").toString();
				email = liste.getString("email").toString();
				user = new Utilisateur(4, pseudo, email, 0, password);
				
				if (c.Inscription(user)) {
					
					obj.put("connexion", true);
					obj.put("id", c.trouverId(pseudo));
				}
				else {
					obj.put("connexion", false);
				}
				
			case "connexion":
				
				pseudo = liste.getString("pseudo").toString();
				password = liste.getString("password").toString();
				user = new Utilisateur(4, pseudo, pseudo, 0, password);
				
				if (c.Authentification(user)) {
					
					obj.put("reponse", "connexion");
					obj.put("connexion", true);
					obj.put("id", c.trouverId(pseudo));
				}
				else {
					obj.put("reponse", "connexion");
					obj.put("connexion", false);
				}
				break;
				
			case "creation_event":
				
				String intitule = liste.getString("intitule");
				String description = liste.getString("description");
				int budget = Integer.parseInt(liste.getString("budget").toString());
				Date start = Date.valueOf(liste.getString("start").toString());
				Date end = Date.valueOf(liste.getString("end").toString());
				String id_user = liste.getString("id_user");
				Evenement e = new Evenement(intitule, budget, description, start, end, c);
				c.Participe(id_user, e);
				obj.put("reponse", "creation_event");
				obj.put("creation_event", true);
				break;
				
			case "participant":
				pseudo = liste.getString("pseudo").toString();
				// Vérification de son existence
				boolean trouve = false;
				int i = 0;
				
				while (!trouve && i < listePseudo.size()) {
					if (listePseudo.get(i).equals(pseudo)) {
						trouve = true;
					}
					i++;
				}
				if (!trouve) {
					obj.put("reponse", "participe");
					obj.put("participe", false);
					return String.valueOf(obj);
				}
				int id = c.trouverId(pseudo);
				String id_event = String.valueOf(liste.getInt("event"));
				
				c.Participe(id, id_event);
				obj.put("reponse", "participe");
				obj.put("participe", true);
				break;
				
			case "budget":
				int event = liste.getInt("id_event");
				int budget2 = c.Budget(event);
				obj.put("reponse", "budget");
				obj.put("budget", budget2);
				break;
				
			case "message":
				usr = Integer.parseInt(liste.getString("id_user").toString());
				id_evt = Integer.parseInt(liste.getString("id_event").toString());
				String texte = liste.getString("texte");
				c.InsererMessage(id_evt, usr, texte);
				obj.put("reponse", "message");
				break;
				
			case "transaction":
				int montant = liste.getInt("montant");
				id_evt = Integer.parseInt(liste.getString("id_event").toString());
				usr = Integer.parseInt(liste.getString("id_user").toString());
				int montant_final = c.Transaction(id_evt, usr, montant);
				obj.put("reponse", "transaction");
				if (montant_final == -1) {
					obj.put("validite", false);
				}
				else {
					obj.put("validite", true);
					obj.put("montant_final", montant_final);
				}
				break;
		}
		
		c.close();
		return String.valueOf(obj);
	}
	
	public String handlessage(Session session, String message){ 
		Connexion c = new Connexion();
		JSONObject liste = new JSONObject(message);
		String requete = liste.getString("requete");
		
		String pseudo;
		String password;
		String email;
		Utilisateur user;
		switch(requete) {
			case "inscription":
				password = liste.getString("password").toString();
				pseudo = liste.getString("pseudo").toString();
				email = liste.getString("email").toString();
				user = new Utilisateur(4, pseudo, email, 0, password);
				
				if (c.Inscription(user)) {
					c.close();
					return "ok";
				}
				else {
					c.close();
					return "pasok";
				}
				
			case "connexion":
				
				pseudo = liste.getString("pseudo").toString();
				password = liste.getString("password").toString();
				
				user = new Utilisateur(4, pseudo, pseudo, 0, password);
				
				if (c.Authentification(user)) {
					c.close();
					return "ok";
				}
				else {
					
					c.close();
					return "pasok";
				}
				
			case "creation_event":
				String intitule = liste.getString("intitule");
				String description = liste.getString("description");
				int budget = Integer.parseInt(liste.getString("budget").toString());
				Date start = Date.valueOf(liste.getString("start").toString());
				Date end = Date.valueOf(liste.getString("end").toString());
				String id_user =String.valueOf(liste.getInt("id_user"));
				
				c.Participe(id_user, new Evenement(intitule, budget, description, start, end, c));
				
				c.close();
				return "ok";
				
			case "participant":
				pseudo = liste.getString("pseudo").toString();
				// Vérification de son existence
				boolean trouve = false;
				int i = 0;
				
				while (!trouve && i < listePseudo.size()) {
					if (listePseudo.get(i).equals(pseudo)) {
						trouve = true;
					}
					i++;
				}
				if (!trouve) {
					return "pasok";
				}
				int id = c.trouverId(pseudo);
				String id_event = String.valueOf(liste.getInt("event"));
				
				c.Participe(id, id_event);
				c.close();
				return "ok";
				
			case "budget":
				int event = liste.getInt("id_event");
				int budget2 = c.Budget(event);
				c.close();
				return "" + budget2;
		}
		
		c.close();
		return "";
	}	
	
	@OnClose
	public void handleClose(Session s) {
		System.out.println("Client deconnecté");
		for (int i = 0; i < listeSession.size(); i++) {
			if (s.equals(listeSession.get(i))) {
				listeSession.remove(i);
			}
		}
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

	public boolean gerer_requete(Session session, String message) {
		System.out.println("Requete reçu");
		System.out.println(message);
		Connexion c = new Connexion();
		JSONObject liste = new JSONObject(message);
		String requete = liste.getString("requete");
		
		String pseudo;
		String password;
		String email;
		Utilisateur user;
		switch(requete) {
			case "inscription":
				password = liste.getString("password").toString();
				pseudo = liste.getString("pseudo").toString();
				email = liste.getString("email").toString();
				user = new Utilisateur(4, pseudo, email, 0, password);
				
				if (c.Inscription(user)) {
					c.close();
					return true;
				}
				else {
					c.close();
					return false;
				}
				
			case "connexion":
				
				pseudo = liste.getString("pseudo").toString();
				password = liste.getString("password").toString();
				
				user = new Utilisateur(4, pseudo, pseudo, 0, password);
				
				if (c.Authentification(user)) {
					c.close();
					return true;
				}
				else {
					
					c.close();
					return false;
				}
				
			case "creation_event":
				String intitule = liste.getString("intitule");
				String description = liste.getString("description");
				int budget = Integer.parseInt(liste.getString("budget").toString());
				Date start = Date.valueOf(liste.getString("start").toString());
				Date end = Date.valueOf(liste.getString("end").toString());
				String id_user =String.valueOf(liste.getInt("id_user"));
				
				c.Participe(id_user, new Evenement(intitule, budget, description, start, end, c));
				
				c.close();
				return true;
				
			case "participant":
				pseudo = liste.getString("pseudo").toString();
				// Vérification de son existence
				boolean trouve = false;
				int i = 0;
				
				while (!trouve && i < listePseudo.size()) {
					if (listePseudo.get(i).equals(pseudo)) {
						trouve = true;
					}
					i++;
				}
				if (!trouve) {
					return false;
				}
				int id = c.trouverId(pseudo);
				String id_event = String.valueOf(liste.getInt("event"));
				
				c.Participe(id, id_event);
				c.close();
				return true;
				
			case "budget":
				int event = liste.getInt("id_event");
				int budget2 = c.Budget(event);
				try {
					session.getBasicRemote().sendText(String.valueOf(budget2));
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
		c.close();
		return false;
	}	
}
