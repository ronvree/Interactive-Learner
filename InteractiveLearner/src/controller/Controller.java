package controller;

import java.util.ArrayList;
import java.util.List;

import model.Classification;
import model.Document;
import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.comparator.Comparator;
import model.comparator.StandardComparator;
import model.processeddocumentset.ProcessedDocumentSet;
import model.processeddocumentset.StandardProcessedDocumentSet;
import model.user.HumanUser;
import model.user.User;
import view.TestUI;
import view.View;

public class Controller {
	
	public static void main(String[] args) {
		List<Document> toProcess = new ArrayList<Document>();
		
		Classifier classifier = new NaiveBayes();
		User user = new HumanUser();
		Comparator comparator = new StandardComparator(user);
		ProcessedDocumentSet documentSet = new StandardProcessedDocumentSet();
		View view = new TestUI();
		
		documentSet.reset();
		for (Document document : toProcess)	{
			classifier.train(documentSet);
			Classification classification = classifier.classify(document);
			Classification correctClassification = comparator.compare(document, classification);
			documentSet.put(document, correctClassification);
		}
		
		
		
		
	}
	
}
