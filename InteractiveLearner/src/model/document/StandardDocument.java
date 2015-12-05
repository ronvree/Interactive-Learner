package model.document;

import java.io.*;

public class StandardDocument implements Document {

    private String text;

    public StandardDocument(File file) {
        char[] buffer = new char[(int) file.length()];
        BufferedReader bufferedReader;
        try {
            FileReader reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
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
    
}
