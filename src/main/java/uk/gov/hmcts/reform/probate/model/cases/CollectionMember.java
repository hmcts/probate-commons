package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@ApiModel(value = "CollectionMember", description = "Represents collection member")
public class CollectionMember<T> {

    private final String id;

    private final T value;
}
