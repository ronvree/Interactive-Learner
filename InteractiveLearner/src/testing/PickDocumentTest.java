package testing;

import model.Document;
import model.documentprocessor.DocumentProcessor;
import model.user.HumanUser;
import model.user.User;

public class PickDocumentTest {
	
	public static void main(String[] args) {
		
		User user = new HumanUser();
		
		Document doc = user.pickDocument();
		DocumentProcessor.normalize(DocumentProcessor.tokenize(doc));
		
		
	}
	
}
