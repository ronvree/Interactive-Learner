package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;


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

    String choosertitle;

    public GUI() {
        firstClass = new JButton("Choose first train folder");
        secondClass = new JButton("Choose second train folder");
        testClass = new JButton("Choose folder to test");

        firstClassName = new JTextField("Please enter first class name");
        secondClassName = new JTextField("Please enter second class name");

        runTest = new JButton("Run test!");

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

        firstClassName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstClassNameValue = firstClassName.getText();
            }
        });

        secondClassName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondClassNameValue = secondClassName.getText();
            }
        });

        add(firstClass);
        add(firstClassName);

        add(secondClass);
        add(secondClassName);

        add(testClass);
        add(runTest);

        firstClass.setVisible(true);
        secondClass.setVisible(true);
        testClass.setVisible(true);
        firstClassName.setVisible(true);
        secondClassName.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(runTest.getActionCommand())) {
            if (firstClassFolder != null && secondClassFolder != null && testClassFolder != null && firstClassName != null && secondClassName != null) {
                System.out.println("First: " + firstClassFolder);
                System.out.println("first name: " + firstClassNameValue);

                System.out.println("Second: " + secondClassFolder);
                System.out.println("second name: " + secondClassNameValue);

                System.out.println("test: " + testClassFolder);



            } else {
//            TODO
            }
        } else if (e.getActionCommand().equals(firstClass.getActionCommand())) {
            choosertitle = "First folder";
            firstClassFolder = chooseFolder();
        } else if (e.getActionCommand().equals(secondClass.getActionCommand())) {
            choosertitle = "Second folder";
            secondClassFolder = chooseFolder();
        } else if (e.getActionCommand().equals(testClass.getActionCommand())) {
            choosertitle = "Test folder";
            testClassFolder = chooseFolder();
        }
    }

    public String chooseFolder() throws NullPointerException {
        File result = null;
        JFileChooser chooser;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
           result = chooser.getSelectedFile();
        }
        else {
            System.out.println("No Selection");
        }
        return result.toString();
    }

    public Dimension getPreferredSize(){
        return new Dimension(500, 500);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("CLASSIFIER");
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
        frame.setVisible(true);
    }
}