package hampster.task;

import java.util.List;

/**
 * Represents a simple task without a deadline or event time.
 */
public class ToDo extends Task {

    /**
     * Creates a to-do task with the specified completion status.
     *
     * @param done whether the task is completed
     * @param description the task description
     */
    public ToDo(boolean done, String description) {
        super(done, description);
    }

    /**
     * Creates an incomplete to-do task.
     *
     * @param description the task description
     */
    public ToDo(String description) {
        this(false, description);
    }

    /**
     * Converts this task into its pipe-separated storage format.
     *
     * @return the serialized to-do task
     */
    @Override
    public String saveString() {
        return String.format(
                "T|%s|%s",
                this.done ? "1" : "0",
                this.description
        );
    }

    /**
     * Returns a user-readable representation of this to-do task.
     *
     * @return the task type, completion status, and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}