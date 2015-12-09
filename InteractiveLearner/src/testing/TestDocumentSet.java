package testing;

import java.io.File;

import model.processeddocumentset.BinomialDataSet;

public class TestDocumentSet {

	public static void main(String[] args) {
		
		String corpusLocation = "src/dataset/";
		String c1 = "ham";
		String c2 = "spam";
		
		File f1 = new File(corpusLocation + c2);
		File f2 = new File(corpusLocation + c1);
		BinomialDataSet docset = new BinomialDataSet(f1,f2);
		
		System.out.println("Nr of files: " + docset.size());
		System.out.println("Nr of " + c1 + ": " + docset.countDocuments(c1));
		System.out.println("Nr of " + c2 + ": " + docset.countDocuments(c2));
		System.out.println();
		System.out.println("Nr of words: " + docset.wordCount());
		System.out.println("Freq of \"salary\": " + docset.getFrequency("salary"));
		System.out.println("Freq of \"salary\" in " + c2 + ": " + docset.getFrequency("salary", c2));
		System.out.println("Freq of \"salary\" in " + c1 + ": " + docset.getFrequency("salary", c1));
		
	}

}
