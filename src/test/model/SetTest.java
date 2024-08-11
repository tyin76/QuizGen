package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// unit tests for Set

public class SetTest {

    private Set testSet;
    private Question addedQuestion = new Question
            ("What colour is a banana?", "yellow", "2");

    private Question secondAddedQuestion= new Question("What is 1+1?", "2", "3");

    private Question oneOfMultipleQuestions = new Question
            ("What is 2+2?", "4", "4");

    private Question secondOfMultipleQuestions = new Question
            ("What is the capital of Canada?", "Ottawa", "5");

    private Question thirdOfMultipleQuestions = new Question
            ("What is the capital of China?", "Beijing", "6");

    private Question testAllBlankQuestion = new Question
            (" ", " ", " ");

    private Question testOnlyQuestionBlank =  new Question
            (" ", "apple", "7");

    private Question testOnlyAnswerBlank = new Question
            ("What is the national animal of Canada?", " ", "8");

    private Question testOnlyNumberBlank = new Question
            ("What is the capital of Italy?", "Rome", " ");

    @BeforeEach void runBefore() {
        testSet = new Set();
    }

    @Test void testConstructor() {
        assertEquals(0, testSet.getQuestion().size());
    }

    @Test void testAddQuestion() {
       testSet.addQuestionToSet(addedQuestion);
       assertTrue(testSet.getQuestion().contains(addedQuestion));

       assertEquals(1, testSet.getQuestion().size());
       assertEquals(addedQuestion, testSet.getQuestion().get(0));

       testSet.addQuestionToSet(secondAddedQuestion);
       assertTrue(testSet.getQuestion().contains(secondAddedQuestion));
       assertEquals(2, testSet.getQuestion().size());
       assertEquals(secondAddedQuestion, testSet.getQuestion().get(1));
    }

    @Test void addTwoQuestions() {
        testSet.addQuestionToSet(oneOfMultipleQuestions);
        testSet.addQuestionToSet(secondOfMultipleQuestions);

        assertEquals(oneOfMultipleQuestions, testSet.getQuestion().get(0));
        assertEquals(secondOfMultipleQuestions, testSet.getQuestion().get(1));

        assertTrue(testSet.getQuestion().contains(oneOfMultipleQuestions));
        assertTrue(testSet.getQuestion().contains(secondOfMultipleQuestions));

        assertEquals(2, testSet.getQuestion().size());
    }

    @Test void addThreeQuestions() {
        testSet.addQuestionToSet(oneOfMultipleQuestions);
        testSet.addQuestionToSet(secondOfMultipleQuestions);
        testSet.addQuestionToSet(thirdOfMultipleQuestions);

        assertEquals(oneOfMultipleQuestions, testSet.getQuestion().get(0));
        assertEquals(secondOfMultipleQuestions, testSet.getQuestion().get(1));
        assertEquals(thirdOfMultipleQuestions,testSet.getQuestion().get(2));

        assertTrue(testSet.getQuestion().contains(oneOfMultipleQuestions));
        assertTrue(testSet.getQuestion().contains(secondOfMultipleQuestions));
        assertTrue(testSet.getQuestion().contains(thirdOfMultipleQuestions));

        assertEquals(3, testSet.getQuestion().size());
    }

    @Test void blankQuestion() {
        testSet.addQuestionToSet(testAllBlankQuestion);
        assertEquals(0, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().contains(testAllBlankQuestion));

        testSet.addQuestionToSet(testOnlyQuestionBlank);
        assertEquals(0, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().contains(testOnlyQuestionBlank));

        testSet.addQuestionToSet(testOnlyAnswerBlank);
        assertEquals(0, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().contains(testOnlyAnswerBlank));

        testSet.addQuestionToSet(testOnlyNumberBlank);
        assertEquals(0, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().contains(testOnlyNumberBlank));
    }

    @Test void onlyQuestionBlank() {
        testSet.addQuestionToSet(testOnlyQuestionBlank);
        assertEquals(0, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().contains(testOnlyQuestionBlank));
    }

    @Test void onlyAnswerBlank() {
        testSet.addQuestionToSet(testOnlyAnswerBlank);
        assertEquals(0, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().contains(testOnlyAnswerBlank));
    }

    @Test void onlyNumberBlank() {
        testSet.addQuestionToSet(testOnlyNumberBlank);
        assertEquals(0, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().contains(testOnlyNumberBlank));
    }

    @Test void testRemoveQuestion() {
        testSet.addQuestionToSet(addedQuestion);
        testSet.removeQuestion(0);
        assertEquals(0, testSet.getQuestion().size());
        assertTrue(testSet.getQuestion().isEmpty());

        testSet.addQuestionToSet(addedQuestion);
        testSet.addQuestionToSet(secondAddedQuestion);
        testSet.removeQuestion(1);
        assertEquals(1, testSet.getQuestion().size());
        assertFalse(testSet.getQuestion().isEmpty());
    }

    @Test void testChangeQuestion() {
        Question changeQuestion = new Question("what is 5+5", "10", "5");
        testSet.addQuestionToSet(addedQuestion);
        testSet.changeQuestion(0, changeQuestion);
        assertEquals(changeQuestion, testSet.getQuestion().get(0));
    }
}

