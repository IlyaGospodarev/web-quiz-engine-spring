package engine.service;

import engine.entity.Quiz;
import engine.model.request.AnswerRequest;
import engine.model.response.AnswerResponse;
import engine.model.request.QuizRequest;
import engine.model.response.exceptions.InvalidIDException;
import engine.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz addQuiz(QuizRequest quizRequest) {

        Quiz quiz = new Quiz(quizRequest.title(),
                             quizRequest.text(),
                             quizRequest.options(),
                             quizRequest.answer());

        quizRepository.save(quiz);

        return quiz;
    }

    public Quiz getQuizById(long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException(HttpStatus.NOT_FOUND));
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public AnswerResponse answerToQuiz(long id, AnswerRequest answerRequest) {

        AnswerResponse answerTrue = new AnswerResponse(true,
                                                       "Congratulations, you're right!");
        AnswerResponse answerFalse = new AnswerResponse(false,
                                                        "Wrong answer! Please, try again.");

        Quiz quiz = quizRepository.findById(id)
                        .orElseThrow(() -> new InvalidIDException(HttpStatus.NOT_FOUND));

        if (Arrays.equals(answerRequest.answer(), quiz.getAnswer())) {
            return answerTrue;
        } else {
            return answerFalse;
        }
    }
}