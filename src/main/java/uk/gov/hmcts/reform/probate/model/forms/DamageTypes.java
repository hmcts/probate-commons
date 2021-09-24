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
@ApiModel(value = "DamageTypes", description = "Represents will or codicil condition selections in ccd")
public class DamageTypes {
    private List<String> damageTypesList;
    private String otherDamageDescription;
}
