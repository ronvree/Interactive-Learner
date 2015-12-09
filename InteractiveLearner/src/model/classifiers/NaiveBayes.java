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
		double wordCount = (double) kb.wordCount();
		/*
		Calculations for first classification
		 */
		double changeWordsGivenClassification = 0;
		for (String word : normalized) {
			changeWordsGivenClassification = changeWordsGivenClassification + (Math.log(((double) kb.getFrequency(word, classification1) + 1) / ((double) kb.wordCount(classification1) + 1)));
		}
		double changeClassification = (double) kb.wordCount(classification1) / wordCount;
		double changeClassificationGivenWords = Math.log(changeClassification) + changeWordsGivenClassification;

		double TOTAL1 = changeClassificationGivenWords;

		/*
		Calculations for second classification
		 */
		double changeWordsGivenClassification2 = 0;
		for (String word : normalized) {
			changeWordsGivenClassification2 = changeWordsGivenClassification2 + (Math.log(((double) kb.getFrequency(word, classification2) + 1) / ((double) kb.wordCount(classification2) + 1)));
		}
		double changeClassification2 = (double) kb.wordCount(classification2) / wordCount;
		double changeClassificationGivenWords2 = Math.log(changeClassification2) + changeWordsGivenClassification2;

		double TOTAL2 = changeClassificationGivenWords2;

		String result;
		if(TOTAL1 > TOTAL2) {
			result = classification1;
		} else {
			result = classification2;
		}

		System.out.println("This document has been classified as: " + result);
		return result;
	}

	@Override
	public void train(ProcessedDocumentSet documents) {
		kb = documents;
	}
}
