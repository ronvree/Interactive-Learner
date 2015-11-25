package model.documentprocessor;

import model.Document;

public interface DocumentProcessor {
	
	public String[] tokenize(Document document);
	
	public String[] normalize(String[] words);
	
}
