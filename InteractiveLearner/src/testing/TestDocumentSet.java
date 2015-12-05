package testing;

import java.io.File;

import model.processeddocumentset.BinomialDataSet;

public class TestDocumentSet {

	public static void main(String[] args) {
		
		String corpusLocation = "src/corpus-mails/corpus/";
		File f1 = new File(corpusLocation + "part1");
		File f2 = new File(corpusLocation + "part2");
		BinomialDataSet docset = new BinomialDataSet(f1,f2);
		
	}

}
