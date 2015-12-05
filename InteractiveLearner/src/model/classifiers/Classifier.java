package model.classifiers;

import model.Document;
import model.processeddocumentset.ProcessedDocumentSet;

/*
 * Classifies documents based on training on a set of pre-classified set of documents.
 */

public interface Classifier {
	
	public String classify(Document document);
	
	public void train(ProcessedDocumentSet documents);
	
}
