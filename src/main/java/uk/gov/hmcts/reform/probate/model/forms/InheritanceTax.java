package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.BigDecimalDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"grossValueOnline", "netValueOnline"}, allowGetters = true)
public class InheritanceTax {

    private IhtMethod method;

    private String ihtFormId;

    private String form;

    private String identifier;

    private BigDecimal grossValue;

    private BigDecimal netValue;

    private String grossValueField;

    private String netValueField;

    @JsonProperty("grossValueFieldIHT205")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal grossIht205;

    @JsonProperty("netValueFieldIHT205")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal netIht205;

    @JsonProperty("grossValueFieldIHT207")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal grossIht207;

    @JsonProperty("netValueFieldIHT207")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal netIht207;

    @JsonProperty("grossValueFieldIHT400421")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal grossIht400421;

    @JsonProperty("netValueFieldIHT400421")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal netIht400421;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean completed;

    public String getGrossValueOnline() {
        if (grossValue == null) {
            return null;
        }
        return grossValue.toPlainString();
    }

    public String getNetValueOnline() {
        if (netValue == null) {
            return null;
        }
        return netValue.toPlainString();
    }

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean assetsOutside;

    @JsonProperty("netValueAssetsOutside")
    private BigDecimal assetsOutsideNetValue;

    private String netValueAssetsOutsideField;


}
