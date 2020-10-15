package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.DeathCertificate.Constants.DEATH_CERTIFICATE_VALUE;
import static uk.gov.hmcts.reform.probate.model.cases.DeathCertificate.Constants.INTERIM_DEATH_CERTIFICATE_VALUE;

@RequiredArgsConstructor
public enum DeathCertificate {

    @JsonProperty(DEATH_CERTIFICATE_VALUE) DEATH_CERTIFICATE("optionDeathCertificate"),
    @JsonProperty(INTERIM_DEATH_CERTIFICATE_VALUE) INTERIM_DEATH_CERTIFICATE("optionInterimDeathCertificate");

    @Getter
    private final String description;


    public static DeathCertificate fromString(String text) {
        for (DeathCertificate dc : DeathCertificate.values()) {
            if (text != null && dc.description.equalsIgnoreCase(text)) {
                return dc;
            }
        }
        return null;
    }

    public static class Constants {
        public static final String DEATH_CERTIFICATE_VALUE = "deathCertificate";
        public static final String INTERIM_DEATH_CERTIFICATE_VALUE = "interimDeathCertificate";

        private Constants() {
        }
    }

}
