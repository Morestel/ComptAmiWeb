package compteami;


public class Utilisateur{
    private int id;
    private String pseudo;
    private int est_admin;
    private String password;
    private String mail;
    
    public Utilisateur(int id, String pseudo, String mail, int admin, String password) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setMail(mail);
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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
