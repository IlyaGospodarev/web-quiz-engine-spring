package engine.service;

import engine.dto.ResponseDto;
import engine.entity.Completion;
import engine.entity.Quiz;
import engine.entity.User;
import engine.exceptions.InvalidAccessException;
import engine.exceptions.InvalidAnswerException;
import engine.exceptions.QuizNotFoundException;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final CompletionRepository completionRepository;

    public QuizService(QuizRepository quizRepository, CompletionRepository completionRepository) {
        this.quizRepository = quizRepository;
        this.completionRepository = completionRepository;
    }

    public Quiz addQuiz(Quiz quiz) {
        User user = getUser();
        quiz.setOwnerEmail(user.getEmail());

        if(quiz.getOptions() == null) {
            throw new InvalidAnswerException();
        } else {

            int numberOfOptionsInQuiz = quiz.getOptions().size();

            for (Integer eachAnswer : quiz.getAnswer()) {
                if (eachAnswer < 0 || eachAnswer > numberOfOptionsInQuiz) {
                    throw new InvalidAnswerException();
                }
            }
        }

        quizRepository.save(quiz);

        return quiz;
    }

    public Page<Quiz> getAllQuizzes(int page) {
        String properties = "id";
        Pageable paging = getPageable(page, properties);

        return quizRepository.findAll(paging);
    }

    public Quiz getQuizById(int id) {
        return getById(id);
    }

    public ResponseDto solveQuizById(int id, Set<Integer> answer) {
        Quiz quiz = getById(id);

        if (Arrays.equals(quiz.getAnswer()
                                  .toArray(), answer.toArray())) {
            User user = getUser();
            completionRepository.save(new Completion(user.getEmail(), id, new Date()));

            return new ResponseDto(true, "Congratulations, you're right!");
        } else {
            return new ResponseDto(false, "Wrong answer! Please, try again.");
        }
    }

    public void deleteQuizById(int id) {
        Quiz quiz = getById(id);

        User user = getUser();

        if (!quiz.getOwnerEmail()
                .equals(user.getEmail())) {
            throw  new InvalidAccessException();
        }

        quizRepository.deleteById(id);
    }

    public Page<Completion> getCompletedQuizzes(int page) {
        String properties = "completedAt";
        Pageable paging = getPageable(page, properties);

        User user = getUser();

        return completionRepository.findAllByUserEmail(user.getEmail(), paging);
    }

    private Quiz getById(int id) {
        return quizRepository.findById(id)
                .orElseThrow(QuizNotFoundException::new);
    }

    private static Pageable getPageable(int page, String properties) {
        int pageSize = 10;
        return PageRequest.of(page, pageSize, Sort.by(properties)
                .descending());
    }

    private static User getUser() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}