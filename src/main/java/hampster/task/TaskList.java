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
}