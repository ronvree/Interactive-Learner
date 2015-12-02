package model.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

import model.Classification;
import model.Document;

public class HumanUser implements User {
	
	private static final String CONFIRMATION = "yes";
	private static final String DISAGREE = "no";
	
	private static final JFileChooser fc = new JFileChooser();
	
	@Override
	public String getInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	@Override
	public Document pickDocument() {
		StandardDocument doc = null;
		int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            doc = new StandardDocument(file);
        }
		return doc;
	}

	@Override
	public boolean classify(Document document, Classification classification) {
		boolean valid = false;
		String input = "";
		do {
			input = this.getInput();
			valid = input.equals(CONFIRMATION) || input.equals(DISAGREE);
		} while (!valid);
		return input.equals(CONFIRMATION);
	}

}
