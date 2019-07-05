package uk.gov.hmcts.reform.probate.model.forms.pa;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.ProbateApplicant;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaApplicant extends ProbateApplicant {

    @Builder
    public PaApplicant(String firstName, String lastName, String alias, String aliasReason, Address address,
                       String postcode, String postcodeAddress, Boolean addressFound,
                       List<Map<String, Object>> addresses, String phoneNumber, Boolean nameAsOnTheWill,
                       String otherReason) {
        super(firstName, lastName, alias, aliasReason, address, postcode, postcodeAddress, addressFound, addresses,
                phoneNumber, nameAsOnTheWill, otherReason);
    }
}
