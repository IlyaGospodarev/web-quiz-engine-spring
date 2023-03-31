package engine.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserRequest(Long id,
                          @NotNull @NotBlank(message = "Email is required")  @Email(regexp = ".+@.+\\..+") String email,
                          @NotNull @NotBlank(message = "Password is required") @Size(min = 5) @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String password) {
}
