package crazylogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class CrazyLoggerTest {
    @Test
    void addMessage1() {
    }

    @Test
    void getFullLog1() {
    }

    @Test
    void findFirstMessage1() {
    }

    @Test
    void findLastMessage1() {
    }

    @Test
    void findAllMessages1() {
    }

    @Test
    void addMessage() {
    }

    @Test
    void getFullLog() {
    }

    @Test
    void findFirstMessage() {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.addMessage("Woo-1");
        crazyLogger.addMessage("Boo");
        crazyLogger.addMessage("Moo");
        crazyLogger.addMessage("Woo-2");
        crazyLogger.addMessage("Loo");
        System.out.println(crazyLogger.findFirstMessage("Woo"));
    }

    @Test
    void findLastMessage() {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.addMessage("Woo-1");
        crazyLogger.addMessage("Boo");
        crazyLogger.addMessage("Moo");
        crazyLogger.addMessage("Woo-2");
        crazyLogger.addMessage("Loo");
        System.out.println(crazyLogger.findLastMessage("Woo"));
    }

    @Test
    void findAllMessages() {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.addMessage("Woo-1");
        crazyLogger.addMessage("Boo");
        crazyLogger.addMessage("Moo");
        crazyLogger.addMessage("Woo-2");
        crazyLogger.addMessage("Loo");
        for (String s : crazyLogger.findAllMessages("06-10-2017")) {
            System.out.println(s);
        }
    }

    @Test
    void getMessageByDateTimeTest() throws Exception {
        CrazyLogger crazyLogger = new CrazyLogger();

    }

    @Test
    void getFullLogTest() {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.addMessage("woo");
        crazyLogger.addMessage("gee");
        System.out.println(crazyLogger.getFullLog());
    }
}
