package engine.controller;

import engine.model.request.AnswerRequest;
import engine.entity.Quiz;
import engine.model.response.AnswerResponse;
import engine.model.request.QuizRequest;
import engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping()
    public ResponseEntity<Quiz> addQuiz(@RequestBody @Valid QuizRequest quizRequest) {
        return ResponseEntity.ok(quizService.addQuiz(quizRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping()
    public ResponseEntity<Collection<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<AnswerResponse> answerToQuiz(@PathVariable long id,
                                                       @RequestBody AnswerRequest answerRequest) {
        return ResponseEntity.ok(quizService.answerToQuiz(id, answerRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Quiz> deletePost(@PathVariable long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}
