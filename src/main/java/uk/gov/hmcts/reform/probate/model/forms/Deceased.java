package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.time.LocalDate;


@Data
//@Builder
public abstract class Deceased {

    private String firstName;

    private String lastName;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty(value = "dob_date")
    private LocalDate dateOfBirth;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty(value = "dod_date")
    private LocalDate dateOfDeath;

    private Boolean addressFound;

    private String address;

    private String freeTextAddress;

}
