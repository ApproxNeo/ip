package hampster.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import hampster.exception.HampsterException;
import hampster.parser.DateTimeParser;
import hampster.task.Deadline;
import hampster.task.Event;
import hampster.task.Task;
import hampster.task.TaskList;
import hampster.task.ToDo;

/**
 * Handles saving tasks to and loading tasks from the persistent data file.
 */
public class Storage {

    /** The name of the file used to store tasks. */
    private static final String FILE_NAME = "data.txt";

    /**
     * Saves all tasks to the data file.
     *
     * <p>Each task is converted into its storage format using
     * {@link Task#saveString()}.</p>
     *
     * @param list the list of tasks to save
     */
    public static void save(List<Task> list) {
        try {
            List<String> lines = new ArrayList<>();

            for (Task task : list) {
                lines.add(task.saveString());
            }

            Files.write(Path.of(FILE_NAME), lines);

        } catch (IOException e) {
            System.out.println("Error saving file.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the data file.
     *
     * <p>Blank lines are ignored. Invalid task records are skipped and
     * reported to the console.</p>
     *
     * @return a task list containing all valid stored tasks
     * @throws IOException if the data file cannot be read
     */
    public static TaskList load() throws IOException {
        Path path = Path.of(FILE_NAME);

        if (!Files.exists(path)) {
            return new TaskList();
        }

        TaskList tasks = new TaskList();

        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                try {
                    if (line.isBlank()) {
                        continue;
                    }

                    String[] parts = line.split("\\|");

                    switch (parts[0]) {
                        case "T" -> tasks.add(parseToDo(parts));
                        case "D" -> tasks.add(parseDeadline(parts));
                        case "E" -> tasks.add(parseEvent(parts));
                        default -> throw new HampsterException(
                                "Unknown task type: " + parts[0]
                        );
                    }
                } catch (HampsterException e) {
                    System.out.println(
                            "Savefile load() error: " + e.getMessage()
                    );
                }
            }
        }

        return tasks;
    }

    /**
     * Parses a saved to-do task.
     *
     * <p>The expected format is
     * {@code T|status|description}.</p>
     *
     * @param parts the pipe-separated task fields
     * @return the parsed to-do task
     * @throws HampsterException if the task format is invalid
     */
    private static ToDo parseToDo(String[] parts)
            throws HampsterException {
        if (parts.length != 3) {
            throw new HampsterException("Invalid ToDo format");
        }

        return new ToDo(parts[1].equals("1"), parts[2]);
    }

    /**
     * Parses a saved deadline task.
     *
     * <p>The expected format is
     * {@code D|status|description|deadline}.</p>
     *
     * @param parts the pipe-separated task fields
     * @return the parsed deadline task
     * @throws HampsterException if the task format is invalid
     * @throws java.time.format.DateTimeParseException if the deadline
     *         has an invalid date format
     */
    private static Deadline parseDeadline(String[] parts)
            throws HampsterException {
        if (parts.length != 4) {
            throw new HampsterException("Invalid Deadline format");
        }

        return new Deadline(
                parts[1].equals("1"),
                parts[2],
                DateTimeParser.parseFromSave(parts[3])
        );
    }

    /**
     * Parses a saved event task.
     *
     * <p>The expected format is
     * {@code E|status|description|start|end}.</p>
     *
     * @param parts the pipe-separated task fields
     * @return the parsed event task
     * @throws HampsterException if the task format is invalid
     * @throws java.time.format.DateTimeParseException if either event time
     *         has an invalid date format
     */
    private static Event parseEvent(String[] parts)
            throws HampsterException {
        if (parts.length != 5) {
            throw new HampsterException("Invalid Event format");
        }

        return new Event(
                parts[1].equals("1"),
                parts[2],
                DateTimeParser.parseFromSave(parts[3]),
                DateTimeParser.parseFromSave(parts[4])
        );
    }
}