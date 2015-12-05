package model.user;

import model.document.Document;

public interface User {
	
	public String getInput();
	
	public Document pickDocument();
	
	public boolean classify(Document document, String classification);
	
}
