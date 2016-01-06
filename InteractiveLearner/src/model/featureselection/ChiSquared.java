package model.featureselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.processeddocumentset.ProcessedDocumentSet;

public class ChiSquared extends FeatureSelection {
	
	public static final int THRESHOLD = 5;
	
	public ChiSquared(ProcessedDocumentSet dataSet) {
		super(dataSet);
	}

	@Override
	public int score(String word, String classification) {
		double N = this.dataSet.size(classification);					// he total number of the documents that belong to category
		double A = this.dataSet.getFrequency(word, classification);		// the number of the documents that contain the word and also belong to category
		double B = this.dataSet.getFrequency(word) - A;					// the number of the documents that contain the word but do not belong to category
		double score = (2*N*(A-B)*(A-B))/((A + B)*((2*N)-(A+B)));
//		System.out.println(N + "\t" + A + "\t" + B + "\t" + score + "\t" + word);
		return (int) score ;
	}

	@Override
	public List<String> select(Map<String, Integer> scores) {
		List<String> result = new ArrayList<String>();
		for (String word : scores.keySet())	{
			if (scores.get(word) > THRESHOLD)	{
				result.add(word);
			}
		}
		return result;
	}
	
}
