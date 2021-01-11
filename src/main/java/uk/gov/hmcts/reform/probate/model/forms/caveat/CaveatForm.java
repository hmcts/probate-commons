package uk.gov.hmcts.reform.probate.model.forms.caveat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import uk.gov.hmcts.reform.probate.model.forms.Copies;
import uk.gov.hmcts.reform.probate.model.forms.Equality;
import uk.gov.hmcts.reform.probate.model.forms.Fees;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.Language;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CaveatForm extends Form<CaveatDeceased, CaveatApplicant> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @ApiModelProperty(value = "unique application id")
    @JsonProperty(value = "applicationId")
    private String applicationId;

    @ApiModelProperty(value = "expiry date of caveat")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "expiryDate")
    private LocalDate expiryDate;

    @JsonProperty(value = "equality")
    private Equality equality;

    @Builder
    public CaveatForm(ProbateType type, CaveatDeceased deceased, CaveatApplicant applicant,
                      Registry registry, CcdCase ccdCase, List<Payment> payments, LocalDate expiryDate,
                      String applicationId, Fees fees, Copies copies, Payment payment,
                      Language language, Equality equality) {
        super(type, deceased, applicant, registry, ccdCase, payments, fees, copies, payment, language, null);
        this.expiryDate = expiryDate;
        this.applicationId = applicationId;
        this.equality = equality;
    }

}
