package hampster;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import hampster.task.Task;
import hampster.task.ToDo;
import hampster.task.Deadline;
import hampster.task.Event;

public class Savefile {

    private static final String FILE_NAME = "data.txt";

    public static void save(List<Task> list) {
        try {
            List<String> lines = new ArrayList<>();
            for (Task t : list) {
                lines.add(t.saveString());
            }

            Files.write(Path.of(FILE_NAME), lines);

        } catch (IOException e) {
            System.out.println("Error saving file.");
            e.printStackTrace();
        }
    }

    public static List<Task> load() throws IOException {
        Path path = Path.of(FILE_NAME);

        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        List<Task> tasks = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                try {
                    if (line.isBlank()) {
                        continue;
                    }

                    String[] parts = line.split("\\|");

                    switch (parts[0]) {
                        case "T":
                            tasks.add(parseToDo(parts));
                            break;

                        case "D":
                            tasks.add(parseDeadline(parts));
                            break;

                        case "E":
                            tasks.add(parseEvent(parts));
                            break;

                        default:
                            throw new HampsterException("Unknown task type: " + parts[0]);
                    }
                } catch (HampsterException e) {
                    System.out.println("Savefile load() error: " + e.getMessage());
                }

            }
        }

        return tasks;
    }

    private static ToDo parseToDo(String[] parts) throws HampsterException {
        if (parts.length != 3) {
            throw new HampsterException("Invalid ToDo format");
        }

        return new ToDo(parts[1].equals("1"), parts[2]);
    }

    private static Deadline parseDeadline(String[] parts) throws HampsterException {
        if (parts.length != 4) {
            throw new HampsterException("Invalid Deadline format");
        }

        return new Deadline(parts[1].equals("1"), parts[2], DateTimeParser.parseFromSave(parts[3]));
    }

    private static Event parseEvent(String[] parts) throws HampsterException {
        if (parts.length != 5) {
            throw new HampsterException("Invalid Event format");
        }

        return new Event(parts[1].equals("1"), parts[2], DateTimeParser.parseFromSave(parts[3]),
                DateTimeParser.parseFromSave(parts[4]));
    }
}