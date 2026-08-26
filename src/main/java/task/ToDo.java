package task;

import java.util.List;

public class ToDo extends Task {

    public ToDo(boolean done, String description) {
        super(done, description);
    }

    public ToDo(List<String> parts) {
        this(parts.get(1).equals("1") ? true : false, parts.get(2));
    }

    @Override
    public String saveString() {
        return String.format("T,%s,%s", this.done ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
