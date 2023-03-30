package engine.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record QuizRequest(@NotNull @NotBlank String title,
                          @NotNull @NotBlank String text,
                          @Size(min = 2) @NotNull String[] options,
                          Integer[] answer) {}
