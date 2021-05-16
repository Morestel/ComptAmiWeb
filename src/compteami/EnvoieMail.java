package compteami;

import java.util.ArrayList;
import java.sql.*;
import java.util.*;

public class EnvoieMail implements Runnable{

	
	public EnvoieMail() {
		super();
	}
	
	@Override
	public void run() {
		String titre = "[COMPTAMI] Relance - Evenement toujours impay�";
		while(true) {
			ArrayList<Utilisateur> util = new ArrayList<>();
			ArrayList<Evenement> event = new ArrayList<>();
			Connexion c = new Connexion();
			
			// Chargement des listes
			util = c.ChargerUtilisateur();
			event = c.ChargerEvent();
			
			// Pour tous les �v�nements on compare les dates
			for (Evenement e : event) {
				java.sql.Date date_fin = e.getEnd();
				java.sql.Date date_jour = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				if (date_fin.before(date_jour) && e.getBudget() != 0) { // Date fin d'�v�nement d�pass�e
					String message = "Bonjour, nous vous rappelons qu'un �v�nement auquel vous participez n'est pas fini d'�tre rembourser. Montant restant = " + e.getBudget() + "�.";
					for (Utilisateur u : util) {
						if (c.Participation(u.getId(), e.getId())) { // Il participe
							Mail m = new Mail(u.getMail(), titre, message);
							m.envoyer();
						}
					}
				}

			}
			c.close();
			int une_heure = 60000 * 60;
			try {
		        Thread.sleep(une_heure);
		    }
		    catch(InterruptedException e) {
		        e.printStackTrace();
		    }
		}
	}
	
}
