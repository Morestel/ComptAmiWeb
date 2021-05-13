package compteami;

import java.sql.*;
import java.util.ArrayList;
// import java.util.Date;
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
            String requete = "INSERT INTO `utilisateur` (`Prenom`, `Nom`, `Password`, `est_admin`) VALUES (" + 
                                user.getPrenom() + "," + 
                                user.getNom() +  "," +
                                user.getPassword() +  "," + 
                                user.getAdmin() + ")";
            String query = "INSERT INTO Utilisateur (Prenom, Nom, Mail, Password, est_admin) VALUES (?, ?, ?, ?, ?)";

            try(PreparedStatement ps = c.prepareStatement(query);){
                ps.setString(1, user.getPrenom());
                ps.setString(2, user.getNom());
                ps.setString(3, user.getMail());
                ps.setString(4, user.getPassword());
                String ad = String.valueOf(user.getAdmin());
                ps.setString(5, ad);
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
                String prenom = resultat.getString(2);
                String nom = resultat.getString(3);
                String password = resultat.getString(4);
                // Comparaison
                if (prenom.equals(user.getPrenom()) && nom.equals(user.getNom()) && password.equals(user.getPassword())){
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
     * Suppression d'un evenement
     * @param event Evenement a delete
     */
    public void Delete_Event(Evenement event){
        String query = "DELETE FROM Evenement " +
                   "WHERE id = " + event.getId();
        try {
            System.out.println(event.getId());
            ts.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public void UpdateBudget(Evenement event){
        String query = "UPDATE Evenement SET budget = " + event.getBudget() + " WHERE id = " + event.getId();
        try{
            ts.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void ChargerMessage(Evenement event, PageMessagerie pMessagerie){
        String query = "SELECT Contenu, Id_user, Date_envoie, Nom FROM Messagerie, Utilisateur WHERE Id_event = " + event.getId() + " AND Messagerie.Id_user = Utilisateur.Id";
        try(ResultSet resultat = ts.executeQuery(query);){
            while(resultat.next()){
                Message mess = new Message(resultat.getString(1),
                                            Integer.parseInt(resultat.getString(2)),
                                            resultat.getString(3),
                                            resultat.getString(4)
                );
                pMessagerie.AddMessage(mess);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public List<Message> ChargerMessagerie(int id_event){
    	List<Message> m = new ArrayList<>();
    	String query = "SELECT Contenu, Id_user, Date_envoie, Nom FROM Messagerie, Utilisateur WHERE Id_event = " + id_event + " AND Messagerie.Id_user = Utilisateur.Id";
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
    
    public void close() {
    	try {
	    	ts.close();
	    	c.close();
    	}catch(SQLException e) {
    		
    	}
    }
}
