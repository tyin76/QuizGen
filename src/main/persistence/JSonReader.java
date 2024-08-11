package persistence;

import model.Event;
import model.EventLog;
import model.Question;
import model.Set;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads set from JSON data stored in file
public class JSonReader {

    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JSonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads set from file and returns it
    //          throws IOException if an error occurs reading data from file
    public Set read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSet(jsonObject);
    }

    // EFFECTS: reads the source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }

    // EFFECTS: parses set from JSON object and returns it
    private Set parseSet(JSONObject json) {
        Set set = new Set();
        addQuestionS(set, json);
        return set;

    }

    // MODIFIES: set
    // EFFECTS: parses Questions from JSON Object and adds them to set
    private void addQuestionS(Set set, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questionsInSet");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(set, nextQuestion);
        }

    }

    // MODIFIES: set
    // EFFECTS: parses Question from JSON Object and adds them to set
    private void addQuestion(Set set, JSONObject json) {
        String question = json.getString("question");
        String answer = json.getString("answer");
        String questionNumber = json.getString("questionNumber");

        Question addQuestion = new Question(question, answer, questionNumber);
        set.addQuestionToSet(addQuestion);
    }
}