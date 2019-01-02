package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.CaseType;

import static uk.gov.hmcts.reform.probate.model.ProbateType.Constants.CAVEAT_NAME;
import static uk.gov.hmcts.reform.probate.model.ProbateType.Constants.INTESTACY_NAME;
import static uk.gov.hmcts.reform.probate.model.ProbateType.Constants.PERSONAL_NAME;

@ApiModel(value = "ProbateType", description = "Represents probate type")
@RequiredArgsConstructor
public enum ProbateType {

    @JsonProperty(INTESTACY_NAME) INTESTACY(INTESTACY_NAME, CaseType.GRANT_OF_REPRESENTATION),
    @JsonProperty(PERSONAL_NAME) PERSONAL(PERSONAL_NAME, CaseType.GRANT_OF_REPRESENTATION),
    @JsonProperty(CAVEAT_NAME) CAVEAT(CAVEAT_NAME, CaseType.CAVEAT);

    @Getter
    private final String name;

    @Getter
    private final CaseType caseType;

    public static class Constants {

        public static final String INTESTACY_NAME = "Intestacy";
        public static final String PERSONAL_NAME = "Personal";
        public static final String CAVEAT_NAME = "Caveat";

        private Constants() {
        }
    }
}
