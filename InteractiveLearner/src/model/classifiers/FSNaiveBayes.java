package model.classifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.document.Document;
import model.documentprocessor.DocumentProcessor;
import model.featureselection.ChiSquared;
import model.featureselection.FeatureSelection;
import model.processeddocumentset.ProcessedDocumentSet;

public class FSNaiveBayes implements Classifier {
	
	private static final List<String> STOPWORDS = new ArrayList<String>();
	static	{
		STOPWORDS.addAll(Arrays.asList(new String[]{"a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"}));
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
