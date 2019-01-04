package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.ApplicationType.Constants.PERSONAL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.ApplicationType.Constants.SOLICITORS_NAME;

@ApiModel(value = "ApplicationType", description = "Represents application type")
@RequiredArgsConstructor
public enum ApplicationType {

    @JsonProperty(PERSONAL_NAME)PERSONAL(PERSONAL_NAME),
    @JsonProperty(SOLICITORS_NAME) SOLICITORS(SOLICITORS_NAME);

    @Getter
    private final String name;


    public static class Constants {

        public static final String PERSONAL_NAME = "Personal";

        public static final String SOLICITORS_NAME = "Solicitor";

        private Constants() {
        }
    }
}
