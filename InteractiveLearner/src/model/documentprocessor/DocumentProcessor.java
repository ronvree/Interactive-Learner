package model.documentprocessor;

import model.document.Document;

public interface DocumentProcessor {

	// Elegant code!
	public static String[] normalize(Document document)	{
		String text = document.getText();
		String[] result = text.replaceAll("[^a-zA-Z]", "").toLowerCase().split("\\s+");
		return result;
	}
}
