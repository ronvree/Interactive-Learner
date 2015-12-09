package testing;

import java.io.File;
import java.io.IOException;

import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSet;

public class PickDocumentTest {
	
	public static void main(String[] args) {


		File f1 = new File("InteractiveLearner"+File.separator+"data"+File.separator+"spam");
		File f2 = new File("InteractiveLearner"+File.separator+"data"+File.separator+"ham");
		BinomialDataSet docset = new BinomialDataSet(f1,f2);
		Classifier NB = new NaiveBayes("spam", "ham");
		NB.train(docset);
		NB.classify(new StandardDocument(new File("InteractiveLearner"+File.separator+"data"+File.separator+"spam"+File.separator+"spmsga1.txt")));
	}
	
}
