package uk.gov.hmcts.reform.probate.model.forms.caveat;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.Deceased;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CaveatDeceased extends Deceased {

    @Builder
    public CaveatDeceased(String firstName, String lastName, LocalDate dateOfBirth, LocalDate dateOfDeath,
                          Boolean addressFound, String address, String freeTextAddress, Boolean alias,
                          Map<String, AliasOtherNames> otherNames, String postCode) {
        super(firstName, lastName, dateOfBirth, dateOfDeath, addressFound, address,
                freeTextAddress, alias, otherNames, postCode);
    }
}


