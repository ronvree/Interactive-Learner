package view;

import model.Classification;
import model.Document;

public interface View {
	
	public Document pickDocument();
	
	public boolean classify(Document document, Classification classification);
	
	public void update();
	
}
