package engine.service;

import engine.model.request.Quiz;
import engine.model.response.AnswerResponse;
import engine.model.response.QuizResponse;
import engine.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizResponse addQuiz(Quiz quiz) {
        return quizRepository.addQuiz(quiz);
    }

    public Quiz getQuizById(int id) {
        return quizRepository.findQuizById(id);
    }

    public Collection<Quiz> getAllQuizzes() {
        return quizRepository.findAllQuiz();
    }

    public AnswerResponse answerToQuiz(int id, Integer[] answer) {
        return quizRepository.solveQuiz(id, answer);
    }
}
