package locale;

import java.util.*;

public class ResourceService {
    private static Set<Locale> supportedLocales = new HashSet<>();
    private ResourceBundle resourceBundle;
    private List<QuestionAndAnswer> questionsAndAnswers;

    private static final String QUESTION_FORMAT = "%s.%d.question";
    private static final String ANSWER_FORMAT = "%s.%d.answer";

    static {
        supportedLocales.add(new Locale("ru"));
        supportedLocales.add(new Locale("en"));
    }

    public ResourceService(String baseName, Locale locale) {
        if (supportedLocales.contains(locale)) {
            resourceBundle = ResourceBundle.getBundle(baseName, locale);
            questionsAndAnswers = getQuestionsAndAnswers(resourceBundle, baseName);
        } else {
            throw new IllegalArgumentException("Unsupported locale");
        }
    }

    private List<QuestionAndAnswer> getQuestionsAndAnswers(ResourceBundle resourceBundle, String baseName) {
        List<QuestionAndAnswer> questionsAndAnswers = new ArrayList<>();

        int i = 1;
        while (true) {
            String questionKey = String.format(QUESTION_FORMAT, baseName, i);
            String answerKey = String.format(ANSWER_FORMAT, baseName, i);
            if (resourceBundle.containsKey(questionKey)) {
                questionsAndAnswers.add(new QuestionAndAnswer(resourceBundle.getString(questionKey), resourceBundle.getString(answerKey)));
            } else {
                return questionsAndAnswers;
            }
            i++;
        }
    }

    public String getQuestion(int index) {
        return questionsAndAnswers.get(index - 1).getQuestion();
    }

    public String getAnswer(int index) {
        return questionsAndAnswers.get(index - 1).getAnswer();
    }
}
