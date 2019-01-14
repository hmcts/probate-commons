package uk.gov.hmcts.reform.probate.model.cases;

import lombok.Data;

import java.util.function.Function;

@Data
public class SearchField<T extends CaseData> {

    private final String name;

    private final Function<T, String> function;
}
