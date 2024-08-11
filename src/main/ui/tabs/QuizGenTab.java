package ui.tabs;

import model.Question;
import model.Set;
import persistence.JSonReader;
import persistence.JSonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class represents QuizGen Tab and the implementations
public class QuizGenTab extends Tab {

    private JTextField userInputQuestionTextField;
    private JTextField userInputAnswerTextField;
    private JTextField userInputQuestionNumberTextField;


    private JLabel inputQuestionInstructionLabel;
    private JLabel inputAnswerInstructionLabel;
    private JLabel inputQuestionNumberInstructionLabel;
    private JTextArea textArea;
    private JButton deleteButton;

    private JButton viewButton;
    private JButton submitButton;

    private String takeUserInputQuestion;
    private String takeUserInputAnswer;
    private String takeUserInputQuestionNumber;

    protected Set set;
    private int score;

    private static final String JSON_STORE = "./data/QuizGen.json";
    private JSonWriter jsonWriter;
    private JSonReader jsonReader;

    // EFFECTS: constructs QuizGen Tab with an empty set
    public QuizGenTab() {

        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);

        set = new Set();

        submitButton = new JButton("Add");

        setQuestionUserInput();
        setAnswerUserInput();
        setQuestionNumberUserInput();

        submitButton.addActionListener(e -> {
            createAndAddQuestion();
            JOptionPane.showMessageDialog(new JFrame(), "Question successfully added");
        });

        viewTabInPopUp();
        this.add(submitButton);

