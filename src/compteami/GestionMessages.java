package compteami;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionMessages {

	List<Message> listeMessages = new ArrayList<>();
	
	public void addMessage(Message m) {
		listeMessages.add(m);
	}
	
	public List<Message> getAll() {
		return Collections.unmodifiableList(listeMessages);
	}
}
