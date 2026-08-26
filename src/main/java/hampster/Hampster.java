package hampster;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import hampster.task.Task;
import hampster.task.ToDo;
import hampster.ui.Ui;
import hampster.task.Deadline;
import hampster.task.Event;
import hampster.storage.*;

public class Hampster {

    private static Ui ui;
    public static void main(String[] args) {
        ui = new Ui();
        ui.showWelcome();
        
        List<Task> tasks;
        
        try {
            tasks = Storage.load();
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();

            ui.printLine();

            try {
                if (userInput.isBlank()) {
                    throw new HampsterException("Broh... you didn't say anything.");
                }

                String[] parts = userInput.trim().split("\\s+");
                Command command = parseCommand(parts[0]);

                switch (command) {
                    case BYE -> {
                        ui.showGoodbye();
                        return;
                    }

                    case LIST -> handleList(tasks);
                    case MARK -> handleMark(parts, tasks, true);
                    case UNMARK -> handleMark(parts, tasks, false);
                    case DELETE -> handleDelete(parts, tasks);
                    case TODO -> handleTodo(parts, tasks);
                    case DEADLINE -> handleDeadline(parts, tasks);
                    case EVENT -> handleEvent(parts, tasks);
                }

                Storage.save(tasks);

            } catch (HampsterException e) {
                ui.showMessage("\tBroh... " + e.getMessage());
            }
            ui.printLine();
        }
    }

    private static Command parseCommand(String word) throws HampsterException {
        try {
            return Command.valueOf(word.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new HampsterException(
                    "Broh... I got no clue what that means.");
        }
    }

    private static void handleList(List<Task> tasks) {
        ui.showMessage("\tListing your tasks broh");
        for (int i = 0; i < tasks.size(); ++i) {
            ui.showMessage("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }

    private static void handleMark(String[] parts, List<Task> tasks, boolean done)
            throws HampsterException {
        if (parts.length != 2) {
            throw new HampsterException(
                    "Broh... gimme exactly one task number. Try: "
                            + (done ? "mark" : "unmark") + " <task number>");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new HampsterException(
                    "Broh... '" + parts[1] + "' ain't a task number.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new HampsterException(
                    "Broh... task " + taskNumber + " doesn't exist.");
        }

        Task task = tasks.get(taskNumber - 1);

        if (done) {
            task.mark();
            ui.showMessage("\tBoom. Task " + taskNumber + " is donezo.");
        } else {
            task.unmark();
            ui.showMessage("\tAight. Task " + taskNumber + " is back in action.");
        }

        ui.showMessage("\t" + task);
    }

    private static void handleDelete(String[] parts, List<Task> tasks)
            throws HampsterException {
        if (parts.length != 2) {
            throw new HampsterException(
                    "Delete needs exactly one task number. Try: delete <task number>");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new HampsterException(parts[1] + "' ain't a task number.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new HampsterException("task " + taskNumber + " doesn't exist.");
        }

        Task removedTask = tasks.remove(taskNumber - 1);

        ui.showMessage("\tNoted broh. I've removed this task:");
        ui.showMessage("\t  " + removedTask);
        ui.showMessage("\tNow you've got " + tasks.size() + " tasks in the list.");
    }

    private static void handleTodo(String[] parts, List<Task> tasks)
            throws HampsterException {
        String description = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (description.isEmpty()) {
            throw new HampsterException(
                    "You gotta tell me what the todo actually is.");
        }

        tasks.add(new ToDo(false, description));

        ui.showMessage("\tAight, added that todo broh.");
        ui.showMessage("\t" + tasks.get(tasks.size() - 1));
        ui.showMessage("\tYou've got " + tasks.size() + " tasks now.");
    }

    private static void handleDeadline(String[] parts, List<Task> tasks)
            throws HampsterException {
        String input = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (input.isEmpty()) {
            throw new HampsterException(
                    "Deadlines need a description.");
        }

        String[] deadlineParts = input.split("\\s+/by\\s+", 2);

        if (deadlineParts.length != 2) {
            throw new HampsterException(
                    "Deadlines need a /by. "
                            + "Try: deadline <description> /by <date or time>");
        }

        String description = deadlineParts[0].trim();
        LocalDateTime by;

        try {
            by = DateTimeParser.parse(deadlineParts[1]);
        } catch (DateTimeParseException e) {
            throw new HampsterException(e.getMessage());
        }

        tasks.add(new Deadline(false, description, by));

        ui.showMessage("\tDeadline locked in.");
        ui.showMessage("\t" + tasks.get(tasks.size() - 1));
        ui.showMessage("\tYou've got " + tasks.size() + " tasks now.");
    }

    private static void handleEvent(String[] parts, List<Task> tasks)
            throws HampsterException {
        String input = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (input.isEmpty()) {
            throw new HampsterException(
                    "Events need a description.");
        }

        String[] eventParts = input.split("\\s+/from\\s+", 2);

        if (eventParts.length != 2) {
            throw new HampsterException(
                    "Events need a /from time. "
                            + "Try: event <description> /from <start> /to <end>");
        }

        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("\\s+/to\\s+", 2);

        if (timeParts.length != 2) {
            throw new HampsterException(
                    "Events need a /to time."
                            + "Try: event <description> /from <start> /to <end>");
        }

        LocalDateTime from;
        LocalDateTime to;
        try {
            from = DateTimeParser.parse(timeParts[0]);
            to = DateTimeParser.parse(timeParts[1]);

        } catch (DateTimeParseException e) {
            throw new HampsterException(e.getMessage());
        }

        if (description.isEmpty()) {
            throw new HampsterException(
                    "Tell me what the event actually is.");
        }

        tasks.add(new Event(false, description, from, to));

        ui.showMessage("\tEvent secured broh.");
        ui.showMessage("\t" + tasks.get(tasks.size() - 1));
        ui.showMessage("\tYou've got " + tasks.size() + " tasks now.");
    }
}
