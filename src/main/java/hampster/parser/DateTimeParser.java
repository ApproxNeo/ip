package hampster.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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
            DateTimeFormatter.ofPattern("MMM dd uuuu, hh:mm a", Locale.ENGLISH);

    /** Error message used when a date-time does not match its expected format. */
    private static final String INVALID_DATETIME_MESSAGE =
            "Datetimes needs to be in d/M/uuuu HHmm (e.g. 2/12/2019 1800)";

    /**
     * Parses a user-entered date-time string.
     *
     * @param dateTime the date-time in {@code d/M/uuuu HHmm} format
     * @return the parsed date-time
     * @throws DateTimeParseException if the input has an invalid format
     */
    public static LocalDateTime parse(String dateTime) {
        return parseDateTime(dateTime, inputFormat);
    }

    /**
     * Parses a date-time loaded from the save file.
     *
     * @param dateTime the date-time in {@code MMM dd uuuu, hh:mm a} format
     * @return the parsed date-time
     * @throws DateTimeParseException if the input has an invalid format
     */
    public static LocalDateTime parseFromSave(String dateTime) {
        return parseDateTime(dateTime, outputFormat);
    }

    /** Parses a date-time with the supplied format and standardizes parse errors. */
    private static LocalDateTime parseDateTime(
            String dateTime, DateTimeFormatter format) {
        try {
            return LocalDateTime.parse(dateTime, format);

        } catch (DateTimeParseException exception) {
            throw new DateTimeParseException(
                    INVALID_DATETIME_MESSAGE,
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
