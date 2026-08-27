package hampster.task;

import java.time.LocalDateTime;

import hampster.parser.DateTimeParser;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

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
        return String.format("E|%s|%s|%s|%s", this.done ? "1" : "0", this.description, DateTimeParser.deparse(this.from), DateTimeParser.deparse(this.to));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeParser.deparse(this.from)
                + " to: " + DateTimeParser.deparse(this.to) + ")";
    }
}