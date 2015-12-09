package testing;

import java.io.File;

import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSet;

public class PickDocumentTest {
	
	public static void main(String[] args) {


		File f1 = new File("data/ham");
		File f2 = new File("data/spam");
		BinomialDataSet docset = new BinomialDataSet(f2,f1);
		Classifier NB = new NaiveBayes("Spam", "Ham");

		NB.classify(new StandardDocument(new File("data/spam/spmsga1.txt")));
		
	}
	
}
