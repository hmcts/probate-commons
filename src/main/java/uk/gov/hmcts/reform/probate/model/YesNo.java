package uk.gov.hmcts.reform.probate.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.YesNo.Constants.NO_VALUE;
import static uk.gov.hmcts.reform.probate.model.YesNo.Constants.YES_VALUE;

@RequiredArgsConstructor
public enum YesNo {

    YES(YES_VALUE), NO(NO_VALUE);

    @Getter
    private final String description;

    public static class Constants {

        public static final String YES_VALUE = "Yes";

        public static final String NO_VALUE = "No";

        public static final String ALLOWABLE_VALUES = YES_VALUE + "," + NO_VALUE;

        private Constants() {
        }
    }
}
