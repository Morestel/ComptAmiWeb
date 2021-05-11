package compteami;

import java.sql.Date;
import java.util.*;


import javax.mail.internet.*;
import javax.mail.*;

public class Evenement {
    private final int id;
    private String intitule;
    private int budget;
    private GestionMessages gestionnaire_message;
    private int nbEvenement = 0;
    private String texte;
    private Date start;
    private Date end;
    private Connexion c;
    
    public Evenement(String intitule, int montant, String texte, Date start, Date end, Connexion c){
        this.setBudget(montant);
        this.texte = texte;
        this.intitule = intitule;
        this.start = start;
        this.end = end;
        this.c = c;
        c.Creer_Event(this);
        this.id = c.RetournerIdEvent(this);
    }

    /**
     * L'utilisateur paye afin de participer à l'évènement
     * @param montant Montant qu'il a payé
     */
    public synchronized void remboursement(int montant){
        setBudget(getBudget() - montant);
        this.getC().UpdateBudget(this);
    }

    public GestionMessages getGestionnaire_message() {
        return gestionnaire_message;
    }

    public void setGestionnaire_message(GestionMessages gestionnaire_message) {
        this.gestionnaire_message = gestionnaire_message;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public String getTexte(){
        return this.texte;
    }

    public void setTexte(String texte){
        this.texte = texte;
    }

    public Connexion getC() {
        return c;
    }

    public void setC(Connexion c) {
        this.c = c;
    }

}
