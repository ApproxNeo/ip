package task;

import java.util.List;

public class Deadline extends Task {

    private String by;

    public Deadline(boolean done, String description, String by) {
        super(done, description);
        this.by = by;
    }

    public Deadline(List<String> parts) {
        this(parts.get(1).equals("1") ? true : false, parts.get(2), parts.get(3));
    }

    @Override
    public String saveString() {
        return String.format("D,%s,%s,%s", this.done ? "1" : "0", this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
