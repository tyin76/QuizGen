package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// unit tests for Question

class QuestionTest {

    private Question testQuestion;
    private String testquestion = "What colour is an apple?";
    private String testAnswer = "red";
    private String testNumber = "1";

    private Question testBlankQuestion = new Question(" ", " ", " ");

    @BeforeEach void runBefore() {
        testQuestion = new Question(testquestion,testAnswer,testNumber);
    }

    @Test void testConstructor() {
        assertEquals(testquestion, testQuestion.getQuestion());
        assertEquals(testAnswer, testQuestion.getAnswer());
        assertEquals(testNumber, testQuestion.getQuestionNumber());

        assertEquals(" ", testBlankQuestion.getQuestion());
        assertEquals(" ", testBlankQuestion.getAnswer());
        assertEquals(" ", testBlankQuestion.getQuestionNumber());

    }


}