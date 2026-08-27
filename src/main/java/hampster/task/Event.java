package hampster.task;

import java.time.LocalDateTime;

import hampster.parser.DateTimeParser;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(boolean done, String description, LocalDateTime from, LocalDateTime to) {
        super(done, description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(false, description, from, to);
    }

    @Override
    public String saveString() {
        return String.format(
                "E|%s|%s|%s|%s",
                done ? "1" : "0",
                description,
                DateTimeParser.deparse(from),
                DateTimeParser.deparse(to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeParser.deparse(from)
                + " to: " + DateTimeParser.deparse(to) + ")";
    }
}