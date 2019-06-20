package uk.gov.hmcts.reform.probate.model.forms.pa;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.ProbateDeceased;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PaDeceased extends ProbateDeceased {

    @Builder
    public PaDeceased(String firstName, String lastName, Boolean alias, Map<String, AliasOtherNames> otherNames,
                      Boolean married, Address address, String postcode, String postcodeAddress, Boolean addressFound,
                      List<Map<String, Object>> addresses, LocalDateTime dateOfBirth, LocalDateTime dateOfDeath,
                      String domicile) {
        super(firstName, lastName, alias, otherNames, married, address, postcode, postcodeAddress, addressFound,
                addresses, dateOfBirth, dateOfDeath, domicile);
    }
}
