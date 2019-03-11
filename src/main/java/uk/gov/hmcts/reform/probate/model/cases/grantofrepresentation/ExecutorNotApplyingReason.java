package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum ExecutorNotApplyingReason {

    @JsonProperty("DiedBefore")
    DIED_BEFORE("optionDiedBefore"),
    @JsonProperty("DiedAfter")
    DIED_AFTER("optionDiedAfter"),
    @JsonProperty("PowerReserved")
    POWER_RESERVED("optionPowerReserved"),
    @JsonProperty("Renunciation")
    RENUNCIATION("optionRenunciated");

    @Getter
    private final String optionValue;

    public static ExecutorNotApplyingReason getExecutorNotApplyingReasonByValue(String value) {
        return Arrays.stream(ExecutorNotApplyingReason.values())
                .filter(executorNotApplyingReason -> executorNotApplyingReason.getOptionValue().equals(value))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
