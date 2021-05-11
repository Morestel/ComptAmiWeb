package compteami;


public class Utilisateur{
    private int id;
    private String nom;
    private String prenom;
    private int est_admin;
    private String password;
    private String mail;
    
    public Utilisateur(int id, String nom, String mail, String prenom, int admin, String password) {
        this.setId(id);
        this.setNom(nom);
        this.setMail(mail);
        this.setPrenom(prenom);
        this.setAdmin(admin);
        this.setPassword(password);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin(){
        return this.est_admin;
    }

    public void setAdmin(int admin){
        this.est_admin = admin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
