package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.ADMON_WILL_GRANT_DRAFT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.ADMON_WILL_GRANT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.CORRESPONDENCE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.DEATH_CERTIFICATE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.DIGITAL_GRANT_DRAFT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.DIGITAL_GRANT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.EDGE_CASE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.EMAIL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.IHT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.INTESTACY_GRANT_DRAFT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.INTESTACY_GRANT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.LEGAL_STATEMENT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.OTHER_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.SENT_EMAIL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.DocumentType.Constants.WILL_NAME;

@RequiredArgsConstructor
@Getter
public enum DocumentType {

    @JsonProperty(LEGAL_STATEMENT_NAME)
    LEGAL_STATEMENT(LEGAL_STATEMENT_NAME),

    @JsonProperty(DIGITAL_GRANT_NAME)
    DIGITAL_GRANT(DIGITAL_GRANT_NAME),

    @JsonProperty(DIGITAL_GRANT_DRAFT_NAME)
    DIGITAL_GRANT_DRAFT(DIGITAL_GRANT_DRAFT_NAME),

    @JsonProperty(INTESTACY_GRANT_NAME)
    INTESTACY_GRANT(INTESTACY_GRANT_NAME),

    @JsonProperty(INTESTACY_GRANT_DRAFT_NAME)
    INTESTACY_GRANT_DRAFT(INTESTACY_GRANT_DRAFT_NAME),

    @JsonProperty(ADMON_WILL_GRANT_NAME)
    ADMON_WILL_GRANT(ADMON_WILL_GRANT_NAME),

    @JsonProperty(ADMON_WILL_GRANT_DRAFT_NAME)
    ADMON_WILL_GRANT_DRAFT(ADMON_WILL_GRANT_DRAFT_NAME),

    @JsonProperty(SENT_EMAIL_NAME)
    SENT_EMAIL(SENT_EMAIL_NAME),

    @JsonProperty(EMAIL_NAME)
    EMAIL(EMAIL_NAME),

    @JsonProperty(IHT_NAME)
    IHT(IHT_NAME),

    @JsonProperty(OTHER_NAME)
    OTHER(OTHER_NAME),

    @JsonProperty(EDGE_CASE_NAME)
    EDGE_CASE(EDGE_CASE_NAME),

    @JsonProperty(DEATH_CERTIFICATE_NAME)
    DEATH_CERT(DEATH_CERTIFICATE_NAME),

    @JsonProperty(CORRESPONDENCE_NAME)
    CORRESPONDENCE(CORRESPONDENCE_NAME),

    @JsonProperty(WILL_NAME)
    WILL(WILL_NAME);
        
    private final String templateName;

    public static class Constants {

        public static final String LEGAL_STATEMENT_NAME = "legalStatement";

        public static final String DIGITAL_GRANT_NAME = "digitalGrant";

        public static final String DIGITAL_GRANT_DRAFT_NAME = "digitalGrantDraft";

        public static final String INTESTACY_GRANT_NAME = "intestacyGrant";

        public static final String INTESTACY_GRANT_DRAFT_NAME = "intestacyGrantDraft";

        public static final String ADMON_WILL_GRANT_NAME = "admonWillGrant";

        public static final String ADMON_WILL_GRANT_DRAFT_NAME = "admonWillGrantDraft";

        public static final String SENT_EMAIL_NAME = "sentEmail";

        public static final String EMAIL_NAME = "email";

        public static final String IHT_NAME = "IHT";

        public static final String OTHER_NAME = "other";

        public static final String EDGE_CASE_NAME = "edgeCase";

        public static final String DEATH_CERTIFICATE_NAME = "deathCertificate";

        public static final String CORRESPONDENCE_NAME = "correspondence";
        
        public static final String WILL_NAME = "will";

        private Constants() {
        }
    }
}
