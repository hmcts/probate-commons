package uk.gov.hmcts.reform.probate.model.cases.willlodgement;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.Address;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "WillExecutor")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WillExecutor {

    private String willExecutorTitle;

    private String willExecutorForenames;

    private String willExecutorSurname;

    private Address willExecutorAddress;

    private String willExecutorEmail;

}
