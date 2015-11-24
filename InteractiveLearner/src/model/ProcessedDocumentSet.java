package model;

public interface ProcessedDocumentSet {
	
	public ProcessedDocument getDocument(int index);
	
	public int size();
	
	public void put(Document document, Classification classification);
	
	public void reset();
	
	public void clear();
	
}
