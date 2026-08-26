package task;

import java.util.List;

public class Event extends Task {

    private String from;
    private String to;

    public Event(boolean done, String description, String from, String to) {
        super(done, description);
        this.from = from;
        this.to = to;
    }

    public Event(List<String> parts) {
        this(parts.get(1).equals("1") ? true : false, parts.get(2), parts.get(3), parts.get(4));
    }

    @Override
    public String saveString() {
        return String.format("E,%s,%s,%s,%s", this.done ? "1" : "0", this.description, this.from, this.to);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from
                + " to: " + to + ")";
    }
}