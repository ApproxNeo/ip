package hampster.task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

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