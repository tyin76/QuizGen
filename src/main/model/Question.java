package model;

import org.json.JSONObject;
import persistence.Writable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

// Represents a Question having a question, answer, and question number (integer)
public class Question implements Writable {

    private String question;
    private String answer;
    private String questionNumber;

    // REQUIRES: the question, answer, and question number to be non-zero in length
    // EFFECTS: constructs a Question to the user given inputs for the question, answer, and questionNumber
    public Question(String question, String answer, String questionNumber) {
        this.question = question;
        this.answer = answer;
        this.questionNumber = questionNumber;
    }

    // all getter methods below
    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getQuestionNumber() {
        return this.questionNumber;
    }

    // EFFECTS: puts Question components into JSON object
    @Override
    public JSONObject toJSon() {
        JSONObject json = new JSONObject();
        json.put("question", this.question);
        json.put("answer", this.answer);
        json.put("questionNumber", this.questionNumber);
        return json;
    }
}
