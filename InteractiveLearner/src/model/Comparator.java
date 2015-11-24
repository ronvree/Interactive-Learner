package model;

/*
 * Uses user input to compare the document's classification to the expected classification.
 */
public interface Comparator {
	
	public boolean compare(Document document, Classification classification);
	
}
