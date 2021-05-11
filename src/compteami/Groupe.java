package compteami;

public class Groupe {
    private Utilisateur[] liste_membre;
    private int nb_membre;
    private final int id;
	private static int nbGroupe = 0;
    private int nbMembre;
    
    public Groupe(Utilisateur createur){
        this.setNb_membre(1);
        this.setListe_membre(null);
        this.id = ++nbGroupe;
        this.setNbMembre(0);
    }

    public int getNbMembre() {
        return nbMembre;
    }

    public void setNbMembre(int nbMembre) {
        this.nbMembre = nbMembre;
    }

    public int getId() {
        return id;
    }

    public int getNb_membre() {
        return nb_membre;
    }

    public void setNb_membre(int nb_membre) {
        this.nb_membre = nb_membre;
    }

    public Utilisateur[] getListe_membre() {
        return liste_membre;
    }

    public void setListe_membre(Utilisateur[] liste_membre) {
        this.liste_membre = liste_membre;
    }

    public void ajout_membre(Utilisateur new_user){
        this.liste_membre[nbMembre] = new_user;
        nb_membre++;
    }
}
