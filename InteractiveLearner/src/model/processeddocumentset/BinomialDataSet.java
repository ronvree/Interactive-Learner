package model.processeddocumentset;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.document.Document;
import model.document.StandardDocument;
import model.document.processed.ProcessedDocument;
import model.documentprocessor.DocumentProcessor;

public class BinomialDataSet implements ProcessedDocumentSet {
	
	/**
	 * Source files of the documents, sorted by classification. The names of these files name the classification.
	 */
	private File srcFile1;
	private File srcFile2;
	
	/**
	 * Classifications (Names of the source files)
	 */
	private String class1;
	private String class2;
	
	/**
	 * List of all documents
	 */
	private List<ProcessedDocument> documents;
	
	/**
	 * Map containing the word count for both classifications
	 */
	private Map<String, Integer> wordCounts;
	
	/**
	 * Map containing the amount each word occurs in a document, for both classifications.
	 */
	private Map<String, Map<String, Integer>> docFrequencies;
	
	/**
	 * Map containing document count for each classification
	 */
	private Map<String, Integer> documentCount;
	
	public BinomialDataSet(File src1, File src2)	{
		this.srcFile1 = src1;
		this.srcFile2 = src2;
		this.class1 = src1.getName();
		this.class2 = src2.getName();
		this.documents = new ArrayList<ProcessedDocument>();
		this.docFrequencies = new HashMap<String, Map<String, Integer>>();
		this.docFrequencies.put(class1, new HashMap<String, Integer>());
		this.docFrequencies.put(class2, new HashMap<String, Integer>());
		this.documentCount = new HashMap<String, Integer>();
		this.wordCounts = new HashMap<String, Integer>();
		this.reset();
	}
	
	public BinomialDataSet(File src1, String firstName, File src2, String secondName)	{
		this.srcFile1 = src1;
		this.srcFile2 = src2;
		this.class1 = firstName;
		this.class2 = secondName;
		this.documents = new ArrayList<ProcessedDocument>();
		this.docFrequencies = new HashMap<String, Map<String, Integer>>();
		this.docFrequencies.put(class1, new HashMap<String, Integer>());
		this.docFrequencies.put(class2, new HashMap<String, Integer>());
		this.documentCount = new HashMap<String, Integer>();
		this.wordCounts = new HashMap<String, Integer>();
		this.reset();
	}
	
	@Override
	public ProcessedDocument getDocument(int index) {
		return this.documents.get(index);
	}

	@Override
	public int getFrequency(String word) {
		Integer count1 = this.docFrequencies.get(class1).get(word);
		Integer count2 = this.docFrequencies.get(class2).get(word);
		return  ((count1 != null)? count1:0) + ((count2 != null)? count2:0);
	}

	@Override
	public int getFrequency(String word, String classification) {
		Integer count = this.docFrequencies.get(classification).get(word);
		return count != null? count:0;
	}

	@Override
	public int size() {
		return this.documents.size();
	}
	
	@Override
	public int size(String classification) {
		return this.documentCount.get(class1) + this.documentCount.get(class2);
	}

	@Override
	public int wordCount() {
		return this.wordCount(class1) + this.wordCount(class2);
	}

	@Override
	public int wordCount(String classification) {
		return this.wordCounts.get(classification);
	}

	@Override
	public List<String> getWords()	{
		List<String> result = new ArrayList<String>(this.docFrequencies.get(class1).keySet());
		result.addAll(this.docFrequencies.get(class2).keySet());
		return result;
	}

	@Override
	public List<String> getClassifications()	{
		List<String> result = new ArrayList<String>();
		result.add(class1);
		result.add(class2);
		return result;
	}

	@Override
	public void put(Document document, String classification) {
		this.documents.add(new ProcessedDocument(document, classification));
		this.documentCount.put(classification, this.documentCount.get(classification) + 1);
		Set<String> words = new HashSet<String>(Arrays.asList(DocumentProcessor.normalize(document)));
		Map<String, Integer> frequency = this.docFrequencies.get(classification);
		for (String word : words)	{
			this.wordCounts.put(classification, this.wordCounts.get(classification) + 1);
			if (!frequency.containsKey(word))	{
				frequency.put(word, new Integer(1));
			} else {
				Integer count = frequency.get(word);
				frequency.put(word, count + 1);
			}
		}
	}

	@Override
	public void reset() {
		this.clear();
		File[] list1 = srcFile1.listFiles();
		File[] list2 = srcFile2.listFiles();
		if (list1 != null && list2 != null) 	{
			for (File file : list1)	{
				this.put(new StandardDocument(file), class1);
			}
			for (File file : list2)	{
				this.put(new StandardDocument(file), class2);
			}
		}
	}

	@Override
	public void clear() {
		this.documents.clear();
		this.docFrequencies.get(class1).clear();
		this.docFrequencies.get(class2).clear();
		this.documentCount.clear();
		this.documentCount.put(class1, 0);
		this.documentCount.put(class2, 0);
		this.wordCounts.clear();
		this.wordCounts.put(class1, 0);
		this.wordCounts.put(class2, 0);
	}


}
