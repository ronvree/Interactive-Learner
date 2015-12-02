package model.documentprocessor;

import model.Document;

public interface DocumentProcessor {

	public static final int ASCIILOWERBOUND = 97;
	public static final int ASCIIUPPERBOUND = 122;

	
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
		String[] toLower = new String[words.length];
		String[] noPunctuation = new String[words.length];

		for(int i = 0; i < words.length; i++) {
			toLower[i] = words[i].toLowerCase();
		}
		int aantalWoorden = 0;
        int woord = -1;
		for(int word = 0; word < toLower.length; word++) {
			for (int character = 0; character < toLower[word].length(); character++) {
				if ((int) toLower[word].charAt(character) < ASCIILOWERBOUND || toLower[word].charAt(character) > ASCIIUPPERBOUND) {
					System.out.println("Woord: " + word + "		" + toLower[word].charAt(character));
                    if (woord < word) {
                        aantalWoorden++;
                        woord = word;
                    }
					// TODO
					/**
					 * Wat doen we als er een leestekenin voor komt?
					 * 	- hele woord verwijderen;
					 * 	- leesteken verwijderen (kan het woord verpesten als er iets met ' of - staat);
					 * 	- Filteren op leestekens, bij sommige leestekens(,!?) alleen leesteken verwijderen en bij anderen('-_@) hele woord verwijderen;
					 */
				}
			}
		}
		System.out.println("\n" +
                "TOTAAL: " + aantalWoorden + " woorden met leestekens in deze file");
		return toLower;
	}
	
}
