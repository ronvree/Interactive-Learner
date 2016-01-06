package model.processeddocumentset;

import java.util.List;

import model.document.Document;
import model.document.processed.ProcessedDocument;

/*
 * A set of classified documents
 */
public interface ProcessedDocumentSet {
	
	public ProcessedDocument getDocument(int index);
	
	/**
	 * Counts the total amount of occurrences of this word in all documents.
	 * @param word -- The word to count
	 */
	public int getFrequency(String word);
	
	/**
	 * Counts the amount of occurrences of this word in documents with the specified classification.
	 * @param word -- The word to count
	 * @param classification -- Classification of the documents
	 */
	public int getFrequency(String word, String classification);
	
	/**
	 * Returns the amount of documents processed by this set
	 */
	public int size();
	
	/**
	 * Returns the amount of words in this set
	 */
	public int wordCount();
	
	/**
	 * Counts the amount of documents in this set with the specified classification
	 * @param classification -- The classification of the document
	 */
	public int countDocuments(String classification);
	
	/**
	 * Returns the total amount of words in documents with this classification
	 * @param classification
	 */
	public int wordCount(String classification);
	
	/**
	 * Returns all words that have been processed
	 */
	public List<String> getWords();
	
	/**
	 * Return all classification
	 */
	public List<String> getClassifications();
	
	/**
	 * Put a new document in the set
	 * @param document -- document
	 * @param classification -- its classification
	 */
	public void put(Document document, String classification);
	
	/**
	 * Clear the learned data and re-learn from the source files
	 */
	public void reset();
	
	/**
	 * Clear the learned data
	 */
	public void clear();

	/**
	 * Counts documents of classification containing word
	 */
	public int countDocsWithWordInClass(String word, String classification);
	
}
