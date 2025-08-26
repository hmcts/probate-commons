package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import lombok.Builder;
import lombok.Data;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DeathRecord {
    private final Integer systemNumber;
    private final String name;
    private final LocalDate dateOfBirth;
    private final String sex;
    private final String address;
    private final LocalDate dateOfDeath;
    private final List<CollectionMember<Alias>> aliases;
    private final String valid;
}
