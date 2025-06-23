package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifiedOCRField {

    private String fieldName;

    private String originalValue;
}