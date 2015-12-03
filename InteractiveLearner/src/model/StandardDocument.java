package model;

import java.io.*;

public class StandardDocument implements Document {

    private String text;
    private Classification classification = null;

    public StandardDocument(File file) {
        char[] buffer = new char[(int) file.length()];
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.read(buffer, 0, (int) file.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = new String(buffer);
    }

    @Override
    public String getDocument() {
        return text;
    }
    public void setClassification(Classification classification) {
        this.classification = classification;
    }
    public Classification getClassification() {
        return this.classification;
    }
}
