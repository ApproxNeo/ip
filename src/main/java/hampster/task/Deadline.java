package hampster.task;

import java.time.LocalDateTime;

import hampster.parser.DateTimeParser;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(boolean done, String description, LocalDateTime by) {
        super(done, description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by) {
        this(false, description, by);
    }

    // public Deadline(List<String> parts) {
    //     this(parts.get(1).equals("1") ? true : false, parts.get(2), parts.get(3));
    // }

    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s", this.done ? "1" : "0", this.description, DateTimeParser.deparse(this.by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.deparse(this.by) + ")";
    }
}
