package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Will {

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean codicils;

    private Long codicilsNumber;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean willHasVisibleDamage;

    private Damage willDamage;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean willDamageReasonKnown;

    private String willDamageReasonDescription;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean willDamageCulpritKnown;

    private CombinedName willDamageCulpritName;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean willDamageDateKnown;

    private String willDamageDate;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean codicilsHasVisibleDamage;

    private Damage codicilsDamage;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean codicilsDamageReasonKnown;

    private String codicilsDamageReasonDescription;
}
