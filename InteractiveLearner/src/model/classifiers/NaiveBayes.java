package model.classifiers;

import model.document.Document;
import model.documentprocessor.DocumentProcessor;
import model.processeddocumentset.ProcessedDocumentSet;

public class NaiveBayes implements Classifier {

	private ProcessedDocumentSet kb;
	private String classification1;
	private String classification2;

	public NaiveBayes(String classification1, String classification2) {
		this.classification1 = classification1;
		this.classification2 = classification2;
	}

	@Override
	public String classify(Document document) {
		String[] normalized = DocumentProcessor.normalize(document);

		double change = calculate(normalized);
		System.out.println("Dus het is: " + change);
		return null;
	}

	@Override
	public void train(ProcessedDocumentSet documents) {
		kb = documents;
	}

	public double calculate(String[] normalized) {
		double changeWordsGivenClassification = 0;
		for (String word : normalized) {
			changeWordsGivenClassification = changeWordsGivenClassification + (Math.log(((double) kb.getFrequency(word, classification1) + 1) / ((double) kb.wordCount(classification1) + 1)));
		}
		double changeClassification = (double) kb.wordCount(classification1) / (double) kb.wordCount();
		double changeClassificationGivenWords = Math.log(changeClassification) + changeWordsGivenClassification;

		System.out.println("changeWordsGivenClassification: " + changeWordsGivenClassification);
		System.out.println("changeClassification: " + changeClassification);
		System.out.println("changeClassificationGivenWords: " + changeClassificationGivenWords);

		double TOTAL1 = changeClassificationGivenWords;

		double changeWordsGivenClassification2 = 0;
		for (String word : normalized) {
			changeWordsGivenClassification2 = changeWordsGivenClassification2 + (Math.log(((double) kb.getFrequency(word, classification2) + 1) / ((double) kb.wordCount(classification2) + 1)));
		}
		double changeClassification2 = (double) kb.wordCount(classification2) / (double) kb.wordCount();
		double changeClassificationGivenWords2 = Math.log(changeClassification2) + changeWordsGivenClassification2;

		System.out.println("changeWordsGivenClassification: " + changeWordsGivenClassification2);
		System.out.println("changeClassification: " + changeClassification2);
		System.out.println("changeClassificationGivenWords: " + changeClassificationGivenWords2);

		double TOTAL2 = changeWordsGivenClassification2;
		System.out.println("Class 1: " + TOTAL1);
		System.out.println("Class 2: " + TOTAL2);

		return Math.max(TOTAL1, TOTAL2);
	}
}
