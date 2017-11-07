package javase07.sync1;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TransferFormer {
    static final Pattern pattern = Pattern.compile("from:(\\d+) to:(\\d+) (\\d+)");
    final String input;

    TransferFormer(String input) {
        this.input = input;
    }

    Transfer getTransfer(Matcher matcher) {
        return new Transfer(
                new Account(Integer.valueOf(matcher.group(1))),
                new Account(Integer.valueOf(matcher.group(2))),
                Integer.valueOf(matcher.group(3)));
    }
}
