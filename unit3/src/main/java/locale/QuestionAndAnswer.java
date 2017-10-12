package locale;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

class QuestionAndAnswer {

    private String question;
    private String answer;

    public QuestionAndAnswer(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {

        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
