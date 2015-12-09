package model.processeddocumentset;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.document.Document;
import model.document.StandardDocument;
import model.document.processed.ProcessedDocument;
import model.documentprocessor.DocumentProcessor;

/**
 * Capable of storing Documents with two different classifications. Data about frequency can be queried easily.
 * @author Ron
 *
 */
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
	 * Map containing the frequency of each word, for both classifications.
	 */
	private Map<String, Map<String, Integer>> frequencies;
	
	public BinomialDataSet(File src1, File src2)	{
		this.srcFile1 = src1;
		this.srcFile2 = src2;
		this.class1 = src1.getName();
		this.class2 = src2.getName();
		this.documents = new ArrayList<ProcessedDocument>();
		this.frequencies = new HashMap<String, Map<String, Integer>>();
		this.frequencies.put(class1, new HashMap<String, Integer>());
		this.frequencies.put(class2, new HashMap<String, Integer>());
		this.reset();
	}
	
	@Override
	public ProcessedDocument getDocument(int index) {
		return this.documents.get(index);
	}

	@Override
	public int getFrequency(String word) {
		return this.frequencies.get(class1).get(word) + this.frequencies.get(class2).get(word);
	}

	@Override
	public int size() {
		return this.documents.size();
	}
	
	@Override
	public int wordCount()	{
		int count = 0;
		for (String c : this.frequencies.keySet())	{
			for (String w : this.frequencies.get(c).keySet())	{
				count += this.frequencies.get(c).get(w);
			}
		}
		return count;
	}
	
	@Override
	public int countDocuments(String classification)	{
		int count = 0;
		for (ProcessedDocument doc : this.documents)	{
			if (doc.getClassification().equals(classification))	{
				count++;
			}
		}
		return count;
	}

	@Override
	public void put(Document document, String classification) {
		this.documents.add(new ProcessedDocument(document, classification));
		
		String[] words = DocumentProcessor.normalize(document);
		
		for (String c : this.frequencies.keySet())	{
			Map<String, Integer> frequency = this.frequencies.get(c);
			for (String word : words)	{
				if (!frequency.containsKey(word))	{
					frequency.put(word, new Integer(1));
				} else {
					Integer count = frequency.get(word);
					frequency.put(word, count + 1);
				}
			}
		}
	}

	@Override
	public void reset() {
		this.clear();
		for (File file : srcFile1.listFiles())	{
			this.put(new StandardDocument(file), class1);
		}
		for (File file : srcFile2.listFiles())	{
			this.put(new StandardDocument(file), class2);
		}
	}

	@Override
	public void clear() {
		this.documents.clear();
		this.frequencies.get(class1).clear();
		this.frequencies.get(class2).clear();
	}

	@Override
	public int getFrequency(String word, String classification) {
		return this.frequencies.get(classification).get(word);
	}

}
