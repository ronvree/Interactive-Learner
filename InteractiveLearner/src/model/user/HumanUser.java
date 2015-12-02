package model.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Classification;
import model.Document;

public class HumanUser implements User {
	
	private static final String moveInputFormat = "TBA"; // TODO
	private static final Pattern regex = Pattern.compile(moveInputFormat);
	
	@Override
	public String getInput() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		boolean valid = false;
		do {
			try {
				input = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Matcher matcher = regex.matcher(input);
			valid = matcher.matches();
		} while (!valid);
		return input;
	}

	@Override
	public Document pickDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean classify(Document document, Classification classification) {
		// TODO Auto-generated method stub
		return false;
	}

}
