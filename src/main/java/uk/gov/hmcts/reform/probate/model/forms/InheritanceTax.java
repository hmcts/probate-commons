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
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

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

    private IhtFormType form;

    private String identifier;

    private BigDecimal grossValue;

    private BigDecimal netValue;

    private String grossValueField;

    private String netValueField;

    @JsonProperty("grossValueFieldIHT205")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal grossIht205;

    @JsonProperty("netValueFieldIHT205")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal netIht205;

    @JsonProperty("grossValueFieldIHT207")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal grossIht207;

    @JsonProperty("netValueFieldIHT207")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal netIht207;

    @JsonProperty("grossValueFieldIHT400421")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal grossIht400421;

    @JsonProperty("netValueFieldIHT400421")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal netIht400421;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean assetsOutside;

    private BigDecimal assetsOutsideNetValue;
}
