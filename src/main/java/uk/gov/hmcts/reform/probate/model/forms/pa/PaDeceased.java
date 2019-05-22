package uk.gov.hmcts.reform.probate.model.forms.pa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.Deceased;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneSerializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"grossValueOnline", "netValueOnline", "dod_day", "dod_month", "dod_year",
    "dob_day", "dob_month", "dob_year", "dob_formattedDate", "dod_formattedDate"}, allowGetters = true)
public class PaDeceased extends Deceased {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private String firstName;

    private String lastName;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean alias;

    private Map<String, AliasOtherNames> otherNames;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean married;

    private Address address;

    private String postcode;

    private String postcodeAddress;

    @JsonDeserialize(using = BooleanAndNoneDeserializer.class)
    @JsonSerialize(using = BooleanAndNoneSerializer.class)
    private Boolean addressFound;

    private List<Map<String, Object>> addresses;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "dob_date")
    private LocalDateTime dateOfBirth;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "dod_date")
    private LocalDateTime dateOfDeath;

    private String domicile;

    @JsonProperty(value = "dod_day")
    public Integer getDodDay() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.getDayOfMonth();
    }

    @JsonProperty(value = "dod_month")
    public Integer getDodMonth() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.getMonthValue();
    }

    @JsonProperty(value = "dod_year")
    public Integer getDodYear() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.getYear();
    }

    @JsonProperty(value = "dob_day")
    public Integer getDobDay() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.getDayOfMonth();
    }

    @JsonProperty(value = "dob_month")
    public Integer getDobMonth() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.getMonthValue();
    }

    @JsonProperty(value = "dob_year")
    public Integer getDobYear() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.getYear();
    }

    @JsonProperty(value = "dob_formattedDate")
    public String getDobFormattedDate() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    @JsonProperty(value = "dod_formattedDate")
    public String getDodFormattedDate() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }
}
