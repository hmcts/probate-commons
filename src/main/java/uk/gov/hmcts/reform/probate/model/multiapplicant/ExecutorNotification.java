package uk.gov.hmcts.reform.probate.model.multiapplicant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "executorNotification")
public class ExecutorNotification implements Serializable {

    @JsonProperty("ccdReference")
    private String ccdReference;

    @JsonProperty("executorName")
    private String executorName;

    @JsonProperty("applicantName")
    private String applicantName;

    @JsonProperty("deceasedName")
    private String deceasedName;

    @JsonProperty("email")
    private String email;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deceasedDod;

}
