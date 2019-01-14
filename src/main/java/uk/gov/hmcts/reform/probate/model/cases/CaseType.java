package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.caveat.CaveatData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;
import uk.gov.hmcts.reform.probate.model.cases.standingsearch.StandingSearchData;
import uk.gov.hmcts.reform.probate.model.cases.willlodgement.WillLodgementData;

import java.util.Arrays;
import java.util.function.Function;

import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.CAVEAT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.GRANT_OF_REPRESENTATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.STANDING_SEARCH_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.Constants.WILL_LODGEMENT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.SearchFunctions.CAVEAT_SEARCH_FUNCTION;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.SearchFunctions.GOP_SEARCH_FUNCTION;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.SearchFunctions.SS_SEARCH_FUNCTION;
import static uk.gov.hmcts.reform.probate.model.cases.CaseType.SearchFunctions.WILL_LODGEMENT_SEARCH_FUNCTION;
import static uk.gov.hmcts.reform.probate.model.cases.EventId.GOP_CREATE_APPLICATION;
import static uk.gov.hmcts.reform.probate.model.cases.EventId.GOP_CREATE_CASE;
import static uk.gov.hmcts.reform.probate.model.cases.EventId.GOP_CREATE_DRAFT;
import static uk.gov.hmcts.reform.probate.model.cases.EventId.GOP_PAYMENT_FAILED;
import static uk.gov.hmcts.reform.probate.model.cases.EventId.GOP_PAYMENT_FAILED_AGAIN;
import static uk.gov.hmcts.reform.probate.model.cases.EventId.GOP_PAYMENT_FAILED_TO_SUCCESS;
import static uk.gov.hmcts.reform.probate.model.cases.EventId.GOP_UPDATE_DRAFT;


@ApiModel(value = "CaseType", description = "Represents case type")
@RequiredArgsConstructor
@Getter
public enum CaseType {

    @JsonProperty(GRANT_OF_REPRESENTATION_NAME) GRANT_OF_REPRESENTATION(
            GrantOfRepresentationData.class,
            GRANT_OF_REPRESENTATION_NAME,
            CaseEvents.builder()
                    .createCaseApplicationEventId(GOP_CREATE_APPLICATION)
                    .createCaseEventId(GOP_CREATE_CASE)
                    .createDraftEventId(GOP_CREATE_DRAFT)
                    .paymentFailedAgainEventId(GOP_PAYMENT_FAILED_AGAIN)
                    .paymentFailedEventId(GOP_PAYMENT_FAILED)
                    .paymentFailedToSuccessEventId(GOP_PAYMENT_FAILED_TO_SUCCESS)
                    .updateDraftEventId(GOP_UPDATE_DRAFT)
                    .build(),
            new SearchField("primaryApplicantEmailAddress", GOP_SEARCH_FUNCTION)
    ),
    @JsonProperty(CAVEAT_NAME) CAVEAT(
            CaveatData.class,
            CAVEAT_NAME,
            CaseEvents.builder()
                    .createCaseApplicationEventId(GOP_CREATE_APPLICATION)
                    .createCaseEventId(GOP_CREATE_CASE)
                    .createDraftEventId(GOP_CREATE_DRAFT)
                    .paymentFailedAgainEventId(GOP_PAYMENT_FAILED_AGAIN)
                    .paymentFailedEventId(GOP_PAYMENT_FAILED)
                    .paymentFailedToSuccessEventId(GOP_PAYMENT_FAILED_TO_SUCCESS)
                    .updateDraftEventId(GOP_UPDATE_DRAFT)
                    .build(),
            new SearchField("caveatorEmailAddress", CAVEAT_SEARCH_FUNCTION)
    ),
    @JsonProperty(STANDING_SEARCH_NAME) STANDING_SEARCH(
            StandingSearchData.class,
            STANDING_SEARCH_NAME,
            CaseEvents.builder()
                    .createCaseApplicationEventId(GOP_CREATE_APPLICATION)
                    .createCaseEventId(GOP_CREATE_CASE)
                    .createDraftEventId(GOP_CREATE_DRAFT)
                    .paymentFailedAgainEventId(GOP_PAYMENT_FAILED_AGAIN)
                    .paymentFailedEventId(GOP_PAYMENT_FAILED)
                    .paymentFailedToSuccessEventId(GOP_PAYMENT_FAILED_TO_SUCCESS)
                    .updateDraftEventId(GOP_UPDATE_DRAFT)
                    .build(),
            new SearchField("applicantEmailAddress", SS_SEARCH_FUNCTION)
    ),
    @JsonProperty(WILL_LODGEMENT_NAME) WILL_LODGEMENT(
            WillLodgementData.class,
            WILL_LODGEMENT_NAME,
            CaseEvents.builder()
                    .createCaseApplicationEventId(GOP_CREATE_APPLICATION)
                    .createCaseEventId(GOP_CREATE_CASE)
                    .createDraftEventId(GOP_CREATE_DRAFT)
                    .paymentFailedAgainEventId(GOP_PAYMENT_FAILED_AGAIN)
                    .paymentFailedEventId(GOP_PAYMENT_FAILED)
                    .paymentFailedToSuccessEventId(GOP_PAYMENT_FAILED_TO_SUCCESS)
                    .updateDraftEventId(GOP_UPDATE_DRAFT)
                    .build(),
            new SearchField("deceasedEmailAddress", WILL_LODGEMENT_SEARCH_FUNCTION)
    );

    private final Class<? extends CaseData> caseDataType;

    private final String name;

    private final CaseEvents caseEvents;

    private final SearchField searchField;

    public static CaseType getCaseType(CaseData caseData) {
        return Arrays.stream(CaseType.values())
                .filter(caseType -> caseType.getCaseDataType().equals(caseData.getClass()))
                .findFirst().orElseThrow(() ->
                        new IllegalArgumentException("Cannot find case type associated with class: "
                                + caseData.getClass().getSimpleName()));

    }

    public static class SearchFunctions {

        public static final Function<GrantOfRepresentationData, String> GOP_SEARCH_FUNCTION =
            gop -> gop.getPrimaryApplicantEmailAddress();

        public static final Function<WillLodgementData, String> WILL_LODGEMENT_SEARCH_FUNCTION =
            wl -> wl.getDeceasedEmailAddress();

        public static final Function<CaveatData, String> CAVEAT_SEARCH_FUNCTION =
            caveat -> caveat.getCaveatorEmailAddress();

        public static final Function<StandingSearchData, String> SS_SEARCH_FUNCTION =
            ss -> ss.getApplicantEmailAddress();

        private SearchFunctions() {
        }
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


