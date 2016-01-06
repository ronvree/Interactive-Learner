package testing;

import model.classifiers.Classifier;
import model.classifiers.FSNaiveBayes;
import model.classifiers.NaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSet;
import view.GUI;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Gijs on 06-Jan-16.
 */
public class CompleteTest implements Serializable {
    public CompleteTest() {
        GUI.buildGUI();
    }

    public static HashMap runTest(File first, String firstName, File second, String secondName, File test, boolean useFeatureSelection, boolean removeStopWords) {
        HashMap<String, String> result = new HashMap<>();
        long time = System.currentTimeMillis();
        BinomialDataSet docset = new BinomialDataSet(first, firstName, second, secondName);
        long dataSetCreatedIn = System.currentTimeMillis() - time;
        Classifier NB;
        if (useFeatureSelection) {
            NB = new FSNaiveBayes(firstName, secondName, removeStopWords);
        } else {
            NB = new NaiveBayes(firstName, secondName);
        }
        time = System.currentTimeMillis();
        NB.train(docset);
        long trainTime = System.currentTimeMillis() - time;
        int counter = 1;
        time = System.currentTimeMillis();
        for (File file : test.listFiles()) {
            System.out.println("File " + counter + " of " + test.listFiles().length);
            counter++;
            System.out.println("Filename: " + file.getName());
            String classification = NB.classify(new StandardDocument(file));
            System.out.println();
            result.put(file.getName(), classification);
        }
        long classificationTime = System.currentTimeMillis() - time;
        result.put("Dataset instantiated in ms: ", String.valueOf(dataSetCreatedIn));
        result.put("Trained in ms: ", String.valueOf(trainTime));
        result.put("Classified in ms: ", String.valueOf(classificationTime));
        double percentage = checkResult(result);
        result.put("Percentage", String.valueOf(percentage));
        return result;
    }


    //    This is only applicable to the test sets we made, because these have their true classification in their filename.
//    This method is mainly used to easily verify our results.
    public static double checkResult(HashMap<String, String> result) {
        HashMap<String, String> endResult = new HashMap<>();
        boolean mail = false;
        boolean blog = false;
        for (String key : result.keySet()) {
            String value = result.get(key);
            if (key.contains("msg")) {
                mail = true;
            } else if (key.contains("F") || key.contains("M")) {
                blog = true;
            }
            if (blog) {
                if (key.startsWith("F")) {
                    if (value.toLowerCase().contains("female")) {
                        endResult.put(key, "Y");
                    } else if (value.toLowerCase().contains("male")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else if (key.startsWith("M")) {
                    if (value.toLowerCase().contains("male")) {
                        endResult.put(key, "Y");
                    } else if (value.toLowerCase().contains("female")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else {
                    System.out.println("Fout bij checken van het resultaat van file: " + key);
                }
            } else if (mail) {
                if (key.contains("sp")) {
                    System.out.println("key.contains(\"p\"): " + key.contains("p"));
                    if (value.toLowerCase().contains("s")) {
                        endResult.put(key, "Y");
                    } else if (value.toLowerCase().contains("h")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else if (!key.contains("sp")) {
                    System.out.println("!key.contains(\"p\"): " + !key.contains("p"));
                    if (value.toLowerCase().contains("h")) {
                        endResult.put(key, "Y");
                    } else if (value.toLowerCase().contains("s")) {
                        endResult.put(key, "N");
                    } else {
                        endResult.put(key, "U");
                    }
                } else {
                    System.out.println("Fout bij checken van het resultaat van file: " + key);
                }
            }
            System.out.println("\nBlog: " + blog
                    + "\nMail: " + mail
                    + "\nKey: " + key + " value: " + value
                    + "\nCorrect?: " + endResult.get(key));
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

        percentage = (correct/(correct+incorrect)) * 100;
        System.out.println("Percentage correct: " + percentage + "%");
        System.out.println("Correct: " + correct);
        System.out.println("Incorrect: " + incorrect);
        return percentage;
    }

    public static void main(String[] args) {
        CompleteTest c = new CompleteTest();

    }


}
