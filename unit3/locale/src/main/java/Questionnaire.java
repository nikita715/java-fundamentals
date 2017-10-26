import org.apache.log4j.BasicConfigurator;

import java.io.PrintStream;
import java.util.*;

public class Questionnaire {
    private static Scanner scanner = new Scanner(System.in);
    private static PrintStream printStream = new PrintStream(System.out);
    private ResourceService resourceService;
    private static final String BASE_NAME = "questions";

    private void setLocale() {

        printStream.print("Input language: ");
        String localeName = scanner.nextLine();
        Locale locale = Locale.forLanguageTag(localeName);

        resourceService = new ResourceService(BASE_NAME, locale);

    }

    private void run() {
        while (true) {

            printStream.print("Input index of question: ");
            int index = scanner.nextInt();

            if (index == 0) {
                break;
            }

            try {

                printStream.println(resourceService.getQuestion(index));

                printStream.println("Your answer: ");
                scanner.nextLine();
                String userAnswer = scanner.nextLine();

                if (userAnswer.equals(resourceService.getAnswer(index))) {
                    printStream.println("Correct!\n");
                } else {
                    printStream.println(String.format("Wrong! Correct answer: %s%n", resourceService.getAnswer(index)));
                }

            } catch (IndexOutOfBoundsException e) {
                printStream.println("Illegal index if question!");
            }

        }
    }

    public static void main(String[] args) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setLocale();
        questionnaire.run();
    }
}
