package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WillDamage {
//    @JsonDeserialize(using = YesNoDeserializer.class)
//    @JsonSerialize(using = YesNoSerializer.class)
//    private Boolean stapleOrPunchHoles;
//
//    @JsonDeserialize(using = YesNoDeserializer.class)
//    @JsonSerialize(using = YesNoSerializer.class)
//    private Boolean rustMarks;
//
//    @JsonDeserialize(using = YesNoDeserializer.class)
//    @JsonSerialize(using = YesNoSerializer.class)
//    private Boolean paperClipMarks;
//
//    @JsonDeserialize(using = YesNoDeserializer.class)
//    @JsonSerialize(using = YesNoSerializer.class)
//    private Boolean tornEdges;
//
//    @JsonDeserialize(using = YesNoDeserializer.class)
//    @JsonSerialize(using = YesNoSerializer.class)
//    private Boolean waterDamage;

    private List<String> willDamageTypesList;

    private String otherDamageDescription;
}
