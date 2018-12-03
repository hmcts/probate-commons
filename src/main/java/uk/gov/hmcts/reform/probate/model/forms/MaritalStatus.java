package uk.gov.hmcts.reform.probate.model.forms;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MaritalStatus {

    MARRIED("Married or in civil partnership"),
    DIVORCED("Divorced or civil partnership dissolved"),
    WIDOWED("Widowed"),
    NEVER_MARRIED("Never married"),
    JUDICIALLY_SEPERATED("Judicially separated");

    @Getter
    private final String description;
}
