package uk.gov.hmcts.reform.probate.model.forms.caveat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;
import uk.gov.hmcts.reform.probate.model.forms.caveat.CaveatApplicant;
import uk.gov.hmcts.reform.probate.model.forms.caveat.CaveatDeceased;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonTypeName(ProbateType.Constants.CAVEAT_NAME)
public class CaveatForm extends Form {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private CaveatDeceased deceased;

    private CaveatApplicant applicant;

    private Registry registry;

    private CcdCase ccdCase;

    private List<Payment> payments;

    @ApiModelProperty(value = "expiry date of caveat")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "expiryDate")
    private LocalDate expiryDate;

    @Builder
    public CaveatForm(CaveatDeceased deceased, CaveatApplicant applicant,
                      Registry registry, CcdCase ccdCase, List<Payment> payments, LocalDate expiryDate) {
        this.deceased = deceased;
        this.applicant = applicant;
        this.registry = registry;
        this.ccdCase = ccdCase;
        this.expiryDate = expiryDate;
    }

}
