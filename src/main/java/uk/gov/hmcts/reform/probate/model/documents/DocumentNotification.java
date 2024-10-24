package uk.gov.hmcts.reform.probate.model.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "documentNotification")
public class DocumentNotification implements Serializable {

    @JsonProperty("ccdReference")
    private String ccdReference;

    @JsonProperty("applicantName")
    private String applicantName;

    @JsonProperty("deceasedName")
    private String deceasedName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("deceasedDod")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String deceasedDod;

    @JsonProperty("citizenResponse")
    private String citizenResponse;

    private List<String> fileName;

    @JsonProperty("expectedResponseDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String expectedResponseDate;
}
