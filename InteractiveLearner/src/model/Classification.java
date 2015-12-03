package model;

public enum Classification {
	
	SPAM, HAM, MALE, FEMALE;

	public Classification other(Classification classification) {
		Classification otherClassification = null;
		if (classification == SPAM) {
			otherClassification = HAM;
		} else if (classification == HAM) {
			otherClassification = SPAM;
		} else if (classification == MALE) {
			otherClassification = FEMALE;
		} else if (classification == FEMALE) {
			otherClassification = MALE;
		}
		return otherClassification;
	}
}
