package engine.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @NotBlank
    @NotNull
    private String title;
    @NotEmpty
    @NotBlank
    @NotNull
    private String text;
    @Size(min = 2)
    @NotNull
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer[] answer;

    public Quiz() {
    }

    public Quiz(long id, String title, String text, String[] options, Integer[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer == null ? new Integer[]{} : answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Integer[] getAnswer() {
        return answer;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quiz quiz)) return false;

        if (id != quiz.id) return false;
        if (!title.equals(quiz.title)) return false;
        return text.equals(quiz.text);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
