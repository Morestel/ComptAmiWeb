package compteami;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.Date;



public class Fenetre extends JPanel implements Config{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    public static void main(String[] args) {

		
		JFrame fenetre = new JFrame("Compte Entre Amis");
        fenetre.setUndecorated(true);

		fenetre.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
		fenetre.setLocation(POS_X, POS_Y);
		fenetre.setBackground(Color.GRAY);
        fenetre.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
		

        Connexion connect = new Connexion();
		Utilisateur user = new Utilisateur(1, "Paul", "Bertrand", "mail", 0, "BbQDuDim");
        Utilisateur user2 = new Utilisateur(2, "Richard", "Testa", "mail", 0, "Jjkj");
        //connect.Inscription(user);
        System.out.println(connect.Authentification(user2));
        connect.Inscription(user2);

        Evenement event = new Evenement("Manger avec tata michelle", 40, "On mange samedi matin chez tata", new Date(2021, 04, 11), new Date(2021, 05, 11), connect);
        PageEvenement pEvent = new PageEvenement(event, connect, user);

        /*
        Mail email = new Mail("morestelthomas445648456@gmail.com", "Yo", "^^");
        email.envoyer();
        */
        // Remboursement
        // System.out.println(event.getBudget());
        // event.remboursement(12);
        // System.out.println(event.getBudget());
        fenetre.add(pEvent);
        
        // PageMessagerie pMessagerie = new PageMessagerie(event, connect, user);
        // fenetre.add(pMessagerie);

        //PageInscription pInscription = new PageInscription(connect);
        //fenetre.add(pInscription);
        fenetre.pack();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        device.setFullScreenWindow(fenetre);
		fenetre.setVisible(true);
    }

}
