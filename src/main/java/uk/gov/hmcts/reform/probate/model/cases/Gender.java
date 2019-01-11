package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.Gender.Constants.FEMALE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.Gender.Constants.MALE_NAME;

@ApiModel(value = "LodgementType", description = "Represents lodgement type")
@RequiredArgsConstructor
public enum Gender {

    @JsonProperty(MALE_NAME) MALE(MALE_NAME),
    @JsonProperty(FEMALE_NAME) FEMALE(FEMALE_NAME);

    @Getter
    private final String name;

    public static class Constants {

        public static final String MALE_NAME = "male";
        public static final String FEMALE_NAME = "female";

        private Constants() {
        }
    }
}
