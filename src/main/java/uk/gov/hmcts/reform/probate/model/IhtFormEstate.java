package uk.gov.hmcts.reform.probate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.IhtFormType.Constants.IHT205_VALUE;
import static uk.gov.hmcts.reform.probate.model.IhtFormType.Constants.IHT207_VALUE;
import static uk.gov.hmcts.reform.probate.model.IhtFormType.Constants.IHT400421_VALUE;
import static uk.gov.hmcts.reform.probate.model.IhtFormType.Constants.IHT400_VALUE;


@RequiredArgsConstructor
public enum IhtFormEstate {

    @JsonProperty(IHT205_VALUE) optionIHT205(IHT205_VALUE),
    @JsonProperty(IHT207_VALUE) optionIHT207(IHT207_VALUE),
    @JsonProperty(IHT400421_VALUE) optionIHT400421(IHT400421_VALUE),
    @JsonProperty(IHT400_VALUE) optionIHT400(IHT400_VALUE);

    @Getter
    private final String description;

    public static IhtFormEstate fromString(String text) {
        for (IhtFormEstate ihtFormType : IhtFormEstate.values()) {
            if (text != null && ihtFormType.description.equalsIgnoreCase(text)) {
                return ihtFormType;
            }
        }
        return null;
    }
}
