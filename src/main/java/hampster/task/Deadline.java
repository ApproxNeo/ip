package hampster.task;

import java.time.LocalDateTime;

import hampster.parser.DateTimeParser;

/**
 * Represents a task that must be completed by a specified date and time.
 */
public class Deadline extends Task {

    /** The date and time by which the task should be completed. */
    private LocalDateTime by;

    /**
     * Creates a deadline task with the specified completion status.
     *
     * @param done whether the task is completed
     * @param description the task description
     * @param by the deadline date and time
     */
    public Deadline(boolean done, String description, LocalDateTime by) {
        super(done, description);
        this.by = by;
    }

    /**
     * Creates an incomplete deadline task.
     *
     * @param description the task description
     * @param by the deadline date and time
     */
    public Deadline(String description, LocalDateTime by) {
        this(false, description, by);
    }

    /**
     * Converts this deadline into its pipe-separated storage format.
     *
     * @return the serialized deadline task
     */
    @Override
    public String saveString() {
        return String.format(
                "D|%s|%s|%s",
                this.done ? "1" : "0",
                this.description,
                DateTimeParser.deparse(this.by)
        );
    }

    /**
     * Returns a user-readable representation of this deadline.
     *
     * @return the task type, completion status, description, and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + DateTimeParser.deparse(this.by) + ")";
    }
}