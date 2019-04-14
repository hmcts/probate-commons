package uk.gov.hmcts.reform.probate.model.client;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ApiClientErrorResponse extends ErrorResponse {

    ApiClientError error;

    @Builder
    public ApiClientErrorResponse(ApiClientError apiClientError) {
        super(ErrorType.API_CLIENT);
        this.error = apiClientError;
    }
}
