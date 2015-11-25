package model.comparator;

import model.Classification;
import model.Document;

/*
 * Uses user input to compare the document's classification to the expected classification.
 */
public interface Comparator {
	
	public Classification compare(Document document, Classification classification);
	
}
