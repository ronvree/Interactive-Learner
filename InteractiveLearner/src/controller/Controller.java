package controller;

import model.Classification;
import model.Document;
import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.comparator.Comparator;
import model.comparator.StandardComparator;
import model.processeddocumentset.ProcessedDocumentSet;
import model.processeddocumentset.CorpusMail;
import model.user.HumanUser;
import model.user.User;
import view.TestUI;
import view.View;

public class Controller {
	
	public static void main(String[] args) {
		Classifier classifier = new NaiveBayes();
		User user = new HumanUser();
		Comparator comparator = new StandardComparator(user);
		ProcessedDocumentSet documentSet = new CorpusMail();
		View view = new TestUI();
		
		documentSet.reset();
		view.update();
		
		while (true)	{
			Document document = user.pickDocument();												/* Ask user to pick a document 		*/
			classifier.train(documentSet);															/* Train classifier on document set */
			Classification classification = classifier.classify(document);							/* Classify document 				*/
			Classification correctClassification = comparator.compare(document, classification);	/* Ask feedback from user 			*/
			documentSet.put(document, correctClassification);										/* Add to processed documents	 	*/
			view.update();																			/* Show progress 					*/
		}
		
	}
	
}
