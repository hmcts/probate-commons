package uk.gov.hmcts.reform.probate.model.forms.pa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Executors {

    private Integer executorsNumber;

    private List<Executor> list;
}
