package controller;

import java.util.ArrayList;
import java.util.List;

import model.Classification;
import model.Document;
import model.ProcessedDocument;
import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.comparator.Comparator;
import model.comparator.HumanComparator;
import model.processeddocumentset.ProcessedDocumentSet;
import model.processeddocumentset.StandardProcessedDocumentSet;
import model.user.StandardUser;
import model.user.User;
import view.View;

public class Controller {
	
	public static void main(String[] args) {
		
		List<Document> toProcess = new ArrayList<Document>();
		
		Classifier classifier = new NaiveBayes();
		User user = new StandardUser();
		Comparator comparator = new HumanComparator(user);
		ProcessedDocumentSet documentSet = new StandardProcessedDocumentSet();
		View view;
		
		documentSet.reset();
		for (Document document : toProcess)	{
			classifier.train(documentSet);
			Classification classification = classifier.classify(document);
			Classification correctClassification = comparator.compare(document, classification);
			documentSet.put(document, correctClassification);
		}
		
		
		
		
	}
	
}
