package testing;

import java.io.File;
import java.util.List;

import model.featureselection.DocumentFrequency;
import model.featureselection.FeatureSelection;
import model.processeddocumentset.BinomialDataSet;
import model.processeddocumentset.ProcessedDocumentSet;

public class TestFeatureSelection {

	public static void main(String[] args) {
		
		String location = "src/dataset/";
		String c1 = "ham";
		String c2 = "spam";
		
		ProcessedDocumentSet dataset = new BinomialDataSet(new File(location + c1), new File(location + c2));
		FeatureSelection fs = new DocumentFrequency(dataset);
		
		fs.update();
		
		List<String> selected = fs.getSelectedWords();
		for (String word : selected)	{
			System.out.println(word);
		}
		
	}

}
