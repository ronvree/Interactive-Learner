package model.processeddocumentset;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Classification;
import model.Document;
import model.ProcessedDocument;
import model.StandardDocument;
import model.documentprocessor.DocumentProcessor;

public class StandardProcessedDocumentSet implements ProcessedDocumentSet {
	
	private static final String PATH = "src/corpus-mails";
	
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
		File data = new File(PATH);
		for (File corpus : data.listFiles())	{
			for (File part : corpus.listFiles())	{
				for (File message : part.listFiles())	{
					Classification classification = (message.getName().startsWith("sp"))? Classification.SPAM:Classification.HAM;
					this.put(new StandardDocument(message), classification);
				}
			}
		}
	}

	@Override
	public void clear() {
		this.documents.clear();
		this.frequencies.clear();
	}

}
