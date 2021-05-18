package compteami;
import javax.mail.internet.*; 
import java.util.Properties;  
import javax.mail.*;  

public class Mail {
    private String destinataire;
    private String titre;
    private String message;


    public Mail(String destinataire, String titre, String message){
        this.destinataire = destinataire;
        this.titre = titre;
        this.message = message;
    }

    /**
     * Envoie un mail
     */
    public void envoyer(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.ssl.checkserveridentity", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session s = Session.getDefaultInstance(props, 
        new javax.mail.Authenticator() {
            @Override
        protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(Config.MAIL, Config.MOT_DE_PASSE);
        }
      });

      try {
        MimeMessage m = new MimeMessage(s);
        m.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(this.getDestinataire()));
        m.setSubject(this.getTitre());
        m.setText(this.getMessage());
        //envoyer le message
        Transport.send(m);
        System.out.println("Message envoyé avec succès");
      } catch (MessagingException e) {
        e.printStackTrace();
      }
    }

    public String getDestinataire(){
        return this.destinataire;
    }

    public String getMessage(){
        return this.message;
    }

    public String getTitre(){
        return this.titre;
    }
}
