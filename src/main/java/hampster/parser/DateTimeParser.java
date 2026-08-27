package hampster.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Provides utility methods for parsing and formatting date-time values.
 */
public final class DateTimeParser {

    /**
     * Formatter for user-entered date-time values.
     *
     * <p>Expected format: {@code d/M/uuuu HHmm}</p>
     */
    private static final DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm");

    /**
     * Formatter for date-time values stored in the save file.
     *
     * <p>Expected format: {@code MMM dd uuuu, hh:mm a}</p>
     */
    private static final DateTimeFormatter outputFormat =
            DateTimeFormatter.ofPattern("MMM dd uuuu, hh:mm a");

    /**
     * Parses a user-entered date-time string.
     *
     * @param dateTime the date-time in {@code d/M/uuuu HHmm} format
     * @return the parsed date-time
     * @throws DateTimeParseException if the input has an invalid format
     */
    public static LocalDateTime parse(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, inputFormat);

        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(
                    "Datetimes needs to be in d/M/uuuu HHmm "
                            + "(e.g. 2/12/2019 1800)",
                    dateTime,
                    0
            );
        }
    }

    /**
     * Parses a date-time loaded from the save file.
     *
     * @param dateTime the date-time in {@code MMM dd uuuu, hh:mm a} format
     * @return the parsed date-time
     * @throws DateTimeParseException if the input has an invalid format
     */
    public static LocalDateTime parseFromSave(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, outputFormat);

        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(
                    "Datetimes needs to be in d/M/uuuu HHmm "
                            + "(e.g. 2/12/2019 1800)",
                    dateTime,
                    0
            );
        }
    }

    /**
     * Formats a date-time for storage or display.
     *
     * @param dateTime the date-time to format
     * @return the formatted date-time string
     */
    public static String deparse(LocalDateTime dateTime) {
        return dateTime.format(outputFormat);
    }
}