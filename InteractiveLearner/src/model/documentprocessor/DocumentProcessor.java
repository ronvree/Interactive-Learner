package model.documentprocessor;

import model.document.Document;

public interface DocumentProcessor {

	public static final int ASCIILOWERBOUND = 97;
	public static final int ASCIIUPPERBOUND = 122;

	public static final int QUESTIONMARK = 63;
	public static final int EXCLAMATIONMARK = 33;
	public static final int DOT = 46;
	public static final int DOUBLEQUOTES = 34;
	public static final int COMMA = 44;
	public static final int OPENINGPARENTHESES = 40;
	public static final int CLOSINGPARENTHESES = 41;
	public static final int COLON = 58;
	public static final int SEMICOLON = 59;
	public static final int OPENINGBRACES = 123;
	public static final int CLOSINGBRACES = 125;





	public static String[] tokenize(Document document)	{
		String text = document.getDocument();
//		System.out.println("Text: " + text);
		String[] split = text.split("\\s+");
//		for(int i = 0; i < split.length; i++) {
//			System.out.println(i +": " + split[i]);
//		}
		return split;
	}
	
	public static String[] normalize(String[] words)	{
		String[] toLower = new String[words.length];
		String[] noPunctuation = new String[words.length];

		for(int i = 0; i < words.length; i++) {
			toLower[i] = words[i].toLowerCase();
		}

		for(int word = 0; word < toLower.length; word++) {
			for (int character = 0; character < toLower[word].length(); character++) {
				if ((int) toLower[word].charAt(character) < ASCIILOWERBOUND || toLower[word].charAt(character) > ASCIIUPPERBOUND) {

					// TODO
					/**
					 * Wat doen we als er een leestekenin voor komt?
					 * 	- hele woord verwijderen;
					 * 	- leesteken verwijderen (kan het woord verpesten als er iets met ' of - staat);
					 * 	- Filteren op leestekens, bij sommige leestekens(.,!?) alleen leesteken verwijderen en bij anderen('-_@) hele woord verwijderen;
					 */
				}
			}
		}
		return toLower;
	}
	
}
