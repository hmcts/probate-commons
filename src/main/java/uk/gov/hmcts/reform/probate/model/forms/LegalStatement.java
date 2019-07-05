package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalStatement {

    private String applicant;

    private String deceased;

    private String deceasedOtherNames;

    private List<LegalStatementExecutorApplying> executorsApplying;

    private String deceasedEstateValue;

    private String deceasedEstateLand;

    private List<String> executorsNotApplying;

    private String intro;
}
