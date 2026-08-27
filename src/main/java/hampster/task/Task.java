package hampster.task;

public class Task {

    protected String description;
    protected boolean done;

    public Task(boolean done, String description) {
        this.description = description;
        this.done = done;
    }

    public boolean toggleState() {
        done = !done;
        return done;
    }

    public String saveString() {
        return "T," + (done ? 1 : 0) + "," + description;
    }

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + " " + description;
    }
}