package model.processeddocumentset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Classification;
import model.Document;
import model.ProcessedDocument;
import model.documentprocessor.DocumentProcessor;

public class StandardProcessedDocumentSet implements ProcessedDocumentSet {
	
	private List<ProcessedDocument> documents;
	private Map<String, Integer> frequencies;
	
	public StandardProcessedDocumentSet()	{
		this.documents = new ArrayList<ProcessedDocument>();
		this.frequencies = new HashMap<String, Integer>();
		this.reset();
	}
	
	@Override
	public ProcessedDocument getDocument(int index) {
		return this.documents.get(index);
	}

	@Override
	public int size() {
		return this.documents.size();
	}

	@Override
	public void put(Document document, Classification classification) {
		this.documents.add(new ProcessedDocument(document, classification));
		
		String[] words = DocumentProcessor.tokenize(document);
		words = DocumentProcessor.normalize(words);
		
		for (String word : words)	{
			Integer count = this.frequencies.get(word);
			if (count == null)	{
				this.frequencies.put(word, new Integer(0));
			} else {
				frequencies.put(word, count++);
			}
		}
	}

	@Override
	public void reset() {
		this.clear();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		this.documents.clear();
		this.frequencies.clear();
	}

}
