package hampster.task;

import java.util.ArrayList;

/**
 * Represents a collection of {@link Task} objects.
 *
 * <p>This class extends {@link ArrayList} and provides functionality
 * for displaying all stored tasks.</p>
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Prints each task in this list to the standard output.
     */
    public void printTasks() {
        for (Task task : this) {
            System.out.println(task);
        }
    }

    public TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (Task task : this) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}