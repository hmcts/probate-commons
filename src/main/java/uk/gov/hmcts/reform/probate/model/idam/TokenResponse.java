package uk.gov.hmcts.reform.probate.model.idam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {

    public final String accessToken;
    public final String expiresIn;
    public final String idToken;
    public final String refreshToken;
    public final String scope;
    public final String tokenType;
    private final String expiresAtTime;

    public TokenResponse(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("expires_in") String expiresIn,
            @JsonProperty("id_token") String idToken,
            @JsonProperty("refresh_token") String refreshToken,
            @JsonProperty("scope") String scope,
            @JsonProperty("token_type") String tokenType
    ) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.tokenType = tokenType;
        this.expiresAtTime = Instant.now().plusSeconds(Long.parseLong(expiresIn)).toString();
    }

    public String getExpiresAtTime() {
        return expiresAtTime;
    }
}

