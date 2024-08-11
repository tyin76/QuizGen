package ui;


import model.Event;
import model.EventLog;
import model.Question;
import model.Set;
import persistence.JSonReader;
import persistence.JSonWriter;
import ui.tabs.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Quiz Generator application
public class QuizGen extends JFrame {

// GUI FIELDS
    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    private JTabbedPane menuBar;
    private static final int homeTabIndex = 0;
    private static final int addTabIndex = 1;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;
    private EventLog eventLog;

    // MODIFIES: this
    // EFFECTS: runs the QuizGen application creating a GUI with JFrame, JPanel, JLabel and menuBar
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public QuizGen() throws FileNotFoundException {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setTitle("QuizGen");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(1000,1000,1000,1000));
        panel.setLayout(new FlowLayout());

        label = new JLabel("Welcome to QuizGen!");
        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);

        menuBar = new JTabbedPane();
        menuBar.setTabPlacement(JTabbedPane.LEFT);
        loadTabs();

        frame.add(menuBar);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        eventLog = EventLog.getInstance();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event event : eventLog) {
                    System.out.println(event.getDate() + ": " + event.getDescription());
                }
                eventLog.clear();
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: adds tabs to this UI
    private void loadTabs() throws FileNotFoundException {
        JPanel homeTab = new HomeTab();
        JPanel addTab = new QuizGenTab();

        menuBar.add(homeTab, homeTabIndex);
        menuBar.setTitleAt(homeTabIndex, "Home");
        menuBar.add(addTab, addTabIndex);
        menuBar.setTitleAt(addTabIndex, "QuizGen");
    }
}