package hampster.task;

/**
 * Represents a general task with a description and completion status.
 *
 * <p>Concrete task types must provide their own storage format.</p>
 */
public abstract class Task {

    /** The task description. */
    protected String description;

    /** Whether the task has been completed. */
    protected boolean done;

    /**
     * Creates a task.
     *
     * @param done whether the task is initially completed
     * @param description the task description
     */
    protected Task(boolean done, String description) {
        this.description = description;
        this.done = done;
    }

    /**
     * Toggles the completion status of the task.
     *
     * @return the new completion status
     */
    public boolean toggleState() {
        done = !done;
        return done;
    }

    /**
     * Converts the task into its storage format.
     *
     * @return a serialized representation of the task
     */
    public abstract String saveString();

    /**
     * Returns a user-readable representation of the task.
     *
     * @return the task status and description
     */
    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + " " + description;
    }
}