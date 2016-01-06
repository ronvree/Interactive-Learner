package model.featureselection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.processeddocumentset.ProcessedDocumentSet;

public abstract class FeatureSelection {
	
	protected ProcessedDocumentSet dataSet;
	protected Map<String, Map<String, Integer>> scores;
	
	public FeatureSelection(ProcessedDocumentSet dataSet)	{
		this.dataSet = dataSet;
		this.scores = new HashMap<String, Map<String, Integer>>();
		for (String classification : dataSet.getClassifications())	{
			scores.put(classification, new HashMap<String, Integer>());
		}
	}
	
	public void update()	{
		List<String> words = this.dataSet.getWords();
		for (String classification : scores.keySet())	{
			for (String word : words)	{
				int score = this.score(word, classification);
				this.scores.get(classification).put(word, new Integer(score));
			}
		}
	}
	
	public void update(String word)	{
		for (String classification : dataSet.getClassifications())	{
			int score = this.score(word, classification);
			this.scores.get(classification).put(word, new Integer(score));
		}
	}
	
	public List<String> getSelectedWords(String classification)	{
		return this.select(this.scores.get(classification));
	}
	
	public abstract int score(String word, String classification);
	
	public abstract List<String> select(Map<String, Integer> scores);
	
}
