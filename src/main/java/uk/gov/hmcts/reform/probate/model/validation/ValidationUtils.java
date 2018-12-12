package uk.gov.hmcts.reform.probate.model.validation;

import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;

public class ValidationUtils {

    public static Long defaultNullToZero(Long value) {
        return (value == null) ? 0L : value;
    }

    public static Boolean isSpouse(Relationship relationship) {
        return relationship != null && relationship.equals(Relationship.SPOUSE);
    }

    public static String getName(Relationship relationship) {
        return relationship == null ? "" : relationship.name();
    }

    public static String getName(MaritalStatus maritalStatus) {
        return maritalStatus == null ? "" : maritalStatus.name();
    }

    private ValidationUtils() {
    }
}
