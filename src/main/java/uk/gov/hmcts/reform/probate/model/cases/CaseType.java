package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.CAVEAT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.GRANT_OF_REPRESENTATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.STANDING_SEARCH_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.WILL_LODGEMENT_NAME;


@ApiModel(value = "CaseType", description = "Represents case type")
@RequiredArgsConstructor
public enum CaseType {

    @JsonProperty(GRANT_OF_REPRESENTATION_NAME) GRANT_OF_REPRESENTATION(GRANT_OF_REPRESENTATION_NAME),
    @JsonProperty(CAVEAT_NAME) CAVEAT(CAVEAT_NAME),
    @JsonProperty(STANDING_SEARCH_NAME) STANDING_SEARCH(STANDING_SEARCH_NAME),
    @JsonProperty(WILL_LODGEMENT_NAME) WILL_LODGEMENT(WILL_LODGEMENT_NAME);

    @Getter
    private final String name;

    public static CaseType getCaseType(CaseData caseData) {
        String className = caseData.getClass().getSimpleName().replace("Data", "");
        return Arrays.stream(CaseType.values())
                .filter(caseType -> caseType.getName().equals(className))
                .findFirst().orElseThrow(() ->
                        new IllegalArgumentException("Cannot find case type associated with class: " + className));

    }

    public static class Constants {

        public static final String GRANT_OF_REPRESENTATION_NAME = "GrantOfRepresentation";

        public static final String CAVEAT_NAME = "Caveat";

        public static final String STANDING_SEARCH_NAME = "StandingSearch";

        public static final String WILL_LODGEMENT_NAME = "WillLodgement";

        private Constants() {
        }
    }
}


