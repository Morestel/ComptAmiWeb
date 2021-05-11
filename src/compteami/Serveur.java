package compteami;

import java.io.IOException;

import java.util.ArrayList;
import javax.swing.JFrame;
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
	
	
	public static void main(String[] args) throws IOException{
		final int PORT = 8001;
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("En attende de client...");
        while(true){
            Socket s = server.accept();
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream());
                String ligne;
                ligne = in.readLine();
                System.out.println(ligne);
            }catch(IOException e){}
            s.close();
		
	}
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
                gerer_requete(ligne);
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
		try {
			s.getBasicRemote().sendText("Changement");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!lancer) {
			start();
			lancer = true;
		}

	}

	@OnMessage
	public String handleMessage(Session session, String message){ 
		
		gerer_requete(message);
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

	public void gerer_requete(String message) {
		System.out.println("Requete reçu");
		System.out.println(message);
		Connexion c = new Connexion();
		JSONObject liste = new JSONObject(message);
		String requete = liste.getString("requete");
		switch(requete) {
			case "inscription":
				String password = liste.getString("password").toString();
				String pseudo = liste.getString("pseudo").toString();
				String email = liste.getString("email").toString();
				Utilisateur user = new Utilisateur(4, pseudo, email, pseudo, 0, password);
				
				c.Inscription(user);
				break;
		}
		
		c.close();
	}

	
}
