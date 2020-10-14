package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.BooleanAndNoneSerializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {"grossValueOnline", "netValueOnline", "dod-day", "dod-month", "dod-year",
    "dob-day", "dob-month", "dob-year", "dob-formattedDate", "dod-formattedDate"}, allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ProbateDeceased extends Deceased {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private String firstName;

    private String lastName;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean alias;

    private Map<String, AliasOtherNames> otherNames;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
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
    @JsonProperty(value = "dob-date")
    private LocalDateTime dateOfBirth;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty(value = "dod-date")
    private LocalDateTime dateOfDeath;

    private String domicile;

    private Boolean diedEngOrWales;

    private String deathCertificate;

    @JsonProperty(value = "dod-day")
    public Integer getDodDay() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.getDayOfMonth();
    }

    @JsonProperty(value = "dod-month")
    public Integer getDodMonth() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.getMonthValue();
    }

    @JsonProperty(value = "dod-year")
    public Integer getDodYear() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.getYear();
    }

    @JsonProperty(value = "dob-day")
    public Integer getDobDay() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.getDayOfMonth();
    }

    @JsonProperty(value = "dob-month")
    public Integer getDobMonth() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.getMonthValue();
    }

    @JsonProperty(value = "dob-year")
    public Integer getDobYear() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.getYear();
    }

    @JsonProperty(value = "dob-formattedDate")
    public String getDobFormattedDate() {
        if (dateOfBirth == null) {
            return null;
        }
        return dateOfBirth.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    @JsonProperty(value = "dod-formattedDate")
    public String getDodFormattedDate() {
        if (dateOfDeath == null) {
            return null;
        }
        return dateOfDeath.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

}
