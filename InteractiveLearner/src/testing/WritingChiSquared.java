package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import model.featureselection.ChiSquared;
import model.featureselection.FeatureSelection;
import model.processeddocumentset.BinomialDataSet;
import model.processeddocumentset.ProcessedDocumentSet;

public class WritingChiSquared extends ChiSquared {
	
	private static final String STANDARD = "UTF-8";
	private static final String FILENAME = "src/chi-words.txt";
	private static final String LINE = "--------------------------------------------------------------------";
	
	private static final int THRESHOLD = 0;
	
	private PrintWriter writer;
	
	public WritingChiSquared(ProcessedDocumentSet dataSet) {
		super(dataSet);
		try {
			writer = new PrintWriter(FILENAME ,STANDARD);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.println("N: 		Number of files with this category.");
		writer.println("A: 		Number of files with word in category");
		writer.println("B: 		Number of files with word but not in category");
		writer.println("Score: 	Chi Squared value");
		writer.println();
		writer.printf("%-10s %-10s %-10s %-10s %-10s\n", "N", "A", "B", "score", "word");
		writer.println(LINE);
		writer.flush();
	}
	
	@Override
	public int score(String word, String classification) {
		double N = this.dataSet.size(classification);					// he total number of the documents that belong to category
		double A = this.dataSet.getFrequency(word, classification);		// the number of the documents that contain the word and also belong to category
		double B = this.dataSet.getFrequency(word) - A;					// the number of the documents that contain the word but do not belong to category
		double score = (2*N*(A-B)*(A-B))/((A + B)*((2*N)-(A+B)));
//		System.out.println(N + "\t" + A + "\t" + B + "\t" + score + "\t" + word);
		if (score >= THRESHOLD)	{
			writer.printf("%-10s %-10s %-10s %-10.3f %-10s\n", N, A, B, score, word);
			writer.flush();
		}
		return (int) score ;
	}
	
	public static void main(String[] args) {
		String location = "src/dataset/";
		String c1 = "ham";
		String c2 = "spam";
		ProcessedDocumentSet dataset = new BinomialDataSet(new File(location + c1), new File(location + c2));
		FeatureSelection fs = new WritingChiSquared(dataset);
		fs.update();
	}

}
