package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum ProbateDocumentType {

    @JsonProperty("legalStatement")
    LEGAL_STATEMENT("legalStatement"),

    @JsonProperty("digitalGrant")
    DIGITAL_GRANT("digitalGrant"),

    @JsonProperty("grantCover")
    GRANT_COVER("grantCover"),

    @JsonProperty("solicitorCoverSheet")
    SOLICITOR_COVERSHEET("solicitorCoverSheet"),

    @JsonProperty("blank")
    BLANK("blank"),

    @JsonProperty("digitalGrantDraft")
    DIGITAL_GRANT_DRAFT("digitalGrantDraft"),

    @JsonProperty("willLodgementDepositReceipt")
    WILL_LODGEMENT_DEPOSIT_RECEIPT("willLodgementDepositReceipt"),

    @JsonProperty("intestacyGrant")
    INTESTACY_GRANT("intestacyGrant"),

    @JsonProperty("intestacyGrantDraft")
    INTESTACY_GRANT_DRAFT("intestacyGrantDraft"),

    @JsonProperty("admonWillGrant")
    ADMON_WILL_GRANT("admonWillGrant"),

    @JsonProperty("admonWillGrantDraft")
    ADMON_WILL_GRANT_DRAFT("admonWillGrantDraft"),

    @JsonProperty("sentEmail")
    SENT_EMAIL("sentEmail"),

    @JsonProperty("email")
    EMAIL("email"),

    @JsonProperty("IHT")
    IHT("IHT"),

    @JsonProperty("other")
    OTHER("other"),

    @JsonProperty("edgeCase")
    EDGE_CASE("edgeCase"),

    @JsonProperty("deathCertificate")
    DEATH_CERT("deathCertificate"),

    @JsonProperty("correspondence")
    CORRESPONDENCE("correspondence"),

    @JsonProperty("caveatCoversheet")
    CAVEAT_COVERSHEET("caveatCoversheet"),

    @JsonProperty("caveatRaised")
    CAVEAT_RAISED("caveatRaised"),

    @JsonProperty("caveatExtended")
    CAVEAT_EXTENDED("caveatExtended"),

    @JsonProperty("caveatWithdrawn")
    CAVEAT_WITHDRAWN("caveatWithdrawn"),

    @JsonProperty("legalStatementAdmon")
    LEGAL_STATEMENT_ADMON("legalStatementAdmon"),

    @JsonProperty("legalStatementProbate")
    LEGAL_STATEMENT_PROBATE("legalStatementProbate"),

    @JsonProperty("legalStatementGrantOfProbate")
    LEGAL_STATEMENT_GRANT_OF_PROBATE("legalStatementGrantOfProbate"),

    @JsonProperty("legalStatementIntestacy")
    LEGAL_STATEMENT_INTESTACY("legalStatementIntestacy"),

    @JsonProperty("adColligendaBonaGrant")
    AD_COLLIGENDA_BONA_GRANT("adColligendaBonaGrant"),

    @JsonProperty("adColligendaBonaGrantDraft")
    AD_COLLIGENDA_BONA_GRANT_DRAFT("adColligendaBonaGrantDraft"),

    @JsonProperty("caveatStopped")
    CAVEAT_STOPPED("caveatStopped"),

    @JsonProperty("grantCoversheet")
    GRANT_COVERSHEET("grantCoversheet"),

    @JsonProperty("grantRaised")
    GRANT_RAISED("grantRaised"),

    @JsonProperty("digitalGrantReissueDraft")
    DIGITAL_GRANT_REISSUE_DRAFT("digitalGrantReissueDraft"),

    @JsonProperty("intestacyGrantReissueDraft")
    INTESTACY_GRANT_REISSUE_DRAFT("intestacyGrantReissueDraft"),

    @JsonProperty("admonWillGrantReissueDraft")
    ADMON_WILL_GRANT_REISSUE_DRAFT("admonWillGrantReissueDraft"),

    @JsonProperty("adColligendaBonaGrantReissueDraft")
    AD_COLLIGENDA_BONA_GRANT_REISSUE_DRAFT("adColligendaBonaGrantReissueDraft"),

    @JsonProperty("digitalGrantReissue")
    DIGITAL_GRANT_REISSUE("digitalGrantReissue"),

    @JsonProperty("intestacyGrantReissue")
    INTESTACY_GRANT_REISSUE("intestacyGrantReissue"),

    @JsonProperty("admonWillGrantReissue")
    ADMON_WILL_GRANT_REISSUE("admonWillGrantReissue"),

    @JsonProperty("adColligendaBonaGrantReissue")
    AD_COLLIGENDA_BONA_GRANT_REISSUE("adColligendaBonaGrantReissue"),

    @JsonProperty("welshDigitalGrant")
    WELSH_DIGITAL_GRANT("welshDigitalGrant"),

    @JsonProperty("welshDigitalGrantDraft")
    WELSH_DIGITAL_GRANT_DRAFT("welshDigitalGrantDraft"),

    @JsonProperty("welshIntestacyGrant")
    WELSH_INTESTACY_GRANT("welshIntestacyGrant"),

    @JsonProperty("welshIntestacyGrantDraft")
    WELSH_INTESTACY_GRANT_DRAFT("welshIntestacyGrantDraft"),

    @JsonProperty("welshAdmonWillGrant")
    WELSH_ADMON_WILL_GRANT("welshAdmonWillGrant"),

    @JsonProperty("welshAdmonWillGrantDraft")
    WELSH_ADMON_WILL_GRANT_DRAFT("welshAdmonWillGrantDraft"),

    @JsonProperty("welshAdColligendaBonaGrant")
    WELSH_AD_COLLIGENDA_BONA_GRANT("welshAdColligendaBonaGrant"),

    @JsonProperty("welshAdColligendaBonaGrantDraft")
    WELSH_AD_COLLIGENDA_BONA_GRANT_DRAFT("welshAdColligendaBonaGrantDraft"),

    @JsonProperty("welshDigitalGrantReissueDraft")
    WELSH_DIGITAL_GRANT_REISSUE_DRAFT("welshDigitalGrantReissueDraft"),

    @JsonProperty("welshIntestacyGrantReissueDraft")
    WELSH_INTESTACY_GRANT_REISSUE_DRAFT("welshIntestacyGrantReissueDraft"),

    @JsonProperty("welshAdmonWillGrantReissueDraft")
    WELSH_ADMON_WILL_GRANT_REISSUE_DRAFT("welshAdmonWillGrantReissueDraft"),

    @JsonProperty("welshAdColligendaBonaGrantReissueDraft")
    WELSH_AD_COLLIGENDA_BONA_GRANT_REISSUE_DRAFT("welshAdColligendaBonaGrantReissueDraft"),

    @JsonProperty("welshDigitalGrantReissue")
    WELSH_DIGITAL_GRANT_REISSUE("welshDigitalGrantReissue"),

    @JsonProperty("welshIntestacyGrantReissue")
    WELSH_INTESTACY_GRANT_REISSUE("welshIntestacyGrantReissue"),

    @JsonProperty("welshAdmonWillGrantReissue")
    WELSH_ADMON_WILL_GRANT_REISSUE("welshAdmonWillGrantReissue"),

    @JsonProperty("welshAdColligendaBonaGrantReissue")
    WELSH_AD_COLLIGENDA_BONA_GRANT_REISSUE("welshAdColligendaBonaGrantReissue"),

    @JsonProperty("sotInformationRequest")
    SOT_INFORMATION_REQUEST("sotInformationRequest"),

    @JsonProperty("statementOfTruth")
    STATEMENT_OF_TRUTH("statementOfTruth"),

    @JsonProperty("welshStatementOfTruth")
    WELSH_STATEMENT_OF_TRUTH("welshStatementOfTruth"),

    @JsonProperty("assembledLetter")
    ASSEMBLED_LETTER("assembledLetter"),

    @JsonProperty("blankLetter")
    BLANK_LETTER("blankLetter"),

    @JsonProperty("dormantReminder")
    DORMANT_REMINDER("dormantReminder"),

    @JsonProperty("welshDormantReminder")
    WELSH_DORMANT_REMINDER("welshDormantReminder");

    private final String templateName;

    ProbateDocumentType(String templateName) {
        this.templateName = templateName;
    }
}
