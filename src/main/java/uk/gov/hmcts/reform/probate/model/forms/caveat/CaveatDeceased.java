package uk.gov.hmcts.reform.probate.model.forms.caveat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.Deceased;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CaveatDeceased extends Deceased {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private String firstName;

    private String lastName;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "dob_date")
    private LocalDate dateOfBirth;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "dod_date")
    private LocalDate dateOfDeath;

    private Address address;

    private Map<String, AliasOtherNames> otherNames;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean alias;

}


