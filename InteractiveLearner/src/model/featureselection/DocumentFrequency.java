package model.featureselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.processeddocumentset.ProcessedDocumentSet;

public class DocumentFrequency extends FeatureSelection {
	
	public static final int SELECTIONTHRESHOLD = 10;
	
	public DocumentFrequency(ProcessedDocumentSet dataSet) {
		super(dataSet);
	}

	@Override
	public int score(String word, String classification) {
		return this.dataSet.getFrequency(word);
	}

	@Override
	public List<String> select(Map<String, Integer> scores) {
		List<String> result = new ArrayList<String>();
		for (String word : scores.keySet())	{
			if (scores.get(word) > (scores.keySet().size()/SELECTIONTHRESHOLD))	{
				result.add(word);
			}
		}
		return result;
	}
	

}
