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
		

		String corpusLocation = "src/dataset/";
		String c1 = "spam";
		String c2 = "ham";

		File f1 = new File(corpusLocation + c2);
		File f2 = new File(corpusLocation + c1);
		BinomialDataSet docset = new BinomialDataSet(f1,f2);
		Classifier NB = new NaiveBayes("Spam", "Ham");

		NB.classify(new StandardDocument(new File("dataset/spam/spmsga1.txt")));
		
	}
	
}
