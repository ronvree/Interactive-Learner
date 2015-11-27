package view;

import model.Classification;
import model.Document;

public interface View {
	
	public Document pickDocument();
	
	public Classification classify(Document document);
	
	public void update();
	
}
