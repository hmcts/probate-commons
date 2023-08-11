package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.caveat.CaveatData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;
import uk.gov.hmcts.reform.probate.model.cases.standingsearch.StandingSearchData;
import uk.gov.hmcts.reform.probate.model.cases.willlodgement.WillLodgementData;

import java.util.Arrays;

import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.CAVEAT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.GRANT_OF_REPRESENTATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.STANDING_SEARCH_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.WILL_LODGEMENT_NAME;


@Schema(name = "CaseType", description = "Represents case type")
@RequiredArgsConstructor
@Getter
public enum CaseType {

    @JsonProperty(GRANT_OF_REPRESENTATION_NAME) GRANT_OF_REPRESENTATION(
            GrantOfRepresentationData.class,
            GRANT_OF_REPRESENTATION_NAME
    ),
    @JsonProperty(CAVEAT_NAME) CAVEAT(
            CaveatData.class,
            CAVEAT_NAME
    ),
    @JsonProperty(STANDING_SEARCH_NAME) STANDING_SEARCH(
            StandingSearchData.class,
            STANDING_SEARCH_NAME
    ),
    @JsonProperty(WILL_LODGEMENT_NAME) WILL_LODGEMENT(
            WillLodgementData.class,
            WILL_LODGEMENT_NAME
    );

    private final Class<? extends CaseData> caseDataType;

    private final String name;

    public static CaseType getCaseType(CaseData caseData) {
        return Arrays.stream(CaseType.values())
                .filter(caseType -> caseType.getCaseDataType().equals(caseData.getClass()))
                .findFirst().orElseThrow(() ->
                        new IllegalArgumentException("Cannot find case type associated with class: "
                                + caseData.getClass().getSimpleName()));

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


