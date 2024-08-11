package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventLogTest {

    private Collection<Event> eventLog;
    private Event event;
    private Set set;
    private Question question;

    @BeforeEach void runBefore() {
        eventLog = new ArrayList<>();
        set = new Set();
        question = new Question("what is 2+2?", "4", "1");
        event = new Event("TEST EVENT");

    }

    @Test void testClearLog() {
    }

    @Test void testIterator() {

    }
}
