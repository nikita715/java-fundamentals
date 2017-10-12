package locale;

import org.apache.log4j.BasicConfigurator;

import java.io.PrintStream;
import java.util.*;

public class Questionnaire {
    private static Scanner scanner = new Scanner(System.in);
    private ResourceBundle resourceBundle;
    private Locale userLocale;
    private static PrintStream printStream = new PrintStream(System.out);
    private ArrayList<QuestionAndAnswer> questionsAndAnswers = new ArrayList<>();

    public Questionnaire() {
        BasicConfigurator.configure();
    }

    public String getQuestion(String key) {
        return resourceBundle.getString(key);
    }

    private void inputLocale() {
        Locale locale;
        while (userLocale == null) {
            printStream.print("Input language: ");
            String[] localeOptions = scanner.nextLine().split(" ");
            locale = new Locale(localeOptions[0]);
            try {
                if (localeOptions.length == 3) {
                    locale = new Locale(localeOptions[0], localeOptions[1], localeOptions[2]);
                    locale.getISO3Country();
                } else if (localeOptions.length == 2) {
                    locale = new Locale(localeOptions[0], localeOptions[1]);
                    locale.getISO3Country();
                } else if (localeOptions.length == 1) {
                    locale = new Locale(localeOptions[0]);
                } else {
                    throw new InputMismatchException();
                }
                locale.getISO3Language();
            } catch (MissingResourceException | InputMismatchException e) {
                printStream.println("Nonexistent language");
                continue;
            }
            userLocale = locale;
        }
    }

    private ArrayList<QuestionAndAnswer> getQuestions(Locale locale) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Questions", locale);
            Enumeration<String> enumeration = rb.getKeys();
            while (enumeration.hasMoreElements()) {
                questionsAndAnswers.add(new QuestionAndAnswer(enumeration.nextElement()));
            }
        } catch (MissingResourceException e) {
            printStream.println(e.toString() + "Unsupported language");
        }
        return questionsAndAnswers;
    }

    private String getQuestion(int index) {
        return questionsAndAnswers.get(index - 1).getQuestion();
    }

    private void setAnswer(int index, String answer) {
        questionsAndAnswers.get(index - 1).setAnswer(answer);
    }

    private void startQuestionnaire() {
        while (true) {
            printStream.print("Input number of question: ");
            int questionIndex = scanner.nextInt();
            if (questionIndex == 0) {
                break;
            }
            printStream.println(getQuestion(questionIndex));
            printStream.println("Your answer: ");
            scanner.nextLine();
            String userAnswer = scanner.nextLine();
            printStream.println(userAnswer);
        }
    }

    public void run() {
        inputLocale();
        getQuestions(userLocale);
        startQuestionnaire();

    }

    public static void main(String[] args) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.run();
    }

}
