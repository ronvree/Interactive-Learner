package model;

public interface Classifier {
	
	public Classification classify(Document document);
	
	public void train(ProcessedDocumentSet documents);
	
}
