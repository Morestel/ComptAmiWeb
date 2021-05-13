package compteami;

public class Message {
	private String mess;
	private String pseudo;
	private int user;
	private String date;
	private final int NUM_MESSAGE;
	private static int nbMessages = 0;

	public Message(String message, int id_user, String date, String pseudo) {
		this.mess = message;
		this.user = id_user;
		this.date = date;
		this.pseudo = pseudo;
		NUM_MESSAGE = ++nbMessages;
	}
	public String getMessage() {
		return mess;
	}
	public int getNum() {
		return NUM_MESSAGE;
	}
	public int getUser() {
		return user;
	}
	public String getDate() {
		return date;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	

}

