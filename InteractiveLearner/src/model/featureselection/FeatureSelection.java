package model.featureselection;

import java.util.List;
import java.util.Map;

import model.processeddocumentset.ProcessedDocumentSet;

public abstract class FeatureSelection {
	
	protected ProcessedDocumentSet dataSet;
	protected Map<String, Integer> scores;
	
	public FeatureSelection(ProcessedDocumentSet dataSet)	{
		this.dataSet = dataSet;
		
	}
	
	public void update()	{
		List<String> words = this.dataSet.getWords();
		for (String word : words)	{
			int score = this.score(word);
			this.scores.put(word, new Integer(score));
		}
	}
	
	public void update(String word)	{
		int score = this.score(word);
		this.scores.put(word, new Integer(score));
	}
	
	public abstract int score(String word);
	
	public abstract List<String> select(Map<String, Integer> scores);
	
}
