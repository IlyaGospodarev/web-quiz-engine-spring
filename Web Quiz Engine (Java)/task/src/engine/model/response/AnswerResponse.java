package engine.model.response;

import org.springframework.stereotype.Component;

@Component
public class AnswerResponse {
    private boolean success;

    private String feedback;

    public AnswerResponse() {}

    public AnswerResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
