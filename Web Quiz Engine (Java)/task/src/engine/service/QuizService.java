package engine.service;

import engine.entity.Quiz;
import engine.entity.User;
import engine.model.request.AnswerRequest;
import engine.model.response.AnswerResponse;
import engine.model.request.QuizRequest;
import engine.model.response.exceptions.InvalidIDException;
import engine.model.response.exceptions.InvalidUserException;
import engine.model.response.exceptions.NotPermittedException;
import engine.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        quiz.setUser(user);
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

    public void deleteQuiz(long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException(HttpStatus.NOT_FOUND));

        if (user == null) {
            throw new InvalidUserException(HttpStatus.NOT_FOUND);
        }
        if (quiz.getUser().getId() != user.getId()) {
            throw new NotPermittedException(HttpStatus.FORBIDDEN);
        }

        quizRepository.delete(quiz);
    }
}