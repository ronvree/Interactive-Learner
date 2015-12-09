package model.classifiers;

import model.document.Document;
import model.document.processed.ProcessedDocument;
import model.documentprocessor.DocumentProcessor;
import model.processeddocumentset.ProcessedDocumentSet;

public class NaiveBayes implements Classifier {

	private ProcessedDocumentSet kb;
	private String classification1;
	private String classification2;

	public NaiveBayes(String classification1, String classification2) {
		this.classification1 = classification1;
		this.classification2 = classification2;
	}

	@Override
	public String classify(Document document) {
		String[] normalized = DocumentProcessor.normalize(document);

		int change = calculate(normalized);
		return null;
	}

	@Override
	public void train(ProcessedDocumentSet documents) {
		kb = documents;
	}

	public int calculate(String[] normalized) {
		int wordInClass1 = 0;
		int wordInClass2 = 0;
		int totalFreqWord = 0;

		int changeWordGivenClassification;
		int changeWord;
		int changeClassification;
		int changeClassificationGivenWord;
		int freqInKB = 0;

		int totalChangeClassificationGivenWord = 0;

		for (String word : normalized) {
			totalFreqWord += kb.getFrequency(word);
			wordInClass1 += kb.getFrequency(word, classification1);
			wordInClass2 += kb.getFrequency(word, classification2);

			freqInKB  = kb.getFrequency(word);
			changeWordGivenClassification = kb.getFrequency(word, classification1) / kb.wordCount(classification1);
			changeWord = wordInClass1 / freqInKB;
			changeClassification = kb.wordCount(classification1) / kb.wordCount();

			changeClassificationGivenWord = changeWordGivenClassification / changeWord * changeClassification;

			totalChangeClassificationGivenWord += changeClassificationGivenWord;

		}

		int change = totalChangeClassificationGivenWord / normalized.length;

		System.out.println("Change: " + change);

		return change;
	}

}
