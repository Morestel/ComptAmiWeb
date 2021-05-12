package compteami;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionMessages {
	String id_event;
	List<Message> listeMessages = new ArrayList<>();
	
	public void addMessage(Message m) {
		listeMessages.add(m);
	}
	
	public List<Message> getAll() {
		return Collections.unmodifiableList(listeMessages);
	}
	
	
	public void loadMessage(String id_event) {
		int event = Integer.parseInt(id_event);
		Connexion c = new Connexion();
		c.ChargerMessagerie(event);
	}

	public String getId_event() {
		return id_event;
	}

	public void setId_event(String id_event) {
		this.id_event = id_event;
	}
}
