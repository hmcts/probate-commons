package uk.gov.hmcts.reform.probate.model.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "WillDamageTypes", description = "Represents will condition selections in ccd")
public class WillDamageTypes {
//    @JsonDeserialize(using = OptionYesNoDeserializer.class)
//    @JsonSerialize(using = OptionYesNoSerializer.class)
//    private Boolean stapleOrPunchHoles;
//
//    @JsonDeserialize(using = OptionYesNoDeserializer.class)
//    @JsonSerialize(using = OptionYesNoSerializer.class)
//    private Boolean rustMarks;
//
//    @JsonDeserialize(using = OptionYesNoDeserializer.class)
//    @JsonSerialize(using = OptionYesNoSerializer.class)
//    private Boolean paperClipMarks;
//
//    @JsonDeserialize(using = OptionYesNoDeserializer.class)
//    @JsonSerialize(using = OptionYesNoSerializer.class)
//    private Boolean tornEdges;
//
//    @JsonDeserialize(using = OptionYesNoDeserializer.class)
//    @JsonSerialize(using = OptionYesNoSerializer.class)
//    private Boolean waterDamage;

    private List<String> willDamageTypesList;

    private String otherDamageDescription;
}
