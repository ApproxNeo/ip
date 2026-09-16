package hampster.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TaskTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 9, 15, 10, 0);
    private static final LocalDateTime END = LocalDateTime.of(2026, 9, 15, 11, 0);

    @Test
    void todo_serializesAndDisplaysIncompleteTask() {
        ToDo task = new ToDo(false, "Write tests");

        assertEquals("T|0|Write tests|", task.saveString());
        assertEquals("[T][ ] Write tests", task.toString());
    }

    @Test
    void todo_serializesAndDisplaysCompletedTaggedTask() {
        ToDo task = new ToDo(true, "Review tests", "#review");

        assertEquals("T|1|Review tests|#review", task.saveString());
        assertEquals("[T][X] Review tests #review", task.toString());
    }

    @Test
    void deadline_serializesAndDisplaysDeadline() {
        Deadline task = new Deadline(false, "Submit report", START, "#school");

        assertEquals("D|0|Submit report|Sep 15 2026, 10:00 AM|#school", task.saveString());
        assertEquals("[D][ ] Submit report #school (by: Sep 15 2026, 10:00 AM)", task.toString());
    }

    @Test
    void event_serializesAndDisplaysEventInterval() {
        Event task = new Event(true, "Team meeting", START, END, "#team");

        assertEquals(
                "E|1|Team meeting|Sep 15 2026, 10:00 AM|Sep 15 2026, 11:00 AM|#team",
                task.saveString()
        );
        assertEquals(
                "[E][X] Team meeting #team (from: Sep 15 2026, 10:00 AM"
                        + " to: Sep 15 2026, 11:00 AM)",
                task.toString()
        );
    }

    @Test
    void toggleState_switchesBetweenIncompleteAndComplete() {
        ToDo task = new ToDo("Toggle me");

        assertTrue(task.toggleState());
        assertFalse(task.toggleState());
    }

    @Test
    void setTag_normalizesTagAndRemoveTagClearsIt() {
        ToDo task = new ToDo("Tag me");

        task.setTag("#WORK");
        assertEquals("#work", task.getTag());

        task.removeTag();
        assertEquals("", task.getTag());
    }

    @Test
    void nullTag_isStoredAsAnEmptyTag() {
        ToDo task = new ToDo(false, "No tag", null);

        assertEquals("", task.getTag());
        assertEquals("[T][ ] No tag", task.toString());
    }

    @Test
    void blankOrNullDescription_isRejected() {
        assertThrows(IllegalArgumentException.class, () -> new ToDo(false, "   "));
        assertThrows(IllegalArgumentException.class, () -> new ToDo(false, null));
    }

    @Test
    void nullDeadlineTime_isRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Deadline(false, "Missing time", null));
    }

    @Test
    void nullOrInvalidEventTimes_areRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                new Event(false, "Missing start", null, END));
        assertThrows(IllegalArgumentException.class, () ->
                new Event(false, "Missing end", START, null));
        assertThrows(IllegalArgumentException.class, () ->
                new Event(false, "Backwards", END, START));
        assertThrows(IllegalArgumentException.class, () ->
                new Event(false, "Zero length", START, START));
    }
}
