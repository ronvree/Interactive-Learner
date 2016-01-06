package model.classifiers;

import java.util.ArrayList;
import java.util.List;

import model.document.Document;
import model.documentprocessor.DocumentProcessor;
import model.featureselection.ChiSquared;
import model.featureselection.FeatureSelection;
import model.processeddocumentset.ProcessedDocumentSet;

public class FSNaiveBayes implements Classifier {
	private FeatureSelection fs;
	private ProcessedDocumentSet kb;
	private String classification1;
	private String classification2;

	public FSNaiveBayes(String classification1, String classification2) {
		this.classification1 = classification1;
		this.classification2 = classification2;
	}

	@Override
	public String classify(Document document) {
		String[] normalized = DocumentProcessor.normalize(document);
		
		List<String> selected1 = this.filter(normalized, classification1);
		List<String> selected2 = this.filter(normalized, classification2);

		double wordCount = (double) kb.wordCount();
		
		double chanceWordsGivenClassification1 = 0;
		double chanceWordsGivenClassification2 = 0;
		for (String word : selected1) {
			chanceWordsGivenClassification1 = chanceWordsGivenClassification1 + (Math.log(((double) kb.getFrequency(word, classification1) + 1) / ((double) kb.wordCount(classification1) + 1)));
		}
		for (String word : selected2)	{
			chanceWordsGivenClassification2 = chanceWordsGivenClassification2 + (Math.log(((double) kb.getFrequency(word, classification2) + 1) / ((double) kb.wordCount(classification2) + 1)));
		}
		double chanceClassification1 = (double) kb.wordCount(classification1) / wordCount;
		double chanceClassification2 = (double) kb.wordCount(classification2) / wordCount;
		
		double chance1 = Math.log(chanceClassification1) + chanceWordsGivenClassification1;
		double chance2 = Math.log(chanceClassification2) + chanceWordsGivenClassification2;
		
		String result;
		if(chance1 > chance2) {
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
		fs = new ChiSquared(documents);
		fs.update();
	}
	
	private List<String> filter(String[] words, String classification)	{
		List<String> selected = this.fs.getSelectedWords(classification);
		List<String> result = new ArrayList<String>();
		for (String word : words)	{
			if (selected.contains(word))	{
				result.add(word);
			}
		}
		return result;
	}
	
	
}
