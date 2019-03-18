package uk.gov.hmcts.reform.probate.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "CcdClientApiErrorResponse")
@NoArgsConstructor
public class CcdClientApiError {

    private String exception;
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
