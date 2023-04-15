package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_CLOSED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_IMPORTED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_MATCHING_EXAMINING_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_MATCHING_ISSUE_GRANT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_MATCHING_REISSUE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_QA_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_STOPPED_AWAIT_REDEC_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_STOPPED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CASE_STOPPED_REISSUE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_CAVEAT_PERMENANT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_EXAMING_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_EXAMINING_REISSUE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_GRANT_ISSUED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_READY_FOR_EXAMINATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_READY_TO_ISSUE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_REDEC_NOTIFICATION_SENT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_REGISTRAR_ESCALATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_SOT_GENERATED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CASE_CREATED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CASE_PAYMENT_FAILED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CASE_PRINTED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CAVEAT_AWAITING_RESOLUTION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CAVEAT_AWAITING_WARNING_RESPONSE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CAVEAT_CLOSED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CAVEAT_NOT_MATCHED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CAVEAT_RAISED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.CAVEAT_WARNING_VALIDATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.DORMANT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.DRAFT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.PA_APP_CREATED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.STOPPED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.CaseState.Constants.BO_POST_GRANT_ISSUED_NAME;

@RequiredArgsConstructor
public enum CaseState {

    @JsonProperty(DRAFT_NAME) DRAFT(DRAFT_NAME),

    @JsonProperty(PA_APP_CREATED_NAME) PA_APP_CREATED(PA_APP_CREATED_NAME),

    @JsonProperty(CASE_PAYMENT_FAILED_NAME) CASE_PAYMENT_FAILED(CASE_PAYMENT_FAILED_NAME),

    @JsonProperty(CASE_CREATED_NAME) CASE_CREATED(CASE_CREATED_NAME),

    @JsonProperty(CAVEAT_RAISED_NAME) CAVEAT_RAISED(CAVEAT_RAISED_NAME),

    @JsonProperty(STOPPED_NAME) STOPPED(STOPPED_NAME),

    @JsonProperty(CASE_PRINTED_NAME) CASE_PRINTED(CASE_PRINTED_NAME),

    @JsonProperty(BO_READY_FOR_EXAMINATION_NAME) BO_READY_FOR_EXAMINATION(BO_READY_FOR_EXAMINATION_NAME),

    @JsonProperty(BO_EXAMING_NAME) BO_EXAMING(BO_EXAMING_NAME),

    @JsonProperty(BO_CASE_STOPPED_NAME) BO_CASE_STOPPED(BO_CASE_STOPPED_NAME),

    @JsonProperty(BO_CAVEAT_PERMENANT_NAME) BO_CAVEAT_PERMENANT(BO_CAVEAT_PERMENANT_NAME),

    @JsonProperty(BO_REGISTRAR_ESCALATION_NAME) BO_REGISTRAR_ESCALATION(BO_REGISTRAR_ESCALATION_NAME),

    @JsonProperty(BO_READY_TO_ISSUE_NAME) BO_READY_TO_ISSUE(BO_READY_TO_ISSUE_NAME),

    @JsonProperty(BO_CASE_QA_NAME) BO_CASE_QA(BO_CASE_QA_NAME),

    @JsonProperty(BO_CASE_MATCHING_ISSUE_GRANT_NAME) BO_CASE_MATCHING_ISSUE_GRANT(BO_CASE_MATCHING_ISSUE_GRANT_NAME),

    @JsonProperty(BO_CASE_MATCHING_EXAMINING_NAME) BO_CASE_MATCHING_EXAMINING(BO_CASE_MATCHING_EXAMINING_NAME),

    @JsonProperty(BO_GRANT_ISSUED_NAME) BO_GRANT_ISSUED(BO_GRANT_ISSUED_NAME),

    @JsonProperty(BO_CASE_CLOSED_NAME) BO_CASE_CLOSED(BO_CASE_CLOSED_NAME),

    @JsonProperty(BO_CASE_IMPORTED_NAME) BO_CASE_IMPORTED(BO_CASE_IMPORTED_NAME),

    @JsonProperty(BO_EXAMINING_REISSUE_NAME) BO_EXAMINING_REISSUE(BO_EXAMINING_REISSUE_NAME),

    @JsonProperty(BO_CASE_MATCHING_REISSUE_NAME) BO_CASE_MATCHING(BO_CASE_MATCHING_REISSUE_NAME),

    @JsonProperty(BO_CASE_STOPPED_REISSUE_NAME) BO_CASE_STOPPED_REISSUE(BO_CASE_STOPPED_REISSUE_NAME),

    @JsonProperty(BO_CASE_STOPPED_AWAIT_REDEC_NAME) BO_CASE_STOPPED_AWAIT_REDEC(BO_CASE_STOPPED_AWAIT_REDEC_NAME),

