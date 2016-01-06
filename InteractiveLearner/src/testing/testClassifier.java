package testing;

import java.io.File;

import model.classifiers.Classifier;
import model.classifiers.FSNaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSet;
import model.processeddocumentset.ProcessedDocumentSet;

public class testClassifier {
	
	public static void main(String[] args) {

		try {
			System.out.println("START");
			long time = System.currentTimeMillis();
			File f1 = new File("InteractiveLearner" + File.separator + "data" + File.separator + "spam");
			File f2 = new File("InteractiveLearner" + File.separator + "data" + File.separator + "ham");
			ProcessedDocumentSet docset = new BinomialDataSet(f1, f2);
			Classifier NB = new FSNaiveBayes("spam", "ham");
			NB.train(docset);

			File testSet = new File("InteractiveLearner" + File.separator + "data" + File.separator + "testSet");
			int counter = 1;
			for (File file : testSet.listFiles()) {
				System.out.println("File " + counter + " of " + testSet.listFiles().length);
				counter++;
				System.out.println("Filename: " + file.getName());
				NB.classify(new StandardDocument(file));
				System.out.println();
			}
			System.out.println("Done! This took: " + (System.currentTimeMillis() - time));
		} catch (NullPointerException e) {
			/*
			Probably a false path name. Both our laptops use other paths. When we have succesfully implemented a GUI this is not necessary anymore.
			 */
			//TODO delete when having GUI.
			System.out.println("START");
			long time = System.currentTimeMillis();
			File f1 = new File("data" + File.separator + "spam");
			File f2 = new File("data" + File.separator + "ham");
			ProcessedDocumentSet docset = new BinomialDataSet(f1, f2);
			Classifier NB = new FSNaiveBayes("spam", "ham");
			NB.train(docset);

//			File testSet = new File("src" + File.separator + "data" + File.separator + "testSet");
			File testSet = new File("data" + File.separator + "testSet");
			int counter = 1;
			for (File file : testSet.listFiles()) {
				System.out.println("File " + counter + " of " + testSet.listFiles().length);
				counter++;
				System.out.println("Filename: " + file.getName());
				NB.classify(new StandardDocument(file));
				System.out.println();
			}
			System.out.println("Done! This took: " + (System.currentTimeMillis() - time));

		}
	}

	
}
