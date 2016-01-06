package controller;

import model.classifiers.Classifier;
import model.classifiers.FSNaiveBayes;
import model.classifiers.NaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSet;
import view.GUI;
import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by Gijs on 06-Jan-16.
 */
public class Controller implements Serializable {
	GUI gui;

    public Controller() {
        GUI.buildGUI();
	}

	public static void runTest(File first, String firstName, File second, String secondName, File test, boolean useFeatureSelection, boolean removeStopWords) {
		BinomialDataSet docset = new BinomialDataSet(first, firstName, second, secondName);
        Classifier NB;
        if (useFeatureSelection) {
            NB = new FSNaiveBayes(firstName, secondName, removeStopWords);
        } else {
            NB = new NaiveBayes(firstName, secondName);
        }

		Scanner scanner = new Scanner(System.in);
		NB.train(docset);
		for (File file : test.listFiles()) {
			boolean goodClassified = true;
			boolean validInput = false;
			String classification = NB.classify(new StandardDocument(file));
			while (!validInput) {
				System.out.print(file.getName() + ": " + classification + ". Is this correct? (y/n)");
				String input = scanner.next();
				if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
					goodClassified = true;
					validInput = true;
				} else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
					goodClassified = false;
					validInput = true;
				} else {
					System.out.println("Not a valid input.");
				}
			}
			if (goodClassified) {
				docset.put(new StandardDocument(file), classification);
			} else {
				if (classification.equals(firstName)) {
					classification = secondName;
					docset.put(new StandardDocument(file), classification);
				} else if (classification.equals(secondName)) {
					classification = firstName;
					docset.put(new StandardDocument(file), classification);
				}
			}
			NB.train(docset);
		}
	}

	public static void main(String[] args) {
        Controller c = new Controller();

	}
}
