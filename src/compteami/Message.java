package compteami;

public class Message {
	private String mess;
	private int user;
	private String date;
	private final int NUM_MESSAGE;
	private static int nbMessages = 0;

	public Message(String message, int id_user, String date) {
		this.mess = message;
		this.user = id_user;
		this.date = date;
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
	

}

