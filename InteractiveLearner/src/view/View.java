package view;

import model.document.Document;

public interface View {
	
	public Document pickDocument();
	
	public boolean classify(Document document, String classification);
	
	public void update();
	
}
