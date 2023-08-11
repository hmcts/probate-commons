package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.CaseType;

import static uk.gov.hmcts.reform.probate.model.ProbateType.Constants.CAVEAT_NAME;
import static uk.gov.hmcts.reform.probate.model.ProbateType.Constants.INTESTACY_NAME;
import static uk.gov.hmcts.reform.probate.model.ProbateType.Constants.PA_NAME;

@Schema(name = "ProbateType", description = "Represents probate type")
@RequiredArgsConstructor
public enum ProbateType {

    @JsonProperty(INTESTACY_NAME) INTESTACY(INTESTACY_NAME, CaseType.GRANT_OF_REPRESENTATION),
    @JsonProperty(PA_NAME) PA(PA_NAME, CaseType.GRANT_OF_REPRESENTATION),
    @JsonProperty(CAVEAT_NAME) CAVEAT(CAVEAT_NAME, CaseType.CAVEAT);

    @Getter
    private final String name;

    @Getter
    private final CaseType caseType;

    public static class Constants {

        public static final String INTESTACY_NAME = "intestacy";
        public static final String PA_NAME = "gop";
        public static final String CAVEAT_NAME = "Caveat";

        private Constants() {
        }
    }
}
