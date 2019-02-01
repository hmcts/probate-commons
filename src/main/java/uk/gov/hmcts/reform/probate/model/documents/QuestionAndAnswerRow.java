package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionAndAnswerRow implements Serializable {

    @NotBlank
    @JsonProperty("question")
    private String question;

    @JsonProperty("answers")
    private List<String> answers = new ArrayList<>();

    private static final String[] notAnswered = {"not answered"};

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {

        if (answers.isEmpty() || answers.contains("")) {
            return Arrays.asList(notAnswered);
        }

        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }



}
