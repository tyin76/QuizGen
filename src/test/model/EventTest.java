package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private Event event;
    private Event differentEvent;
    private Set testSet;

    @BeforeEach void runBefore() {
        event = new Event("TEST EVENT");
        differentEvent = new Event("DIFFERENT EVENT");
        testSet = new Set();
    }

    @Test void testConstructor() {
        assertEquals("TEST EVENT", event.getDescription());
    }

    @Test void testToString() {
        assertEquals(event.getDate().toString() + "\n" + "TEST EVENT", event.toString());
    }

    @Test void testEqualsOtherIsNull() {
        assertFalse(event.equals(null));
    }

    @Test void testOtherIsDifferentClass() {
        assertFalse(event.equals(testSet));
    }

    @Test void sameEquals() {
        assertTrue(event.equals(event));
    }

    @Test void testEqualsDifferentDescription() {
        assertFalse(event.equals(differentEvent));
    }

    @Test void testEqualsDifferentDates() {
        event.getDate().setTime(10000000);
        differentEvent.getDate().setTime(10);
        assertFalse(event.equals(differentEvent));
    }

    @Test void testDifferentBoth() {
        event.getDate().setTime(10000000);
        differentEvent.getDate().setTime(10);
        assertTrue(event.getDescription() != differentEvent.getDescription());
        assertTrue(event.getDate() != differentEvent.getDate());
        assertFalse(event.equals(differentEvent));
        assertFalse(differentEvent.equals(event));

    }

    @Test void testHashcode() {
        assertTrue(event.equals(event));
        assertTrue(event.hashCode() == event.hashCode());
    }


}