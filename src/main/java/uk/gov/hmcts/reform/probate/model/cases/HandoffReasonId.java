package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HandoffReasonId {
    AD_COLLIGENDA_BONA("ColligendaBona"),
    ADMON_WILL("AdmonWill"),
    FOREIGN_WILL("ForeignWill"),
    RECTIFY_WILL_CODICIL("RectifyWillCodicil"),
    CODICIL_MIS("CodicilMis"),
    DE_BONIS_NON("DeBonisNon"),
    DOUBLE_PROBATE("DoubleProbate"),
    EXTENDED_INTESTACY("ExtendedIntestacy"),
    FIAT_WILL("FiatWill"),
    FOREIGN_DOMICILE("ForeignDomicile"),
    INCAPACITY_RULE35("IncapacityRule35"),
    LEADING_FOLLOWING_GRANTS("LeadingFollowing"),
    LITERARY_ESTATE("LiteraryEstate"),
    LOST_WILL("LostWill"),
    MINORITY_INTEREST("MinorityInterest"),
    POA("POA"),
    RESEAL_FOREIGN_GRANT("ResealForeignGrant"),
    SECTION116("Section116"),
    SPREADSHEET("Spreadsheet"),
    TRUST_CORPORATION("TrustCorporation"),
    WILL_CODICIL_NOTATED("WillCodicilNotated"),
    WITNESS_INTERVIEW("WitnessInterview");
    @Getter
    private final String code;

}
