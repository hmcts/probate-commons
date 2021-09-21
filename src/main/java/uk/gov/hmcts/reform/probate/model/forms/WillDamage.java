package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

public class WillDamage {
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean stapleOrPunchMarks;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean rustMarks;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean paperCLipMarks;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean tornEdges;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean waterDamage;

    private String otherDamageDescription;
}
