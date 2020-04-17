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
    CAVEAT_RAISED("caveatRaised");

    private final String templateName;

    ProbateDocumentType(String templateName) {
        this.templateName = templateName;
    }
}
