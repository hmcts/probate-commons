package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LegalStatement {

    private String intro;

    private String applicant;

    private String deceased;

    private String deceasedOtherNames;

    private String deceasedEstateValue;

    private String deceasedEstateLand;

    private List<LegalStatementExecutorsNotApplying> executorsNotApplying;

    private List<LegalStatementExecutorsApplying> executorsApplying;

}