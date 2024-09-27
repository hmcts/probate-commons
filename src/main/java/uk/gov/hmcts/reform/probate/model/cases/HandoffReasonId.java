package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.ADMON_WILL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.AD_COLLIGENDA_BONA_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.CODICIL_MIS_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.DE_BONIS_NON_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.DOUBLE_PROBATE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.EXTENDED_INTESTACY_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.FIAT_WILL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.FOREIGN_DOMICILE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.FOREIGN_WILL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.INCAPACITY_RULE35_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.LEADING_FOLLOWING_GRANTS_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.LITERARY_ESTATE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.LOST_WILL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.MINORITY_INTEREST_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.POA_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.RECTIFY_WILL_CODICIL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.RESEAL_FOREIGN_GRANT_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.SECTION116_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.SPREADSHEET_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.TRUST_CORPORATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.WILL_CODICIL_NOTATED_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.HandoffReasonId.Constants.WITNESS_INTERVIEW_NAME;


@RequiredArgsConstructor
public enum HandoffReasonId {

    @JsonProperty(AD_COLLIGENDA_BONA_NAME) AD_COLLIGENDA_BONA(AD_COLLIGENDA_BONA_NAME),
    @JsonProperty(ADMON_WILL_NAME) ADMON_WILL(ADMON_WILL_NAME),
    @JsonProperty(FOREIGN_WILL_NAME) FOREIGN_WILL(FOREIGN_WILL_NAME),
    @JsonProperty(RECTIFY_WILL_CODICIL_NAME) RECTIFY_WILL_CODICIL(RECTIFY_WILL_CODICIL_NAME),
    @JsonProperty(CODICIL_MIS_NAME) CODICIL_MIS(CODICIL_MIS_NAME),
    @JsonProperty(DE_BONIS_NON_NAME) DE_BONIS_NON(DE_BONIS_NON_NAME),
    @JsonProperty(DOUBLE_PROBATE_NAME) DOUBLE_PROBATE(DOUBLE_PROBATE_NAME),
    @JsonProperty(EXTENDED_INTESTACY_NAME) EXTENDED_INTESTACY(EXTENDED_INTESTACY_NAME),
    @JsonProperty(FIAT_WILL_NAME) FIAT_WILL(FIAT_WILL_NAME),
    @JsonProperty(FOREIGN_DOMICILE_NAME) FOREIGN_DOMICILE(FOREIGN_DOMICILE_NAME),
    @JsonProperty(INCAPACITY_RULE35_NAME) INCAPACITY_RULE35(INCAPACITY_RULE35_NAME),
    @JsonProperty(LEADING_FOLLOWING_GRANTS_NAME) LEADING_FOLLOWING_GRANTS(LEADING_FOLLOWING_GRANTS_NAME),
    @JsonProperty(LITERARY_ESTATE_NAME) LITERARY_ESTATE(LITERARY_ESTATE_NAME),
    @JsonProperty(LOST_WILL_NAME) LOST_WILL(LOST_WILL_NAME),
    @JsonProperty(MINORITY_INTEREST_NAME) MINORITY_INTEREST(MINORITY_INTEREST_NAME),
    @JsonProperty(POA_NAME) POA(POA_NAME),
    @JsonProperty(RESEAL_FOREIGN_GRANT_NAME) RESEAL_FOREIGN_GRANT(RESEAL_FOREIGN_GRANT_NAME),
    @JsonProperty(SECTION116_NAME) SECTION116(SECTION116_NAME),
    @JsonProperty(SPREADSHEET_NAME) SPREADSHEET(SPREADSHEET_NAME),
    @JsonProperty(TRUST_CORPORATION_NAME) TRUST_CORPORATION(TRUST_CORPORATION_NAME),
    @JsonProperty(WILL_CODICIL_NOTATED_NAME) WILL_CODICIL_NOTATED(WILL_CODICIL_NOTATED_NAME),
    @JsonProperty(WITNESS_INTERVIEW_NAME) WITNESS_INTERVIEW(WITNESS_INTERVIEW_NAME);

    @Getter
    private final String code;

    public static HandoffReasonId fromCode(String code) {
        return Arrays.stream(HandoffReasonId.values())
                .filter(e -> e.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Unsupported HandoffReasonId %s", code)));
    }

    public static class Constants {
        public static final String AD_COLLIGENDA_BONA_NAME = "ColligendaBona";
        public static final String ADMON_WILL_NAME = "AdmonWill";
        public static final String FOREIGN_WILL_NAME = "ForeignWill";
        public static final String RECTIFY_WILL_CODICIL_NAME = "RectifyWillCodicil";
        public static final String CODICIL_MIS_NAME = "CodicilMis";
        public static final String DE_BONIS_NON_NAME = "DeBonisNon";
        public static final String DOUBLE_PROBATE_NAME = "DoubleProbate";
        public static final String EXTENDED_INTESTACY_NAME = "ExtendedIntestacy";
        public static final String FIAT_WILL_NAME = "FiatWill";
        public static final String FOREIGN_DOMICILE_NAME = "ForeignDomicile";
        public static final String INCAPACITY_RULE35_NAME = "IncapacityRule35";
        public static final String LEADING_FOLLOWING_GRANTS_NAME = "LeadingFollowing Grants";
        public static final String LITERARY_ESTATE_NAME = "LiteraryEstate";
        public static final String LOST_WILL_NAME = "LostWill";
        public static final String MINORITY_INTEREST_NAME = "MinorityInterest";
        public static final String POA_NAME = "POA";
        public static final String RESEAL_FOREIGN_GRANT_NAME = "ResealForeignGrant";
        public static final String SECTION116_NAME = "Section116";
        public static final String SPREADSHEET_NAME = "Spreadsheet";
        public static final String TRUST_CORPORATION_NAME = "TrustCorporation";
        public static final String WILL_CODICIL_NOTATED_NAME = "WillCodicilNotated";
        public static final String WITNESS_INTERVIEW_NAME = "WitnessInterview";

        private Constants() {
        }
    }
}
