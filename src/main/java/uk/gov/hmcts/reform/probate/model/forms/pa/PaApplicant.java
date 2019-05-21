package uk.gov.hmcts.reform.probate.model.forms.pa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.Applicant;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneSerializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaApplicant extends Applicant {

    private String firstName;

    private String lastName;

    private String alias;

    private String aliasReason;

    private Address address;

    private String postcode;

    private String postcodeAddress;

    private String freeTextAddress;

    @JsonDeserialize(using = BooleanAndNoneDeserializer.class)
    @JsonSerialize(using = BooleanAndNoneSerializer.class)
    private Boolean addressFound;

    private List<Map<String, Object>> addresses;

    private String phoneNumber;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean nameAsOnTheWill;

    private String otherReason;
}
