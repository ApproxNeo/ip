package task;

public class Task {
    protected String description;
    protected boolean done;

    public Task(boolean done, String description) {
        this.description = description;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
    
    public String saveString() {
        return "T," + (this.done ? 1 : 0) + "," + this.description;
    }
    @Override
    public String toString() {
        return (this.done ? "[X]" : "[ ]" ) + " " + this.description;
    }
}
