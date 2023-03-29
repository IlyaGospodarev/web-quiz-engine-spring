package engine.model.request;

import org.springframework.stereotype.Component;

@Component
public class Answer {
    private Integer[] answer;

    public Answer() {
    }

    public Answer(Integer[] answer) {
        this.answer = answer;
    }

    public Integer[] getAnswer() {
        return answer;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }
}
