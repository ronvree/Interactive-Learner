package model.classifiers;

import java.util.ArrayList;
import java.util.List;

import model.document.Document;
import model.documentprocessor.DocumentProcessor;
import model.featureselection.ChiSquared;
import model.featureselection.FeatureSelection;
import model.processeddocumentset.ProcessedDocumentSet;

public class FSNaiveBayes implements Classifier {
	
	private static final List<String> STOPWORDS = new ArrayList<String>();
	static	{
		STOPWORDS.add("a");
		STOPWORDS.add("an");
		STOPWORDS.add("as");
		STOPWORDS.add("at");
		STOPWORDS.add("be");
		STOPWORDS.add("by");
		STOPWORDS.add("in");
		STOPWORDS.add("is");
		STOPWORDS.add("of");
		STOPWORDS.add("on");
		STOPWORDS.add("or");
		STOPWORDS.add("to");
		STOPWORDS.add("and");
		STOPWORDS.add("are");
		STOPWORDS.add("for");
		STOPWORDS.add("with");
		STOPWORDS.add("the");
	}
	
	private FeatureSelection fs;
	private ProcessedDocumentSet kb;
	private String classification1;
	private String classification2;
	private boolean filterStopWords;

	public FSNaiveBayes(String classification1, String classification2) {
		this.classification1 = classification1;
		this.classification2 = classification2;
		filterStopWords = false;
	}
	
	public FSNaiveBayes(String classification1, String classification2, boolean filterStopWords) {
		this.classification1 = classification1;
		this.classification2 = classification2;
		this.filterStopWords = filterStopWords;
	}

	@Override
	public String classify(Document document) {
		String[] normalized = DocumentProcessor.normalize(document);	// Normalize
		
		List<String> words1 = this.filter(normalized, classification1);	// Feature selection
		List<String> words2 = this.filter(normalized, classification2);

		double wordCount = (double) kb.wordCount();						// Total word count
		double wordCount1 = (double) kb.wordCount(classification1); 	// Word count in category 1
		double wordCount2 = (double) kb.wordCount(classification2); 	// Word count in category 2
		
		double chanceWordsGivenClassification1 = 0;
		double chanceWordsGivenClassification2 = 0;
		for (String word : words1) {
			chanceWordsGivenClassification1 += (Math.log(((double) kb.getFrequency(word, classification1) + 1) / (wordCount1 + 1)));
		}
		for (String word : words2)	{
			chanceWordsGivenClassification2 += (Math.log(((double) kb.getFrequency(word, classification2) + 1) / (wordCount2 + 1)));
		}
		
		double chance1 = Math.log(wordCount1 / wordCount) + chanceWordsGivenClassification1;
		double chance2 = Math.log(wordCount2 / wordCount) + chanceWordsGivenClassification2;
		
		String result = (chance1 > chance2)? classification1:classification2;
		System.out.println("This document has been classified as: " + result);
		return result;
	}

	@Override
	public void train(ProcessedDocumentSet documents) {
		kb = documents;
		fs = new ChiSquared(documents);
		fs.update();
	}
	
	/**
	 * Perform feature selection
	 */
	private List<String> filter(String[] words, String classification)	{
		List<String> selected = this.fs.getSelectedWords(classification);
		List<String> result = new ArrayList<String>();
		for (String word : words)	{
			if (selected.contains(word) && !(filterStopWords && STOPWORDS.contains(word)))	{
				result.add(word);
			}
		}
		return result;
	}
	
	
}
