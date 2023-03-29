package engine.repository;

import engine.model.request.Answer;
import engine.model.request.Quiz;
import engine.model.response.AnswerResponse;
import engine.model.response.QuizResponse;
import engine.model.response.exceptions.InvalidIDException;
import engine.model.response.exceptions.InvalidValidationJsonException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuizRepository {
    private Map<Integer, Quiz> quizzes = new HashMap<>();
    private int id = 1;

    public QuizResponse addQuiz(Quiz quiz) {

        if ((quiz.getTitle().isEmpty() || quiz.getTitle() == null) ||
                (quiz.getText().isEmpty() || quiz.getText() == null) ||
                quiz.getOptions().length < 2) {
            throw new InvalidValidationJsonException(HttpStatus.BAD_REQUEST);
        }

        quiz.setId(id);
        QuizResponse quizResponse = new QuizResponse(quiz.getId(),
                                                     quiz.getTitle(),
                                                     quiz.getText(),
                                                     quiz.getOptions());

        quizzes.put(id, quiz);
        id++;

        return quizResponse;
    }

    public Quiz findQuizById(int id) {
        if (quizzes.get(id) == null) {
            throw new InvalidIDException(HttpStatus.NOT_FOUND);
        } else return quizzes.get(id);
    }

    public Collection<Quiz> findAllQuiz() {
        return quizzes.values();
    }

    public AnswerResponse solveQuiz(int id, Integer[] answer) {
        AnswerResponse answerResponse = new AnswerResponse();

        Integer[] zero = new Integer[]{0};

        if (quizzes.get(id) == null) {
            throw new InvalidIDException(HttpStatus.NOT_FOUND);
        } else if (quizzes.get(id).getAnswer() == null && !(Arrays.equals(answer, zero))
                || Arrays.equals(quizzes.get(id).getAnswer(), answer)) {
            answerResponse.setSuccess(true);
            answerResponse.setFeedback("Congratulations, you're right!");
        } else {
            answerResponse.setSuccess(false);
            answerResponse.setFeedback("Wrong answer! Please, try again.");
        }

        return answerResponse;
    }
}
