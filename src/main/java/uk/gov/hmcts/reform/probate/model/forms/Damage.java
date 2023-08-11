package uk.gov.hmcts.reform.probate.model.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Damage", description = "Represents will or codicil condition selections in ccd")
public class Damage {
    private List<String> damageTypesList;
    private String otherDamageDescription;
}
