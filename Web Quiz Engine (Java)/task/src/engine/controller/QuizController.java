package engine.controller;

import engine.model.request.Answer;
import engine.model.request.Quiz;
import engine.model.response.AnswerResponse;
import engine.model.response.QuizResponse;
import engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quizzes")
    public ResponseEntity<QuizResponse> addQuiz(@RequestBody @Valid Quiz quiz) {
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("/quizzes")
    public ResponseEntity<Collection<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<AnswerResponse> answerToQuiz(@PathVariable int id,
                                                       @RequestBody Answer answer) {
        return ResponseEntity.ok(quizService.answerToQuiz(id, answer.getAnswer()));
    }
}
