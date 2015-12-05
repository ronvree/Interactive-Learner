package model.processeddocumentset;

import model.document.Document;
import model.document.processed.ProcessedDocument;

/*
 * A set of classified documents
 */
public interface ProcessedDocumentSet {
	
	public ProcessedDocument getDocument(int index);
	
	public int getFrequency(String word);
	
	public int getFrequency(String word, String classification);
	
	public int size();
	
	public void put(Document document, String classification);
	
	public void reset();
	
	public void clear();
	
}
