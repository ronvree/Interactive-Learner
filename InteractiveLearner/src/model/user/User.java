package model.user;

import java.io.File;

import model.document.Document;

public interface User {
	
	public String getInput();
	
	public Document pickDocument();
	
	public File pickFile();
	
	public boolean classify(Document document, String classification);
	
}
