package compteami;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Graphics;

import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;

public class PageMessagerie extends JPanel implements Config{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private transient Evenement event;
    private transient List<Message> listeMessage;
    private transient Connexion c;
    private transient JButton BoutonMessage;
    private transient JTextArea TextMessage;
    private transient Utilisateur user;


    public PageMessagerie(Evenement event, Connexion c, Utilisateur current_user){
        this.event = event;
        this.listeMessage = new ArrayList<>();
        this.c = c;
        this.user = current_user;

        this.setPreferredSize(new Dimension(LARGEUR_FENETRE/3, HAUTEUR_FENETRE));

        // Chargement des messages contenus dans la base de données
        c.ChargerMessage(event, this);

        TextMessage = new JTextArea();
        TextMessage.setBounds(500, 500, 50, 35);

        this.add(TextMessage);

        BoutonMessage = new JButton("Ajouter message");
        BoutonMessage.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!TextMessage.getText().isEmpty()){
                    Date date = new Date();  
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    String strDate = dateFormat.format(date);  
                    Message mess = new Message(TextMessage.getText(), user.getId(), strDate, user.getNom());
                    AddMessage(mess);
                    c.InsererMessage(event, mess);
                    TextMessage.setText("");
                    repaint();
                }
            }
        });
        this.add(BoutonMessage);
    }


    public void ChargerListeMessage(){
        c.ChargerMessage(event, this);
    }

    public void AddMessage(Message mess){
        this.listeMessage.add(mess);
    }


    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        int i = 50; int j = 50;
        for (Message m: listeMessage){
            g.drawString(m.getMessage(), i, j);
            j+=50;
        }

        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public List<Message> getListeMessage() {
        return listeMessage;
    }

    public void setListeMessage(List<Message> listeMessage) {
        this.listeMessage = listeMessage;
    }
}
