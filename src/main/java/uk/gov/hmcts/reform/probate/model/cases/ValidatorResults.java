package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class ValidatorResults {

    private final List<String> validationMessages = new ArrayList<>();

}
