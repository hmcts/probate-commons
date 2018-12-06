package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonProperty;

import static uk.gov.hmcts.reform.probate.model.forms.IhtMethod.Constants.BY_POST_DESC;
import static uk.gov.hmcts.reform.probate.model.forms.IhtMethod.Constants.ONLINE_DESC;

public enum IhtMethod {

    @JsonProperty(ONLINE_DESC) ONLINE, @JsonProperty(BY_POST_DESC) BY_POST;

    public static class Constants {

        public static final String ONLINE_DESC = "Through the HMRC online service";
        public static final String BY_POST_DESC = "By post";

        private Constants() {
        }
    }
}
