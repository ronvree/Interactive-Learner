package model.user;

import model.Classification;
import model.Document;

public interface User {
	
	public String getInput();
	
	public Document pickDocument();
	
	public boolean classify(Document document, Classification classification);
	
}
