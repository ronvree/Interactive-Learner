package controller;

import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSetv2;
import view.GUI;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Gijs on 06-Jan-16.
 */
public class Controller implements Serializable {
    public Controller() {
        GUI.buildGUI();
	}

	public static HashMap runTest(File first, String firstName, File second, String secondName, File test) {
        HashMap<String, String> result = new HashMap<>();
		long time = System.currentTimeMillis();
		BinomialDataSetv2 docset = new BinomialDataSetv2(first, firstName, second, secondName);
		Classifier NB = new NaiveBayes(firstName, secondName);
		NB.train(docset);
		int counter = 1;
		for (File file : test.listFiles()) {
			System.out.println("File " + counter + " of " + test.listFiles().length);
			counter++;
			System.out.println("Filename: " + file.getName());
			String classification = NB.classify(new StandardDocument(file));
			System.out.println();
            result.put(file.getName(), classification);
		}
        long calculateTime = System.currentTimeMillis() - time;
        result.put("Time", String.valueOf(calculateTime));
        double percentage = checkResult(result);
        result.put("Percentage", String.valueOf(percentage));
		return result;
	}


//    This is only applicable to the test sets we made, because these have their true classification in their filename.
//    This method is mainly used to easily verify our results.
    public static double checkResult(HashMap<String, String> result) {
        Pattern blog;
        Pattern mail1;
        Pattern mail2;
        HashMap<String, String> endResult = new HashMap<>();
        try {
            blog = Pattern.compile("/[FM].test[0-9]*/ ");
            mail1 = Pattern.compile("/[0-9].[0-9]*...[0-9]/ ");
            mail2 = Pattern.compile("/spmsg[abcd][0-9]*/ ");


        for (String key : result.keySet()) {
            String value = result.get(key);
            Matcher blogMatcher = blog.matcher(key);
            Matcher mail1Matcher = mail1.matcher(key);
            Matcher mail2Matcher = mail2.matcher(key);
            System.out.println("blogmatcher: " + blogMatcher.toString() + "\nBlogmatcher matches: " + blogMatcher.matches());
            System.out.println("\nmail1matcher: " + mail1Matcher.toString() + "\nmail1matcher matches: " + mail1Matcher.matches());
            System.out.println("\nmail2matcher: " + mail2Matcher.toString() + "\nmail2matcher matches: " + mail2Matcher.matches());
            if (blogMatcher.matches()) {
                if (key.startsWith("F")) {
                    if (value.startsWith("F")) {
                        endResult.put(key, "Y");
                    } else if (value.startsWith("M")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else if (key.startsWith("M")) {
                    if (value.startsWith("M")) {
                        endResult.put(key, "Y");
                    } else if (value.startsWith("F")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else {
                    System.out.println("Fout bij checken van het resultaat van file: " + key);
                }
            } else if (mail1Matcher.matches() || mail2Matcher.matches()) {
                if (mail1Matcher.matches()) {
                    if (value.startsWith("H")) {
                        endResult.put(key, "Y");
                    } else if (value.startsWith("S")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else if (mail2Matcher.matches()) {
                    if (value.startsWith("S")) {
                        endResult.put(key, "Y");
                    } else if (value.startsWith("H")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else {
                    System.out.println("Fout bij checken van het resultaat van file: " + key);
                }
            }
        }
        } catch (PatternSyntaxException e) {
            System.out.println(e.getMessage());
        }
        double percentage;
        double correct = 0;
        double incorrect = 0;
        for (String key : endResult.keySet()) {
            if (endResult.get(key).equals("Y")) {
                correct++;
            } else if (endResult.get(key).equals("N")) {
                incorrect++;
            }
        }

        percentage = correct/(correct+incorrect);
        System.out.println("Percentage goed: " + percentage);
        System.out.println("Correct: " + correct);
        System.out.println("Incorrect: " + incorrect);
        return percentage;
    }

	public static void main(String[] args) {
        Controller c = new Controller();

	}


}