    @JsonProperty(BO_REDEC_NOTIFICATION_SENT_NAME) BO_REDEC_NOTIFICATION_SENT(BO_REDEC_NOTIFICATION_SENT_NAME),

    @JsonProperty(BO_SOT_GENERATED_NAME) BO_SOT_GENERATED(BO_SOT_GENERATED_NAME),

    @JsonProperty(CAVEAT_NOT_MATCHED_NAME) CAVEAT_NOT_MATCHED(CAVEAT_NOT_MATCHED_NAME),
    
    @JsonProperty(CAVEAT_AWAITING_RESOLUTION_NAME) CAVEAT_AWAITING_RESOLUTION(CAVEAT_AWAITING_RESOLUTION_NAME),
    
    @JsonProperty(CAVEAT_WARNING_VALIDATION_NAME) CAVEAT_WARNING_VALIDATION(CAVEAT_WARNING_VALIDATION_NAME),
    
    @JsonProperty(CAVEAT_AWAITING_WARNING_RESPONSE_NAME) 
        CAVEAT_AWAITING_WARNING_RESPONSE(CAVEAT_AWAITING_WARNING_RESPONSE_NAME),

    @JsonProperty(CAVEAT_CLOSED_NAME) CAVEAT_CLOSED(CAVEAT_CLOSED_NAME),

    @JsonProperty(DORMANT_NAME) DORMANT(DORMANT_NAME),
    @JsonProperty(BO_POST_GRANT_ISSUED_NAME) BO_POST_GRANT_ISSUED(BO_POST_GRANT_ISSUED_NAME);

    @Getter
    private final String name;

    public static CaseState getState(String name) {
        return Arrays.stream(CaseState.values()).filter(caseState -> caseState.getName().equals(name)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Cannot find CaseState enum for name: " + name));
    }

    public static class Constants {

        public static final String DRAFT_NAME = "Pending";

        public static final String PA_APP_CREATED_NAME = "PAAppCreated";

        public static final String CASE_PAYMENT_FAILED_NAME = "CasePaymentFailed";

        public static final String CASE_CREATED_NAME = "CaseCreated";

        public static final String CAVEAT_RAISED_NAME = "CaveatRaised";

        public static final String STOPPED_NAME = "Stopped";

        public static final String CASE_PRINTED_NAME = "CasePrinted";

        public static final String BO_READY_FOR_EXAMINATION_NAME = "BOReadyForExamination";

        public static final String BO_EXAMING_NAME = "BOExamining";

        public static final String BO_CASE_STOPPED_NAME = "BOCaseStopped";

        public static final String BO_CAVEAT_PERMENANT_NAME = "BOCaveatPermenant";

        public static final String BO_REGISTRAR_ESCALATION_NAME = "BORegistrarEscalation";

        public static final String BO_READY_TO_ISSUE_NAME = "BOReadyToIssue";

        public static final String BO_CASE_QA_NAME = "BOCaseQA";

        public static final String BO_CASE_MATCHING_ISSUE_GRANT_NAME = "BOCaseMatchingIssueGrant";

        public static final String BO_CASE_MATCHING_EXAMINING_NAME = "BOCaseMatchingExamining";

        public static final String BO_GRANT_ISSUED_NAME = "BOGrantIssued";

        public static final String BO_CASE_CLOSED_NAME = "BOCaseClosed";

        public static final String BO_CASE_IMPORTED_NAME = "BOCaseImported";

        public static final String BO_EXAMINING_REISSUE_NAME = "BOExaminingReissue";

        public static final String BO_CASE_MATCHING_REISSUE_NAME = "BOCaseMatchingReissue";

        public static final String BO_CASE_STOPPED_REISSUE_NAME = "BOCaseStoppedReissue";

        public static final String BO_CASE_STOPPED_AWAIT_REDEC_NAME = "BOCaseStoppedAwaitRedec";

        public static final String BO_REDEC_NOTIFICATION_SENT_NAME = "BORedecNotificationSent";

        public static final String BO_SOT_GENERATED_NAME = "BOSotGenerated";

        public static final String CAVEAT_NOT_MATCHED_NAME = "CaveatNotMatched";

        public static final String CAVEAT_AWAITING_RESOLUTION_NAME = "AwaitingCaveatResolution";

        public static final String CAVEAT_WARNING_VALIDATION_NAME = "WarningValidation";

        public static final String CAVEAT_AWAITING_WARNING_RESPONSE_NAME = "AwaitingWarningResponse";
        
        public static final String CAVEAT_CLOSED_NAME = "CaveatClosed";

        public static final String DORMANT_NAME = "Dormant";
	public static final String BO_POST_GRANT_ISSUED_NAME = "BOPostGrantIssued";

        private Constants() {
        }
    }
}