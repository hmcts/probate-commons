package uk.gov.hmcts.reform.probate.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.OptionYesNo.Constants.NO_VALUE;
import static uk.gov.hmcts.reform.probate.model.OptionYesNo.Constants.YES_VALUE;

@RequiredArgsConstructor
public enum OptionYesNo {

    YES(YES_VALUE), NO(NO_VALUE);

    @Getter
    private final String description;

    public static class Constants {

        public static final String YES_VALUE = "optionYes";

        public static final String NO_VALUE = "optionNo";

        public static final String ALLOWABLE_VALUES = YES_VALUE + "," + NO_VALUE;

        private Constants() {
        }
    }
}
