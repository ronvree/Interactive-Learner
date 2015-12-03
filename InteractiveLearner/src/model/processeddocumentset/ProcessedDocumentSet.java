package model.processeddocumentset;

import model.Classification;
import model.Document;
import model.ProcessedDocument;

/*
 * A set of classified documents
 */
public interface ProcessedDocumentSet {
	
	public ProcessedDocument getDocument(int index);
	
	public int getFrequency(String word);
	
	public int size();
	
	public void put(Document document, Classification classification);
	
	public void reset();
	
	public void clear();
	
}
