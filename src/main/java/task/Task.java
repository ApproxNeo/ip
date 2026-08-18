package task;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
    
    @Override
    public String toString() {
        return (this.done ? "[X]" : "[ ]" ) + " " + this.description;
    }
}
