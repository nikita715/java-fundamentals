package javase03.crazylogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CrazyLogger {
    private StringBuilder log = new StringBuilder();
    private static final String DATE_TIME_PATTERN = "dd-MM-YYYY : hh-mm";
    private static final String MESSAGES_SEPARATOR = ";";
    private static final String DATE_MESSAGE_SEPARATOR = " - ";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);


    public void addMessage(String message) {
        log.append(LocalDateTime.now().format(dateTimeFormatter))
                .append(DATE_MESSAGE_SEPARATOR)
                .append(message)
                .append(MESSAGES_SEPARATOR);
    }

    public String getFullLog() {
        return log.toString();
    }

    public String findFirstMessage(String message) {
        return getRecord(log.indexOf(message));
    }

    public String findLastMessage(String message) {
        return getRecord(log.lastIndexOf(message));
    }

    public String[] findAllMessages(String message) {
        ArrayList<String> messages = new ArrayList<>();
        StringBuilder logCopy = log;
        while (logCopy.indexOf(message) != -1) {
            messages.add(getRecord(log.indexOf(message)));
            logCopy.delete(0, logCopy.indexOf(MESSAGES_SEPARATOR, logCopy.indexOf(message)));
        }
        return messages.toArray(new String[0]);
    }

    private String getRecord(int messageStartPoint) {
        int recordStartPoint = log.substring(0, messageStartPoint).lastIndexOf(MESSAGES_SEPARATOR);
        return log.substring(recordStartPoint + 1, log.indexOf(MESSAGES_SEPARATOR, messageStartPoint));
    }
}
