package model;

/*
 * Classifies documents based on training on a set of pre-classified set of documents.
 */

public interface Classifier {
	
	public Classification classify(Document document);
	
	public void train(ProcessedDocumentSet documents);
	
}
