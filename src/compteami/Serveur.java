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




@ServerEndpoint("/CompteAmi")
public class Serveur extends Thread{

	static ArrayList<Utilisateur> listeUser = new ArrayList<Utilisateur>();
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
		System.out.println("Client connecté");
		
		if (!lancer) {
			start();
			lancer = true;
		}

	}

	@OnMessage
	public String handleMessage(Session session, String message){ 
		//gerer_requete(message);
		System.out.println(message);
		/*
		if (gerer_requete(message)) {
			try {
				session.getBasicRemote().sendText("true");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		else {
			try {
				
				session.getBasicRemote().sendText("false");
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}*/
		return ""+message;
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
/*
	public boolean gerer_requete(String message) {
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
				user = new Utilisateur(4, pseudo, email, pseudo, 0, password);
				
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
				
				user = new Utilisateur(4, pseudo, pseudo, pseudo, 0, password);
				
				if (c.Authentification(user)) {
					c.close();
					return true;
				}
				else {
					
					c.close();
					return false;
				}
		}
		
		c.close();
		return false;
	}
*/
	
}
