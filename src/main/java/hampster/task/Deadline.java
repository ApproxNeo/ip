package hampster.task;

import java.time.LocalDateTime;

import hampster.parser.DateTimeParser;

public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(boolean done, String description, LocalDateTime by) {
        super(done, description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by) {
        this(false, description, by);
    }

    @Override
    public String saveString() {
        return String.format(
                "D|%s|%s|%s",
                done ? "1" : "0",
                description,
                DateTimeParser.deparse(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + DateTimeParser.deparse(by) + ")";
    }
}