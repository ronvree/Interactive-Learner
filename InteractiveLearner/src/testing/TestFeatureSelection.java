package testing;

import java.io.File;
import java.util.List;

import model.featureselection.FeatureSelection;
import model.processeddocumentset.BinomialDataSet;
import model.processeddocumentset.ProcessedDocumentSet;

public class TestFeatureSelection {

	public static void main(String[] args) {
		
		String location = "src/dataset/";
		String c1 = "ham";
		String c2 = "spam";
		
		ProcessedDocumentSet dataset = new BinomialDataSet(new File(location + c1), new File(location + c2));
		FeatureSelection fs = new WritingChiSquared(dataset);
		
		fs.update();
		
		List<String> selected1 = fs.getSelectedWords(c1);
		List<String> selected2 = fs.getSelectedWords(c2);
		for (String word : selected1)	{
			System.out.println(word);
		}
		for (String word : selected2)	{
			System.out.println(word);
		}
		
	}

}
