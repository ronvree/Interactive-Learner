package testing;

import model.classifiers.Classifier;
import model.classifiers.NaiveBayes;
import model.document.StandardDocument;
import model.processeddocumentset.BinomialDataSetv2;

import java.io.File;

/**
 * Created by Gijs on 06-Jan-16.
 */
public class testClassifierV2 {
    File first;
    File second;
    String firstName;
    String secondName;
    File test;


    public testClassifierV2(File first, String firstName, File second, String secondName, File test) {
        this.first = first;
        this.firstName = firstName;
        this.second = second;
        this.secondName = secondName;
        this.test = test;
    }

    public String runTest() {
        String result = "None";
        System.out.println("START");
        long time = System.currentTimeMillis();
        BinomialDataSetv2 docset = new BinomialDataSetv2(first, firstName, second, secondName);
        Classifier NB = new NaiveBayes(firstName, secondName);
        NB.train(docset);
        int counter = 1;
        for (File file : test.listFiles()) {
            System.out.println("File " + counter + " of " + test.listFiles().length);
            counter++;
            System.out.println("Filename: " + file.getName());
            NB.classify(new StandardDocument(file));
            System.out.println();
        }
        System.out.println("Done! This took: " + (System.currentTimeMillis() - time));
        return result;
    }


}
