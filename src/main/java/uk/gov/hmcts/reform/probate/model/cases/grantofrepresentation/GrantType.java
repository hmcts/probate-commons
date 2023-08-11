package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantType.Constants.ADMON_WILL_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantType.Constants.EDGE_CASE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantType.Constants.GRANT_OF_PROBATE_NAME;
import static uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantType.Constants.INTESTACY_NAME;

@Schema(name = "GrantType", description = "Represents grant type")
@RequiredArgsConstructor
public enum GrantType {

    @JsonProperty(GRANT_OF_PROBATE_NAME) GRANT_OF_PROBATE(GRANT_OF_PROBATE_NAME),
    @JsonProperty(INTESTACY_NAME) INTESTACY(INTESTACY_NAME),
    @JsonProperty(ADMON_WILL_NAME) ADMON_WILL(ADMON_WILL_NAME),
    @JsonProperty(EDGE_CASE_NAME) EDGE_CASE(EDGE_CASE_NAME);

    @Getter
    private final String name;


    public static class Constants {

        public static final String GRANT_OF_PROBATE_NAME = "gop";
        public static final String INTESTACY_NAME = "intestacy";
        public static final String ADMON_WILL_NAME = "admonWill";
        public static final String EDGE_CASE_NAME = "edgeCase";

        private Constants() {
        }
    }
}
