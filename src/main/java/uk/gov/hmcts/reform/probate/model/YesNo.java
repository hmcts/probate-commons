package uk.gov.hmcts.reform.probate.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum YesNo {

    YES("Yes"), NO("No");

    @Getter
    private final String description;
}
