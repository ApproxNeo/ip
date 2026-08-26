package hampster;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeParser {
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd uuuu, hh:mm a");

    public static LocalDateTime parse(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, inputFormat);
            
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Datetimes needs to be in d/M/uuuu HHmm (e.g. 2/12/2019 1800)", dateTime, 0);

        }
    }

    public static LocalDateTime parseFromSave(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, outputFormat);
            
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Datetimes needs to be in d/M/uuuu HHmm (e.g. 2/12/2019 1800)", dateTime, 0);

        }
    }

    public static String deparse(LocalDateTime dateTime) {
        return dateTime.format(outputFormat);
    }
}