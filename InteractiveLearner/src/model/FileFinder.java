package model;

/**
 * Not used
 */



import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileFinder {

    private String fileName;
    private String pathName;
    private File file;
    private long size;

    public FileFinder() {

    }

    public void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".txt")
                        || f.isDirectory();
            }

            public String getDescription() {
                return "Text files";
            }
        });

        int r = chooser.showOpenDialog(new JFrame());
        if (r == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getName();
            pathName = chooser.getSelectedFile().getAbsolutePath();
            file = chooser.getSelectedFile();
            size = chooser.getSelectedFile().length();
        }
    }

    public String getFileName(){
        return fileName;
    }

    public String getFilePath(){
        return pathName;
    }

    public File getFile() {
        return file;
    }

    public long getSize() {
        return size;
    }

}