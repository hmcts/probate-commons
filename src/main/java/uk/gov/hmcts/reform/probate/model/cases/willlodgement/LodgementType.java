package uk.gov.hmcts.reform.probate.model.cases.willlodgement;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.willlodgement.LodgementType.Constants.RENUNCIATION_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.willlodgement.LodgementType.Constants.SAFE_CUSTODY_NAME;

@ApiModel(value = "LodgementType", description = "Represents lodgement type")
@RequiredArgsConstructor
public enum LodgementType {

    @JsonProperty(SAFE_CUSTODY_NAME) SAFE_CUSTODY(SAFE_CUSTODY_NAME),
    @JsonProperty(RENUNCIATION_NAME) RENUNCIATION(RENUNCIATION_NAME);

    @Getter
    private final String name;

    public static class Constants {

        public static final String SAFE_CUSTODY_NAME = "safeCustody";
        public static final String RENUNCIATION_NAME = "renunciation";

        private Constants() {
        }
    }
}
