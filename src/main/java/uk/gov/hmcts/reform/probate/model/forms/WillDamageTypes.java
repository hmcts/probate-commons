package uk.gov.hmcts.reform.probate.model.forms;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "WillDamageTypes", description = "Represents will condition selections in ccd")
public class WillDamageTypes {
    private List<String> willDamageTypesList;
    private String otherDamageDescription;
}
