package model.comparator;

import model.Document;
import model.user.User;

public class StandardComparator implements Comparator {
	
	private User user;
	
	public StandardComparator(User user)	{
		this.user = user;
	}
	
	@Override
	public String compare(Document document, String classification) {
		boolean correct = this.user.classify(document, classification);
		String correctClassification = "";
//		if (correct) {
//			correctClassification = classification;
//		} else {
//            correctClassification = classification.other(classification);
//        }
		return correctClassification;
	}

}
