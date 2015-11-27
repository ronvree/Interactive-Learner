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
		// TODO Auto-generated method stub
		return null;
	}

}
