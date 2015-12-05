package model;

public class ProcessedDocument {
	
	private Document document;
	private String classification;
	
	public ProcessedDocument(Document document, String classification)	{
		this.document = document;
		this.classification = classification;
	}
	
	public Document getDocument()	{
		return this.document;
	}
		
	public String getClassification()	{
		return this.classification;
	}

}
