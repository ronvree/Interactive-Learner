package testing;

import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.document.Document;
import model.document.StandardDocument;
import model.document.processed.ProcessedDocument;
import model.documentprocessor.DocumentProcessor;
import model.processeddocumentset.BinomialDataSet;
import model.processeddocumentset.ProcessedDocumentSet;
import model.user.HumanUser;
import model.user.User;

import java.io.File;

public class PickDocumentTest {
	
	public static void main(String[] args) {


		File f1 = new File("data/ham");
		File f2 = new File("data/spam");
		BinomialDataSet docset = new BinomialDataSet(f2,f1);
		Classifier NB = new NaiveBayes("Spam", "Ham");

		NB.classify(new StandardDocument(new File("data/spam/spmsga1.txt")));
		
	}
	
}
