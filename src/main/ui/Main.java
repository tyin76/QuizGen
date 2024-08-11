package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// main class that runs QuizGen
public class Main {
    public static void main(String[] args) {
        try {
            new QuizGen();
        } catch (FileNotFoundException e) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Unable to run application: file not found :(");
        }
    }
}