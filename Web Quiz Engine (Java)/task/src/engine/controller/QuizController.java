package engine.controller;

import engine.dto.ResponseDto;
import engine.entity.Completion;
import engine.entity.Quiz;
import engine.service.QuizService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        return service.getQuizById(id);
    }

    @GetMapping
    public Page<Quiz> getQuizzes(@RequestParam(required = false, defaultValue = "0", name = "page") int page) {
        return service.getAllQuizzes(page);
    }

    @PostMapping
    public Quiz addQuiz(@Valid @NotNull @RequestBody Quiz quiz) {
        return service.addQuiz(quiz);
    }

    @PostMapping("/{id}/solve")
    public ResponseDto solveQuiz(@PathVariable int id, @RequestBody Quiz answer) {
        return service.solveQuizById(id, answer.getAnswer());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable int id) {
        service.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completed")
    public Page<Completion> getCompleted(@RequestParam(required = false, defaultValue = "0", name = "page") int page) {
        return service.getCompletedQuizzes(page);
    }
}