        deleteQuestion();
        changeQuestion();
        takeQuiz();
        seeScore();
        saveSet();
        loadSet();


    }

    // MODIFIES: this, set
    // EFFECTS: creates and adds deleteButton to QuizGen Tab
    //          if Set is not empty, deletes the question at the user specified index
    //          otherwise, a message dialog box is displayed
    private void deleteQuestion() {
        deleteButton = new JButton("Delete Question");
        this.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (set.getQuestion().size() == 0) {
                    JFrame emptySetFrame = new JFrame();
                    JOptionPane.showMessageDialog(emptySetFrame, "Cannot Delete since Set is Empty");
                } else {
                    String deleteIndex = JOptionPane.showInputDialog("Please select the index of"
                            + " the question you would like to delete");
                    int convertDeleteIndex = Integer.parseInt(deleteIndex);
                    if (convertDeleteIndex > set.getQuestion().size() - 1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Index # is not valid" + "\n"
                                + "Please try again");
                    } else {
                        //set.getQuestion().remove(convertDeleteIndex);
                        set.removeQuestion(convertDeleteIndex);
                        JFrame emptySetFrame = new JFrame();
                        JOptionPane.showMessageDialog(emptySetFrame, "Question successfully deleted");
                    }
                }
            }
        });
    }



    // MODIFIES: this, set
    // EFFECTS: creates and adds changeButton to QuizGen Tab
    //          if Set is not empty, changes the question to user specified input
    //          otherwise, a message dialog box is displayed
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void changeQuestion() {
        JButton changeButton = new JButton("Change Question");
        this.add(changeButton);
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (set.getQuestion().size() == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Cannot change question"
                            + "\n" + "Set is Empty");
                } else {
                    String changeQuestionIndex = JOptionPane.showInputDialog("Please select the index #"
                            + " of the question you would like to change");
                    int convertChangeQuestionIndex = Integer.parseInt(changeQuestionIndex);
                    if (convertChangeQuestionIndex > set.getQuestion().size() - 1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Index # is not valid" + "\n"
                                + "Please try again");
                    } else {
                        String newQuestion = JOptionPane.showInputDialog("Please input the new question:");
                        String newAnswer = JOptionPane.showInputDialog("Please input the new answer:");
                        String newQuestionNumber = JOptionPane.showInputDialog("Please input the new question number");
                        Question newChangedQuestion = new Question(newQuestion, newAnswer, newQuestionNumber);
                        //set.getQuestion().set(convertChangeQuestionIndex, newChangedQuestion);
                        set.changeQuestion(convertChangeQuestionIndex, newChangedQuestion);
                        JOptionPane.showMessageDialog(new JFrame(), "Question successfully changed");
                    }
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates and adds JLabel and JTextField to QuizGen Tab
    private void setQuestionNumberUserInput() {
        inputQuestionNumberInstructionLabel = new JLabel("Please input a number associated with this question");
        userInputQuestionNumberTextField = new JTextField(20);

        this.add(inputQuestionNumberInstructionLabel);
        this.add(userInputQuestionNumberTextField);
    }


    // MODIFIES: this
    // EFFECTS: creates and adds JLabel and JTextField to QuizGen Tab
    private void setAnswerUserInput() {
        inputAnswerInstructionLabel = new JLabel("Please input an answer for the question");
        userInputAnswerTextField = new JTextField(30);

        this.add(inputAnswerInstructionLabel);
        this.add(userInputAnswerTextField);

    }

    // MODIFIES: this
    // EFFECTS: creates and adds JLabel and JTextField to QuizGen Tab
    private void setQuestionUserInput() {
        inputQuestionInstructionLabel = new JLabel("Please input a question");
        userInputQuestionTextField = new JTextField(30);

        this.add(inputQuestionInstructionLabel);
        this.add(userInputQuestionTextField);

    }

    // MODIFIES: set
    // EFFECTS: takes user input from the three text fields
    //          composes the three text fields into a question object
    //          if the question object is valid, adds the question to the set
    //          otherwise, the question is not added and the set does not change
    private void createAndAddQuestion() {
        takeUserInputQuestion = userInputQuestionTextField.getText();
        takeUserInputAnswer = userInputAnswerTextField.getText();
        takeUserInputQuestionNumber = userInputQuestionNumberTextField.getText();

        Question newQuestion = new Question(takeUserInputQuestion, takeUserInputAnswer, takeUserInputQuestionNumber);
        if (takeUserInputAnswer != null && takeUserInputAnswer != null && takeUserInputQuestionNumber != null) {
            set.addQuestionToSet(newQuestion);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a JTextArea and displays the current set in it
    private void displaySet() {
        textArea = new JTextArea();
        for (Question question : set.getQuestion()) {
            textArea.append(question.getQuestionNumber() + ".) " + "Question = " + question.getQuestion()
                    + " / Answer = " + question.getAnswer() + "\n");
        }
        this.add(textArea);
        textArea.setVisible(true);
    }

    // MODIFIES: this, score
    // EFFECTS: creates and adds quizButton to QuizGen Tab and updates score
    //          if set is not empty, prompts user to take the quiz based on the current set
    //          otherwise, quiz cannot be taken and a message dialog box is shown
    //          displays a green checkmark when question is answered correctly
    //          displays a red X when question is answered incorrectly
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void takeQuiz() {
        JButton quizButton = new JButton("Take Quiz");
        this.add(quizButton);
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (set.getQuestion().size() == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Cannot take Quiz" + "\n" + "Set is Empty");
                } else {
                    String questionPicked = JOptionPane.showInputDialog("Select the index # of the question to answer");
                    int convertQuestionPicked = Integer.parseInt(questionPicked);
                    if (convertQuestionPicked > set.getQuestion().size() - 1) {
                        JOptionPane.showMessageDialog(new JFrame(), "Index # is not valid"
                                + "\n" + "Please try again");
                    } else {
                        String question = set.getQuestion().get(convertQuestionPicked).getQuestion();
                        String answer = set.getQuestion().get(convertQuestionPicked).getAnswer();
                        String questionAnswer = JOptionPane.showInputDialog(question);
                        if (questionAnswer.equals(answer)) {
                            addToScore();
                            ImageIcon greenCheckMark = new ImageIcon("transparent green checkmark medium.png");
                            JOptionPane.showMessageDialog(null, "Correct!",
                                    "Nice Job!", JOptionPane.INFORMATION_MESSAGE, greenCheckMark);
                        }
                        if (!questionAnswer.equals(answer)) {
                            ImageIcon redX = new ImageIcon("transparent red x.png");
                            JOptionPane.showMessageDialog(null, "Incorrect!",
                                    "Nice Try!", JOptionPane.INFORMATION_MESSAGE, redX);
                        }
                    }
                }
            }
        });
    }

    // MODIFIES: score
    // EFFECTS: adds one to score
    private int addToScore() {
        return score++;
    }

    // MODIFIES: this
    // EFFECTS: adds and creates seeScoreButton to QuizGen Tab
    //          shows current score in a message dialog box
    private void seeScore() {
        JButton seeScoreButton = new JButton("See Score");
        this.add(seeScoreButton);
        seeScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Your score is: " + score);
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: creates and adds viewPopUpButton to QuizGen Tab
    //          if the set is not empty, displays set in a message dialog box
    //          otherwise, a message dialog box with an error message is displayed
    private void viewTabInPopUp() {
        JButton viewPopUpButton = new JButton("View Set");
        this.add(viewPopUpButton);
        viewPopUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (set.getQuestion().size() == 0) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "There are no questions in the Set");
                } else {
                    List<String> popUpQuestionList = new ArrayList<>();
                    for (Question question : set.getQuestion()) {
                        popUpQuestionList.add(question.getQuestionNumber() + ".) "
                                + "Question = " + question.getQuestion()
                                + " / Answer = " + question.getAnswer() + "\n");
                    }
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, popUpQuestionList);
                }
            }
        });
    }

    // EFFECTS: saves the set to file
    public void saveSet() {
        JButton saveButton = new JButton("Save Set");
        this.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(set);
                    jsonWriter.close();
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Saved all questions to: " + JSON_STORE);
                } catch (FileNotFoundException ex) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads set from file
    public void loadSet() {
        JButton loadButton = new JButton("Load Set");
        this.add(loadButton);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    set = jsonReader.read();
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Loaded questions from" + JSON_STORE);
                } catch (IOException ex) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,"Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }
}