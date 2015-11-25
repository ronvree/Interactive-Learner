package model;

public class ProcessedDocument {
	
	private Document document;
	private Classification classification;
	
	public ProcessedDocument(Document document, Classification classification)	{
		this.document = document;
		this.classification = classification;
	}
	
	public Document getDocument()	{
		return this.document;
	}
		
	public Classification getClassification()	{
		return this.classification;
	}

}
