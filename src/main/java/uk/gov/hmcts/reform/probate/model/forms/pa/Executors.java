package uk.gov.hmcts.reform.probate.model.forms.pa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.OptionYesNoSerializer;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Executors {

    private Integer executorsNumber;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean executorsNamed;

    @Deprecated
    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean allalive;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean anyExecutorsDied;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean otherExecutorsApplying;

    @JsonDeserialize(using = OptionYesNoDeserializer.class)
    @JsonSerialize(using = OptionYesNoSerializer.class)
    private Boolean alias;

    private Boolean invitesSent;

    private List<Executor> list;
}
