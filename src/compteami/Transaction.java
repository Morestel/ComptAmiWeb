package compteami;

public class Transaction {
    private int montant;
    private Utilisateur user;
    private Evenement event;

    public Transaction(int montant, Evenement event){
        this.montant = montant;
        this.event = event;
    }
}
