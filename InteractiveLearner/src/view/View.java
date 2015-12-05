package view;

import model.Document;

public interface View {
	
	public Document pickDocument();
	
	public boolean classify(Document document, String classification);
	
	public void update();
	
}
