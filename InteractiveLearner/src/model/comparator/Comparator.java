package model.comparator;

import model.Document;

/*
 * Uses user input to compare the document's classification to the expected classification.
 */
public interface Comparator {
	
	public String compare(Document document, String classification);
	
}
