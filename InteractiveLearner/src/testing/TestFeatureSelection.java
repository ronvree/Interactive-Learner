package testing;

import java.io.File;
import java.util.List;

import model.featureselection.ChiSquared;
import model.featureselection.FeatureSelection;
import model.processeddocumentset.BinomialDataSetv3;
import model.processeddocumentset.ProcessedDocumentSet;

public class TestFeatureSelection {

	public static void main(String[] args) {
		
		String location = "src/dataset/";
		String c1 = "ham";
		String c2 = "spam";
		
		ProcessedDocumentSet dataset = new BinomialDataSetv3(new File(location + c1), new File(location + c2));
		FeatureSelection fs = new ChiSquared(dataset);
		
		fs.update();
		
		List<String> selected = fs.getSelectedWords("spam");
		for (String word : selected)	{
			System.out.println(word);
		}
		
	}

}
