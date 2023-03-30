package engine.service;

import engine.model.request.Quiz;
import engine.model.response.AnswerResponse;
import engine.model.response.QuizResponse;
import engine.model.response.exceptions.InvalidIDException;
import engine.model.response.exceptions.InvalidValidationJsonException;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public QuizService() {}

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizResponse addQuiz(Quiz quiz) {

        if ((quiz.getTitle().isEmpty() || quiz.getTitle() == null) ||
                (quiz.getText().isEmpty() || quiz.getText() == null) ||
                quiz.getOptions().length < 2) {
            throw new InvalidValidationJsonException(HttpStatus.BAD_REQUEST);
        }

        QuizResponse quizResponse = new QuizResponse(quiz.getId(),
                                                     quiz.getTitle(),
                                                     quiz.getText(),
                                                     quiz.getOptions());

        quizRepository.save(quiz);

        return quizResponse;
    }

    public Quiz getQuizById(long id) {

        if (quizRepository.findById(id) == null) {
            throw new InvalidIDException(HttpStatus.NOT_FOUND);
        }

        return quizRepository.findById(id);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public AnswerResponse answerToQuiz(int id, Integer[] answer) {

        AnswerResponse answerTrue = new AnswerResponse(true,
                                                       "Congratulations, you're right!");
        AnswerResponse answerFalse = new AnswerResponse(false,
                                                        "Wrong answer! Please, try again.");

        Integer[] emptyArray = {};

        Quiz quiz = quizRepository.findById(id);

        if (quiz == null) {
            throw new InvalidIDException(HttpStatus.NOT_FOUND);
        }

        if (quiz.getAnswer() == null && Arrays.equals(answer, emptyArray)
                || Arrays.equals(quiz.getAnswer(), answer)) {
            return answerTrue;
        } else return answerFalse;
    }
}



//    private Map<Integer, Quiz> quizzes = new HashMap<>();
//    private int id = 1;
//
//    public QuizResponse addQuiz(Quiz quiz) {
//
//        if ((quiz.getTitle()
//                .isEmpty() || quiz.getTitle() == null) ||
//                (quiz.getText()
//                        .isEmpty() || quiz.getText() == null) ||
//                quiz.getOptions().length < 2) {
//            throw new InvalidValidationJsonException(HttpStatus.BAD_REQUEST);
//        }
//
//        quiz.setId(id);
//        QuizResponse quizResponse = new QuizResponse(quiz.getId(),
//                                                     quiz.getTitle(),
//                                                     quiz.getText(),
//                                                     quiz.getOptions());
//
//        quizzes.put(id, quiz);
//        id++;
//
//        return quizResponse;
//    }
//
//    public Quiz findQuizById(int id) {
//        if (quizzes.get(id) == null) {
//            throw new InvalidIDException(HttpStatus.NOT_FOUND);
//        } else return quizzes.get(id);
//    }
//
//    public Collection<Quiz> findAllQuiz() {
//        return quizzes.values();
//    }
//
//    public AnswerResponse solveQuiz(int id, Integer[] answer) {
//        AnswerResponse answerTrue = new AnswerResponse(true,
//                                                       "Congratulations, you're right!");
//        AnswerResponse answerFalse = new AnswerResponse(false,
//                                                        "Wrong answer! Please, try again.");
//
//        Integer[] zero = {};
//
//        if (quizzes.get(id) == null) {
//            throw new InvalidIDException(HttpStatus.NOT_FOUND);
//        }
//
//        if (quizzes.get(id).getAnswer() == null && Arrays.equals(answer, zero)
//                || Arrays.equals(quizzes.get(id).getAnswer(), answer)) {
//
//            return answerTrue;
//        } else return answerFalse;
//    }