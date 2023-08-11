package uk.gov.hmcts.reform.probate.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static uk.gov.hmcts.reform.probate.model.client.ErrorType.Constants.API_CLIENT_ERROR;
import static uk.gov.hmcts.reform.probate.model.client.ErrorType.Constants.VALIDATION_ERROR;

@Schema(name = "ErrorType", description = "Represents probate error response type")
@RequiredArgsConstructor
public enum ErrorType {

    @JsonProperty(VALIDATION_ERROR) VALIDATION(VALIDATION_ERROR),
    @JsonProperty(API_CLIENT_ERROR) API_CLIENT(API_CLIENT_ERROR);

    @Getter
    private final String name;

    public static class Constants {

        public static final String VALIDATION_ERROR = "VALIDATION";
        public static final String API_CLIENT_ERROR = "API_CLIENT";

        private Constants() {
        }
    }
}
