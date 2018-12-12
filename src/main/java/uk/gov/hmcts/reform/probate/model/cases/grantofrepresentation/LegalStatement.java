package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalStatement {

    private final String intro;

    private final String applicant;

    private final String deceased;

    private final String deceasedOtherNames;

    private final String deceasedEstateValue;

    private final String deceasedEstateLand;

    private final List<LegalStatementExecutorsNotApplying> executorsNotApplying;

    private final List<LegalStatementExecutorsApplying> executorsApplying;

}