package model.featureselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.processeddocumentset.ProcessedDocumentSet;

public class ChiSquared extends FeatureSelection {

	public ChiSquared(ProcessedDocumentSet dataSet) {
		super(dataSet);
	}

	@Override
	public int score(String word, String classification) {
		double N = this.dataSet.size(classification);
		double A = this.dataSet.getFrequency(word, classification);
		double B = N - A;
		double score = ((2*N*((A-B)*(A-B)))/((A + B)*((2*N)-(A+B))));
//		System.out.println(N + "\t" + A + "\t" + B + "\t" + score + "\t" + word);
		return (int) score ;
	}

	@Override
	public List<String> select(Map<String, Integer> scores) {
		List<String> result = new ArrayList<String>();
		for (String word : scores.keySet())	{
			if (scores.get(word) > 4800)	{
				result.add(word);
			}
		}
		return result;
	}
	
	
	
}
