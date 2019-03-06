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
public class PaLegalStatement {

    private String applicant;

    private String deceased;

    private String deceasedOtherNames;

    private List<PaLegalStatementExecutorApplying> executorsApplying;

    private String deceasedEstateValue;

    private String deceasedEstateLand;

    private List<String> executorsNotApplying;

    private String intro;
}