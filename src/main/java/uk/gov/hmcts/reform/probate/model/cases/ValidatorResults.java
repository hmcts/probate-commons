package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
public class ValidatorResults {

    private final List<String> validationMessages;

}
