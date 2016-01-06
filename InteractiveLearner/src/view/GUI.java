package view;

import controller.Controller;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;


public class GUI extends JPanel implements ActionListener {
    JButton firstClass;
    String firstClassFolder;
    JButton secondClass;
    String secondClassFolder;
    JButton testClass;
    String testClassFolder;
    JButton runTest;
    JTextField firstClassName;
    JTextField secondClassName;
    String firstClassNameValue;
    String secondClassNameValue;
    JCheckBox featureSelection;
    JCheckBox removeStopWords;

    String chooserTitle;

    HashMap<String, String> result;

    public GUI() {
        firstClass = new JButton("Choose first train folder");
        secondClass = new JButton("Choose second train folder");
        testClass = new JButton("Choose folder to test");

        firstClassName = new JTextField("Please enter first class name");
        secondClassName = new JTextField("Please enter second class name");

        runTest = new JButton("Run test!");
        featureSelection = new JCheckBox("Use feature selection", true);
        removeStopWords = new JCheckBox("Remove stop words", true);

        firstClass.addActionListener(this);
        secondClass.addActionListener(this);
        testClass.addActionListener(this);
        runTest.addActionListener(this);

        firstClassName.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                firstClassName.setText("");
            }
        });
        secondClassName.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                secondClassName.setText("");
            }
        });

        add(firstClass);
        add(firstClassName);

        add(secondClass);
        add(secondClassName);

        add(testClass);
        add(featureSelection);
        add(removeStopWords);
        add(runTest);

        firstClass.setVisible(true);
        secondClass.setVisible(true);
        testClass.setVisible(true);
        firstClassName.setVisible(true);
        secondClassName.setVisible(true);
        featureSelection.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(runTest.getActionCommand())) {
            firstClassNameValue = firstClassName.getText();
            secondClassNameValue = secondClassName.getText();
            if (firstClassFolder != null && secondClassFolder != null && testClassFolder != null && !firstClassNameValue.equals("") && !secondClassNameValue.equals("")) {
                System.out.println("First: " + firstClassFolder);
                System.out.println("first name: " + firstClassNameValue);

                System.out.println("Second: " + secondClassFolder);
                System.out.println("second name: " + secondClassNameValue);

                System.out.println("test: " + testClassFolder);

                result = Controller.runTest(new File(firstClassFolder), firstClassNameValue, new File(secondClassFolder), secondClassNameValue, new File(testClassFolder), featureSelection.isSelected(), removeStopWords.isSelected());
                showResults();
            } else {
                String message = "Not entered correct data!";
                JOptionPane.showMessageDialog(new JFrame(), message, "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals(firstClass.getActionCommand())) {
            chooserTitle = "First folder";
            firstClassFolder = chooseFolder();
        } else if (e.getActionCommand().equals(secondClass.getActionCommand())) {
            chooserTitle = "Second folder";
            secondClassFolder = chooseFolder();
        } else if (e.getActionCommand().equals(testClass.getActionCommand())) {
            chooserTitle = "Test folder";
            testClassFolder = chooseFolder();
        }
    }

    public void showResults() {
        JFrame frame = new JFrame("Results");

        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        String text = "Results:";
        for (String key : this.result.keySet()) {
            if (!(key.equals("Time") || key.equals("Percentage"))) {
                text += "\n" + key + ": " + this.result.get(key);
            }
        }
        text += "\nThis took: " + result.get("Time") + " milliseconds";
        text += "\nPercentage correctly classified: " + result.get("Percentage") + "%";
        System.out.println(text);

        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setSize(getPreferredSize());
        frame.setSize(getPreferredSize());
        frame.add(areaScrollPane);
        frame.setVisible(true);
        areaScrollPane.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    public String chooseFolder() throws NullPointerException {
        File result = null;
        try {
            JFileChooser chooser;
            chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(chooserTitle);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                result = chooser.getSelectedFile();
            } else {
                System.out.println("No Selection");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return result.toString();
    }

    public Dimension getPreferredSize(){
        return new Dimension(500, 200);
    }

    public static void buildGUI() {
        JFrame frame = new JFrame("Classifier, (c) Ron van Bree & Gijs Beernink 2016");
        GUI panel = new GUI();
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        frame.getContentPane().add(panel,"Center");
        frame.setSize(panel.getPreferredSize());
        frame.setResizable(false);
        frame.setVisible(true);
    }
}