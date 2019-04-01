package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.BIRMINGHAM_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.BRIGHTON_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.CARDIFF_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.CTSC_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.LEEDS_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.LIVERPOOL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.LONDON_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.MANCHESTER_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.NEWCASTLE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.OXFORD_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.Constants.WINCHESTER_NAME;

@RequiredArgsConstructor
public enum RegistryLocation {


    @JsonProperty(OXFORD_NAME) OXFORD(OXFORD_NAME),
    @JsonProperty(BIRMINGHAM_NAME) BIRMINGHAM(BIRMINGHAM_NAME),
    @JsonProperty(MANCHESTER_NAME) MANCHESTER(MANCHESTER_NAME),
    @JsonProperty(LEEDS_NAME) LEEDS(LEEDS_NAME),
    @JsonProperty(LIVERPOOL_NAME) LIVERPOOL(LIVERPOOL_NAME),
    @JsonProperty(BRIGHTON_NAME) BRIGHTON(BRIGHTON_NAME),
    @JsonProperty(LONDON_NAME) LONDON(LONDON_NAME),
    @JsonProperty(CARDIFF_NAME) CARDIFF(CARDIFF_NAME),
    @JsonProperty(NEWCASTLE_NAME) NEWCASTLE(NEWCASTLE_NAME),
    @JsonProperty(WINCHESTER_NAME) WINCHESTER(WINCHESTER_NAME),
    @JsonProperty(CTSC_NAME) CTSC(CTSC_NAME);

    @Getter
    private final String name;

    public static RegistryLocation findRegistryLocationByName(String name) {
        return Arrays.stream(RegistryLocation.values())
                .filter(e -> e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Unsupported RegistryLocation %s", name)));
    }

    public static class Constants {

        public static final String OXFORD_NAME = "Oxford";

        public static final String BIRMINGHAM_NAME = "Birmingham";

        public static final String MANCHESTER_NAME = "Manchester";

        public static final String LEEDS_NAME = "Leeds";

        public static final String LIVERPOOL_NAME = "Liverpool";

        public static final String BRIGHTON_NAME = "Brighton";

        public static final String LONDON_NAME = "London";

        public static final String CARDIFF_NAME = "Cardiff";

        public static final String NEWCASTLE_NAME = "Newcastle";

        public static final String WINCHESTER_NAME = "Winchester";

        public static final String CTSC_NAME = "ctsc";

        private Constants() {
        }
    }
}
