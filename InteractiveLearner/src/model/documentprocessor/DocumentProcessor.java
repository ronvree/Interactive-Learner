package model.documentprocessor;

import model.Document;

public interface DocumentProcessor {
	
	public static String[] tokenize(Document document)	{
		String text = document.getDocument();
		System.out.println("Text: " + text);
		String[] split = text.split("\\s+");

		////////////////////////////////////////////////////////
		for(int i = 0; i < split.length; i++) {
			System.out.println(i +": " + split[i]);
		}
		////////////////////////////////////////////////////////
		return split;
	}
	
	public static String[] normalize(String[] words)	{
		// TODO
		
		return null;
	}
	
}
