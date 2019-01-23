package uk.gov.hmcts.reform.probate.model.forms.caveat;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Applicant;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CaveatApplicant extends Applicant {

    @Builder
    public CaveatApplicant(String email, String firstName, String lastName, String postCode,
                              String phoneNumber, String address, Boolean addressFound, String freeTextAddress) {
        super(email, firstName, lastName, postCode, phoneNumber, address, addressFound, freeTextAddress);
    }
}
