package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.HALF_BLOOD_NIECE_OR_NEPHEW_DESC;
import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.HALF_BLOOD_SIBLING_DESC;
import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.WHOLE_BLOOD_NIECE_OR_NEPHEW_DESC;
import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.WHOLE_BLOOD_SIBLING_DESC;
import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.PARTNER_DESC;
import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.CHILD_DESC;
import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.GRANDCHILD_DESC;
import static uk.gov.hmcts.reform.probate.model.CoApplicantRelationship.Constants.OTHER_DESC;


@RequiredArgsConstructor
public enum CoApplicantRelationship {

    @JsonProperty(PARTNER_DESC) PARTNER("optionSpousePartner"),
    @JsonProperty(CHILD_DESC) CHILD("optionChild"),
    @JsonProperty(GRANDCHILD_DESC) GRANDCHILD("optionGrandchild"),
    @JsonProperty(WHOLE_BLOOD_SIBLING_DESC) WHOLE_BLOOD_SIBLING("optionWholeBloodSibling"),
    @JsonProperty(HALF_BLOOD_SIBLING_DESC) HALF_BLOOD_SIBLING("optionHalfBloodSibling"),
    @JsonProperty(WHOLE_BLOOD_NIECE_OR_NEPHEW_DESC) WHOLE_BLOOD_NIECE_OR_NEPHEW("optionWholeBloodNieceOrNephew"),
    @JsonProperty(HALF_BLOOD_NIECE_OR_NEPHEW_DESC) HALF_BLOOD_NIECE_OR_NEPHEW("optionHalfBloodNieceOrNephew"),
    @JsonProperty(OTHER_DESC) OTHER("optionOther");

    @Getter
    private final String description;

    public static CoApplicantRelationship fromString(String text) {
        for (CoApplicantRelationship ms : CoApplicantRelationship.values()) {
            if (text != null && ms.description.equalsIgnoreCase(text)) {
                return ms;
            }
        }
        return null;
    }

    public static class Constants {

        public static final String PARTNER_DESC = "partner";
        public static final String CHILD_DESC = "child";
        public static final String GRANDCHILD_DESC = "grandchild";
        public static final String WHOLE_BLOOD_SIBLING_DESC = "wholeBloodSibling";
        public static final String HALF_BLOOD_SIBLING_DESC = "halfBloodSibling";
        public static final String WHOLE_BLOOD_NIECE_OR_NEPHEW_DESC = "wholeBloodNieceOrNephew";
        public static final String HALF_BLOOD_NIECE_OR_NEPHEW_DESC = "halfBloodNieceOrNephew";
        public static final String OTHER_DESC = "other";

        private Constants() {
        }
    }
}
