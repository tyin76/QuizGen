
package model;

import org.junit.jupiter.api.Test;
import persistence.JSonReader;
import persistence.JSonWriter;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JSonWriterTest {

    @Test void testWriterInvalidFileName() {
        try {
            Set set = new Set();
            JSonWriter writer = new JSonWriter("./data/\1/illegalFileName.json");
            writer.open();
            fail("IOException should have been thrown");
        } catch(IOException e){
            // pass
        }
    }

    @Test void testSetNotEmpty() {
        try {
            Set set = new Set();
            Question testquestion1 = new Question("hello morning", "bye night", "2");
            Question testquestion2 = new Question("What is 1+1?", "2", "3");
            set.addQuestionToSet(testquestion1);
            set.addQuestionToSet(testquestion2);
            JSonWriter writer = new JSonWriter("./data/JSonWriterTest.json");
            writer.open();
            writer.write(set);
            writer.close();

            JSonReader reader = new JSonReader("./data/JSonWriterTest.json");
            set = reader.read();
            assertEquals(2, set.getQuestion().size());
            assertEquals("hello morning", set.getQuestion().get(0).getQuestion());
            assertEquals("bye night", set.getQuestion().get(0).getAnswer());
            assertEquals("2", set.getQuestion().get(0).getQuestionNumber());

            assertEquals("What is 1+1?", set.getQuestion().get(1).getQuestion());
            assertEquals("2", set.getQuestion().get(1).getAnswer());
            assertEquals("3", set.getQuestion().get(1).getQuestionNumber());

        } catch (IOException e) {
            fail("Should not have thrown exception");
        }
    }
}

