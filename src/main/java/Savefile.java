import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;;

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
            if (line.isBlank()) {
                continue;
            }

            String[] parts = line.split(",");

            switch (parts[0]) {
                case "T":
                    tasks.add(new ToDo(Arrays.asList(parts)));
                    break;

                case "D":
                    tasks.add(new Deadline(Arrays.asList(parts)));
                    break;

                case "E":
                    tasks.add(new Event(Arrays.asList(parts)));
                    break;

                default:
                    throw new IllegalArgumentException(
                        "Unknown task type: " + parts[0]
                    );
            }
        }
    }

    return tasks;
}
}