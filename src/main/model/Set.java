package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Represents a Set that is composed of a list of Questions
public class Set implements Writable {

    private List<Question> questionsInSet;
    private EventLog eventLog;

    // EFFECTS: constructs a Set with no Questions
    public Set() {
        questionsInSet = new ArrayList<>();
        this.eventLog = EventLog.getInstance();
    }

    // getter method
    public List<Question> getQuestion() {
        return questionsInSet;
    }

    // MODIFIES: this, eventLog
    // EFFECTS: adds a Question to the Set and adds the add event to eventLog
    public List<Question> addQuestionToSet(Question question) {
        if (!question.getAnswer().equals(" ") && !question.getQuestion().equals(" ")
                && !question.getQuestionNumber().equals(" ")) {
            questionsInSet.add(question);
            Event addedQuestionToSet = new Event("A question was added to the Set!");
            eventLog.logEvent(addedQuestionToSet);
            return questionsInSet;
        }
        return null;
    }

    // MODIFIES: this, eventLog
    // EFFECTS: removes Question at specified index and logs the remove event to eventLog
    public void removeQuestion(int index) {
        questionsInSet.remove(index);
        Event deleteEvent = new Event("A question was deleted!");
        eventLog.logEvent(deleteEvent);
    }

    // MODIFIES: this, eventLog
    // EFFECTS: changes question to given question at index
    public void changeQuestion(int index, Question question) {
        questionsInSet.set(index, question);
        Event changeEvent = new Event("A question was changed!");
        eventLog.logEvent(changeEvent);
    }

    // MODIFIES: eventLog
    // EFFECTS: creates new JSON object and adds set components to JSON
    @Override
    public JSONObject toJSon() {
        JSONObject json = new JSONObject();
        json.put("questionsInSet", questionsToJSon());
        return json;
    }

    // EFFECTS: returns Questions in this set as a JSON array
    private JSONArray questionsToJSon() {
        JSONArray jsonArray = new JSONArray();

        for (Question question : questionsInSet) {
            jsonArray.put(question.toJSon());
        }
        return jsonArray;
    }
}