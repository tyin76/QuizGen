package ui.tabs;

import ui.QuizGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents HomeTab on UI
public class HomeTab extends Tab {

    private JLabel welcomeMsg;
    private JButton quitButton;


    // MODIFIES: this
    // EFFECTS: constructs HomeTab with Quit Button and greeting
    public HomeTab() {
        quitButton = new JButton("Quit QuizGen");
        quitButton.setPreferredSize(new Dimension(100, 50));
        setLayout(new GridLayout(10,1));
        placeGreeting();
        this.add(quitButton);
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

    }

    // MODIFIES: this
    // EFFECTS: creates welcome message JLabel and adds it
    private void placeGreeting() {
        welcomeMsg = new JLabel("Welcome to QuizGen!", JLabel.CENTER);
        welcomeMsg.setSize(500, 100);
        this.add(welcomeMsg);
    }
}