package controller;

import model.classifiers.Classifier;
import model.classifiers.FSNaiveBayes;
import model.classifiers.NaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSet;
import view.GUI;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Gijs on 06-Jan-16.
 */
public class Controller implements Serializable {

    public Controller() {
        GUI.buildGUI();
	}

	public static HashMap runTest(File first, String firstName, File second, String secondName, File test, boolean useFeatureSelection, boolean removeStopWords) {
        HashMap<String, String> result = new HashMap<>();
		long time = System.currentTimeMillis();
		BinomialDataSet docset = new BinomialDataSet(first, firstName, second, secondName);
        Classifier NB;
        if (useFeatureSelection) {
            NB = new FSNaiveBayes(firstName, secondName, removeStopWords);
        } else {
            NB = new NaiveBayes(firstName, secondName);
        }
		NB.train(docset);
		int counter = 1;
		for (File file : test.listFiles()) {
			counter++;
			String classification = NB.classify(new StandardDocument(file));
            result.put(file.getName(), classification);
		}
        long calculateTime = System.currentTimeMillis() - time;
        result.put("Time", String.valueOf(calculateTime));
		return result;
	}

	public static void main(String[] args) {
        Controller c = new Controller();

	}
}
