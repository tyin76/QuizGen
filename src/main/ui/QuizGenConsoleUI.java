package ui;

import model.Question;
import model.Set;
import persistence.JSonReader;
import persistence.JSonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class QuizGenConsoleUI {

    private Scanner input;
    private String command;
    private int score;
    private Set addedSet = new Set();
    private static final String JSON_STORE = "./data/QuizGen.json";
    private JSonWriter jsonWriter;
    private JSonReader jsonReader;

    // EFFECTS: runs the QuizGen application and sets the current user score to 0
    public QuizGenConsoleUI() throws FileNotFoundException {
        jsonWriter = new JSonWriter(JSON_STORE);
        jsonReader = new JSonReader(JSON_STORE);
        score = 0;
        runQuizGen();
    }

    public static void main(String[] args) {
        try {
            new QuizGenConsoleUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found :(");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user input
    public void runQuizGen() {
        boolean continueOn = true;
        command = null;

        init();

        while (continueOn) {
            displayMenu();
            command = input.next();

            if (command.equals("8")) {
                continueOn = false;
                System.out.println("GOODBYE, Thank you for using QuizGen!");
            } else {
                processCommand(command);
            }
        }
    }


    // EFFECTS: displays the possible menu items for the user to choose from
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add Question and Answer");
        System.out.println("\t2 -> Look at questions/answers in set");
        System.out.println("\t3 -> Take Quiz");
        System.out.println("\t4 -> Delete Question");
        System.out.println("\t5 -> Change Question");
        System.out.println("\t6 -> Save Set");
        System.out.println("\t7 -> Load Set");
        System.out.println("\t8 -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes Questions
    public void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("1")) {
            doAddQuestion();
        } else if (command.equals("2")) {
            doLookAtQuestions();
        } else if (command.equals("3")) {
            takeQuiz();
        } else if (command.equals("4")) {
            deleteQuestion();
        } else if (command.equals("5")) {
            changeQuestion();
        } else if (command.equals("6")) {
            saveSet();
        } else if (command.equals("7")) {
            loadSet();
        } else {
            System.out.println("Selection not valid...");
        }

    }

    // MODIFIES: this
    // EFFECTS: adds a Question to the set
    public void doAddQuestion() {
        System.out.println("Please enter the question you would like to add:");
        Scanner inputQuestion = new Scanner(System.in);
        inputQuestion.useDelimiter("\n");
        String takeQuestion = inputQuestion.next();

        System.out.println("Please provide the answer to the question you just submitted:");
        Scanner inputAnswer = new Scanner(System.in);
        inputAnswer.useDelimiter("\n");
        String takeAnswer = inputAnswer.next();

        System.out.println("Please input the number you want associated with this question entry:");
        Scanner inputNumber = new Scanner(System.in);
        inputNumber.useDelimiter("\n");
        String takeNumber = inputNumber.next();


        Question addQuestion = new Question(takeQuestion, takeAnswer, takeNumber);
        addedSet.addQuestionToSet(addQuestion);
    }

    // EFFECTS: displays the Questions that are currently in the set
    //          produces "There are no questions in this set. Add some!"
    //                    if no Questions are in the set
    public void doLookAtQuestions() {
        if (addedSet.getQuestion().size() == 0) {
            System.out.println("There are no questions in this set. Add some!");
        } else {
            for (Question q : addedSet.getQuestion()) {
                System.out.println(q.getQuestionNumber() + ".) " + "Question = " + q.getQuestion() + " / Answer = "
                        + q.getAnswer());
            }
        }
    }

    // EFFECTS: displays ONLY the question part for the Questions that are currently in the set
    //          produces "There are no questions in this set. Add some!"
    //                    if no Questions are in the set
    public void doLookAtQuestionQuizOnly() {
        if (addedSet.getQuestion().size() == 0) {
            System.out.println("There are no questions in this set. Add some!");
        } else {
            for (Question q : addedSet.getQuestion()) {
                System.out.println(q.getQuestionNumber() + ".) " + "Question = " + q.getQuestion());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds 1 to score
    public int addToScore() {
        return score++;
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    // REQUIRES: must have at least one Question in the set
    // MODIFIES: this
    // EFFECTS: starts the quiz for the user and updates their score
    //          returns the score once the user returns to the Main Menu
    public void takeQuiz() {

        doLookAtQuestionQuizOnly();
        System.out.println("Please select the index # of the question that you would like to answer OR" + "\n"
                + "Enter q to return to the Main Menu");

        Scanner quizQuestion = new Scanner(System.in);
        String takeQuizQuestion = quizQuestion.next();
        if (takeQuizQuestion.equals("q")) {
            System.out.println("Back to the Main Menu!");
            System.out.println("Your score = " + score);
        } else if (addedSet.getQuestion().size() == 0 && !takeQuizQuestion.equals("q")) {
            System.out.println("Please add questions!");
        } else {
            int convertTakeQuizQuestion = Integer.parseInt(takeQuizQuestion);

            //  if (convertTakeQuizQuestion == 100) {
            //      System.out.println("Back to the Main Menu!");
            //      System.out.println("Your score = " + score);
            //  }
            takeQuizShowOnlyQuestion(convertTakeQuizQuestion);
            System.out.println("Please input your answer to this question");
            Scanner quizAnswer = new Scanner(System.in);
            quizAnswer.useDelimiter("\n");
            String takeQuizAnswer = quizAnswer.next();
            String correctQuizAnswer = takeQuizGetOnlyAnswer(convertTakeQuizQuestion);

            if (takeQuizAnswer.equals(correctQuizAnswer)) {
                System.out.println("You are correct! :)");
                addToScore();
                takeQuiz();
            } else {
                System.out.println("That is incorrect :(, please try again");
                takeQuiz();
            }
        }
    }

    // REQUIRES: q to be a non-negative integer
    //           and to be within the list index range of the Questions in the set
    // EFFECTS: presents only the question portion of Questions in the set during a quiz
    //          at the specified index represented by q
    //          does not include the question number
    public void takeQuizShowOnlyQuestion(int q) {
        Question retreiveQuizQuestion =  addedSet.getQuestion().get(q);
        String showQuestionOnQuiz = retreiveQuizQuestion.getQuestion();

        System.out.println(showQuestionOnQuiz);
    }

    // REQUIRES: a to be a non-negative integer
    //           and to be within the list index range of the Questions in the set
    // EFFECTS: presents only the answer portion of Questions in the set during a quiz
    //          at the specified index represented by a
    //          does not include the question number
    public String takeQuizGetOnlyAnswer(int a) {
        Question retrieveQuizAnswer =  addedSet.getQuestion().get(a);
        String getOnlyAnswerForQuiz = retrieveQuizAnswer.getAnswer();

        return getOnlyAnswerForQuiz;
    }

    // REQUIRES: user to specify an index within the range of Questions in the set
    //           and must have at least one Question in the set
    // MODIFIES: this
    // EFFECTS: deletes a Question from the set at a specified index
    //          outputs "Question successfully deleted!" when finished
    public void deleteQuestion() {
        doLookAtQuestions();

        System.out.println("Please select the index # of the question that you would like to delete");
        Scanner inputDeleteNumber = new Scanner(System.in);
        String takeDeleteNumber = inputDeleteNumber.next();

        int convertDeleteNumber = Integer.parseInt(takeDeleteNumber);

        addedSet.getQuestion().remove(convertDeleteNumber);

        System.out.println("Question successfully deleted!");

    }

    // REQUIRES: user to specify an index in the range of Questions in the set
    //           and must have at least one Question in the set
    // MODIFIES: this
    // EFFECTS: changes the Question in a set at the user specified index and updates it to whatever the user
    //          wants to change it to
    public void changeQuestion() {
        doLookAtQuestions();

        System.out.println("Please select the index # of the question that you would like to change");
        Scanner inputChangeNumber = new Scanner(System.in);
        String takeChangeNumber = inputChangeNumber.next();

        int convertChangeNumber = Integer.parseInt(takeChangeNumber);

        System.out.println("Please input the new question that you would like");
        Scanner inputNewQuestion = new Scanner(System.in);
        inputNewQuestion.useDelimiter("\n");
        String takeNewQuestion = inputNewQuestion.next();

        System.out.println("Please input the new answer that you would like");
        Scanner inputNewAnswer = new Scanner(System.in);
        inputNewAnswer.useDelimiter("\n");
        String takeNewAnswer = inputNewAnswer.next();

        System.out.println("Please input the new # that you would like to associate with this question");
        Scanner inputNewNumber = new Scanner(System.in);
        String takeNewNumber = inputNewNumber.next();

        Question updatedChangedQuestion = new Question(takeNewQuestion, takeNewAnswer, takeNewNumber);
        addedSet.getQuestion().set(convertChangeNumber, updatedChangedQuestion);

    }

    // EFFECTS: saves the set to file
    public void saveSet() {
        try {
            jsonWriter.open();
            jsonWriter.write(addedSet);
            jsonWriter.close();
            System.out.println("Saved all questions to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads set from file
    public void loadSet() {
        try {
            addedSet = jsonReader.read();
            System.out.println("Loaded questions from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }
}
