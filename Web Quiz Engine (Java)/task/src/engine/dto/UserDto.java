package engine.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserDto {
    private Long id;

    @Email(regexp = ".+@.+\\..+")
    private String email;

    @Size(min = 5)
    private String password;

    public UserDto() {
    }

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
