package compteami;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Connexion {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    private final String JDBC_CONNEXION = "jdbc:mysql://localhost:4456/mt05697s";
    private final String JDBC_USER = "mt05697s";
    private final String JDBC_PWD = "3RSHLEL7";
    
    /*
    private final String JDBC_CONNEXION = "jdbc:mysql://localhost/mydb";
    private final String JDBC_USER = "root";
    private final String JDBC_PWD = "";
    */
    Connection c;
    private Statement ts;

    public Connexion() {
    	
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Java Database Connectivity a ete trouve");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection(JDBC_CONNEXION, JDBC_USER, JDBC_PWD);
            System.out.println("Connexion etablie");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ts = c.createStatement();
            System.out.println("Statement cree");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction d'inscription d'un utilisateur (Les informations seront verifiees anterieurement)
     * @param user Utilisateur a inscrire
     */
    public boolean Inscription(Utilisateur user){
        if (!this.Authentification(user)){ // On empêche que deux personnes ayant les mêmes infos s'inscrivent 

            String query = "INSERT INTO Utilisateur (Pseudo, Mail, Password, est_admin) VALUES ( ?, ?, ?, ?)";

            try(PreparedStatement ps = c.prepareStatement(query);){
           
                ps.setString(1, user.getPseudo());
                ps.setString(2, user.getMail());
                ps.setString(3, user.getPassword());
                String ad = String.valueOf(user.getAdmin());
                ps.setString(4, ad);
                ps.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    /**
     * Fonction d'authentification d'un utilisateur, vérifie que toutes les données fournies soient juste
     * @param user Utilisateur souhaitant s'authentifier
     */
    public boolean Authentification(Utilisateur user){
        String query = "SELECT * FROM Utilisateur";
        try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){ 
                // Stockage des informations
                String pseudo = resultat.getString(2);
                String password = resultat.getString(4);
               
                // Comparaison
                if (pseudo.equals(user.getPseudo()) && password.equals(user.getPassword())){
                    return true; // On a trouvé une correspondance
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return false;
    }

    /**
     * Implementation de l'evenement dans la bdd
     * @param e Evenement a insere
     */
    public void Creer_Event(Evenement e){
            String query = "INSERT INTO Evenement (Intitule, Description, Budget, Start, End) VALUES (?, ?, ?, ?, ?)";
            
            try(PreparedStatement ps = c.prepareStatement(query);){
                ps.setString(1, e.getIntitule());
                ps.setString(2, e.getTexte());
                String ad = String.valueOf(e.getBudget());
                ps.setString(3, ad);
                ps.setString(4, e.getStart().toString());
                ps.setString(5, e.getEnd().toString());
                ps.executeUpdate();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
    }
    
    /**
     * Ajoute un participant � un �v�nement
     * @param id_user Utilisateur qui participe
     * @param e Evenement auquel il participe
     */
    public void Participe(String id_user, Evenement e){
        String query = "INSERT INTO Participe (Id_event, Id_user) VALUES (?, ?)";
        try(PreparedStatement ps = c.prepareStatement(query);){
            String Id_event = String.valueOf(e.getId());
            String Id_user = String.valueOf(id_user);
            ps.setString(1, Id_event);
            ps.setString(2, Id_user);
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    /**
     * Insertion dans table participe
     * @param id_user 
     * @param id_event
     */
    public void Participe(int id_user, String id_event) {
    	String query = "INSERT INTO Participe (Id_event, Id_user) VALUES (?, ?)";
        try(PreparedStatement ps = c.prepareStatement(query);){
            ps.setString(1, id_event);
            ps.setInt(2, id_user);
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    	
    }
    
    /**
     * Indique si un utilisateur participe � un �v�nement
     * @param id_user
     * @param id_event
     * @return
     */
    public boolean Participation(int id_user, int id_event) {
    	String query = "SELECT * FROM Participe WHERE Id_user = '"+ id_user + "' " + "AND Id_event = '" + id_event + "'"; 
    	try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){
            	return true;
            }
        }catch(SQLException e){
        	e.printStackTrace();    
        }
    	return false;
    }
    /**
     * Trouve id en fonction d'un pseudo
     * @param pseudo
     * @return id � retourner
     */
    public int trouverId(String pseudo) {
    	String query = "SELECT Id FROM Utilisateur WHERE pseudo = '"+ pseudo + "' "; 
    	try(ResultSet resultat = ts.executeQuery(query);){
            int id = -1;
            
            while(resultat.next()){
            	id = Integer.parseInt(resultat.getString(1));
            }
            return id;
        }catch(SQLException e){
        	e.printStackTrace();
        	return -1;
            
        }
    }

    /**
     * Supprime un evenement
     * @param event a delete
     */
    public void Delete_Event(Evenement event){
        String query = "DELETE FROM Evenement " +
                   "WHERE id = " + event.getId();
        try {
            ts.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Suppression d'un evenement
     * @param id_event Evenement a delete
     */
    public void Delete_Event(int id_event){
        String query = "DELETE FROM Evenement " +
                   "WHERE id = " + id_event;
        try {
            ts.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param event
     * @return id evenement
     */
    public int RetournerIdEvent(Evenement event){
        String query = "SELECT Id FROM Evenement WHERE Intitule = '" + event.getIntitule() + "' " +
                        "AND Description = '" + event.getTexte() + "' " +
                        "AND Budget = '" + event.getBudget() + "' " +
                        "AND Start = '" + event.getStart() + "' " +
                        "AND End = '" + event.getEnd() + "'";

        try(ResultSet resultat = ts.executeQuery(query);){
            int id = 0;
            while(resultat.next()){ 
                id = Integer.parseInt(resultat.getString(1));
            }
            return id;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Modifie le budget dans bdd
     * @param event
     */
    public void UpdateBudget(Evenement event){
        String query = "UPDATE Evenement SET budget = " + event.getBudget() + " WHERE id = " + event.getId();
        try{
            ts.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Charge une messagerie
     * @param id_event
     * @return Liste de message
     */
    public List<Message> ChargerMessagerie(int id_event){
    	List<Message> m = new ArrayList<>();
    	String query = "SELECT Contenu, Id_user, Date_envoie, Pseudo FROM Messagerie, Utilisateur WHERE Id_event = " + id_event + " AND Messagerie.Id_user = Utilisateur.Id";
        try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){
                Message mess = new Message(resultat.getString(1),
                                            Integer.parseInt(resultat.getString(2)),
                                            resultat.getString(3),
                                            resultat.getString(4)
                );
                m.add(mess);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    	return m;
    	
    }
    
    /**
     * Charge tous les pseudos dans une arraylist
     * @return L'arraylist de tous les pseudos
     */
    public ArrayList<String> ChargerPseudo(){
    	ArrayList<String> m = new ArrayList<>();
    	String query = "SELECT Pseudo FROM Utilisateur";
        try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){
                String ps = resultat.getString(1);
                m.add(ps);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    	return m;
    }

    /**
     * Charge des �v�nements dans une arraylist
     * @return
     */
    public ArrayList<Evenement> ChargerEvent(){
    	ArrayList<Evenement> e = new ArrayList<>();
    	String query = "SELECT * FROM Evenement";
    	try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){                
            	String intitule = resultat.getString(2);
                String texte = resultat.getString(3);
                int montant = resultat.getInt(4);
                java.sql.Date start = resultat.getDate(5);
                java.sql.Date end = resultat.getDate(6);
                Evenement event = new Evenement(intitule, montant, texte, start, end);
                event.setId(resultat.getInt(1));
                e.add(event);
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }	
    	return e;
    }
    
    /**
     * Charge liste user
     * @return
     */
    public ArrayList<Utilisateur> ChargerUtilisateur(){
    	ArrayList<Utilisateur> u = new ArrayList<>();
    	String query = "SELECT Id, Pseudo, Password, est_admin, Mail FROM Utilisateur";
    	try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){                
            	String pseudo = resultat.getString(2);
            	String password = resultat.getString(3);
                int admin = resultat.getInt(4);
                String mail = resultat.getString(5);
                Utilisateur user = new Utilisateur(pseudo, mail, admin, password);
                user.setId(resultat.getInt(1));
                u.add(user);
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }	
    	return u;
    }
    
    /**
     * Ins�re un message dans un �v�nement
     * @param event
     * @param mess
     */
    
    public void InsererMessage(Evenement event, Message mess){
        String query = "INSERT INTO Messagerie (Contenu, Date_envoie, Id_event, Id_user) VALUES (?, ?, ?, ?)";
        try(PreparedStatement ps = c.prepareStatement(query);){

            ps.setString(1, mess.getMessage());
            ps.setString(2, mess.getDate());
            String Id_event = String.valueOf(event.getId());
            ps.setString(3, Id_event);
            String Id_user = String.valueOf(mess.getUser());
            ps.setString(4, Id_user);
           
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void InsererMessage(int id_event, int id_user, String texte) {
    	String query = "INSERT INTO Messagerie (Contenu, Date_envoie, Id_event, Id_user) VALUES (?, ?, ?, ?)";
    	Date date = new Date();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String strDate = dateFormat.format(date);  
    	try(PreparedStatement ps = c.prepareStatement(query);){
    		ps.setString(1, texte);
    		ps.setString(2, strDate);
    		ps.setInt(3,  id_event);
    		ps.setInt(4, id_user);
    		
    		ps.executeUpdate();
    	}catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void close() {
    	try {
	    	ts.close();
	    	c.close();
    	}catch(SQLException e) {
    		
    	}
    }
    
    /**
     * Renvoie le budget
     * @param id_event
     * @return
     */
    public int Budget(int id_event) {
    	String query = "SELECT budget FROM Evenement WHERE Id = " + id_event;
    	try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){
                int bd = resultat.getInt(1);
                return bd;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    	return -1;
    }
    
    /**
     * Etabli une transaction
     * @param id_event
     * @param id_user
     * @param montant
     * @return
     */
    public int Transaction(int id_event, int id_user, int montant) {
    	String query = "INSERT INTO Transaction (Date_transaction, Montant, Id_event, Id_user) VALUES (?, ?, ?, ?)";
    	int montant_final = Budget(id_event) - montant;
    	if (montant_final < 0) {
    		return -1;
    	}
    	Date date = new Date();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String strDate = dateFormat.format(date);  
    	 try(PreparedStatement ps = c.prepareStatement(query);){

             ps.setString(1, strDate);
             ps.setInt(2, montant);
             ps.setInt(3, id_event);
             ps.setInt(4, id_user);
            
             ps.executeUpdate();
         }catch(SQLException ex){
             ex.printStackTrace();
         }
    	 
    	 String query2 = "UPDATE Evenement SET budget = " + montant_final + " WHERE id = " + id_event;
    	 try{
             ts.executeUpdate(query2);
         }catch(SQLException e){
             e.printStackTrace();
         }
    	return montant_final;
    }
}
