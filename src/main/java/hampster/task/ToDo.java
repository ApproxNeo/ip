package hampster.task;

import java.util.List;

public class ToDo extends Task {

    public ToDo(boolean done, String description) {
        super(done, description);
    }

    public ToDo(String description) {
        this(false, description);
    }

    public ToDo(List<String> parts) {
        this("1".equals(parts.get(1)), parts.get(2));
    }

    @Override
    public String saveString() {
        return String.format(
                "T|%s|%s",
                done ? "1" : "0",
                description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}