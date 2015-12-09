package controller;

import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.user.HumanUser;
import model.user.User;

public class Controller {
	
	public static void main(String[] args) {
		Classifier classifier = new NaiveBayes();
		User user = new HumanUser();
//		Comparator comparator = new StandardComparator(user);
//		ProcessedDocumentSet documentSet = new BinomialDataSet();
//		View view = new TestUI();
//		
//		documentSet.reset();
//		view.update();
//		
//		while (true)	{
//			Document document = user.pickDocument();												/* Ask user to pick a document 		*/
//			classifier.train(documentSet);															/* Train classifier on document set */
//			Classification classification = classifier.classify(document);							/* Classify document 				*/
//			Classification correctClassification = comparator.compare(document, classification);	/* Ask feedback from user 			*/
//			documentSet.put(document, correctClassification);										/* Add to processed documents	 	*/
//			view.update();																			/* Show progress 					*/
//		}
//		
	}
	
}
