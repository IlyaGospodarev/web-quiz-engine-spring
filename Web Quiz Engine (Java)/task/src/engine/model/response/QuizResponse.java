package engine.model.response;

import javax.persistence.*;

@Entity
public class QuizResponse {
    @Id
    private long id;
    @Column
    private String title;
    @Column
    private String text;
    @Column
    private String[] options;

    public QuizResponse() {
    }

    public QuizResponse(long id, String title, String text, String[] options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
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
}
