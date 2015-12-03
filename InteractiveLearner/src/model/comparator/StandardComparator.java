package model.comparator;

import model.Classification;
import model.Document;
import model.user.User;

public class StandardComparator implements Comparator {
	
	private User user;
	
	public StandardComparator(User user)	{
		this.user = user;
	}
	
	@Override
	public Classification compare(Document document, Classification classification) {
		boolean correct = this.user.classify(document, classification);
		Classification correctClassification;
		if (correct) {
			correctClassification = classification;
		} else {
            correctClassification = classification.other(classification);
        }
		return correctClassification;
	}

}
