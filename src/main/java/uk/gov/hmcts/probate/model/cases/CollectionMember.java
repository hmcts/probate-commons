package uk.gov.hmcts.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ApiModel(value = "CollectionMember", description = "Represents collection member")
public class CollectionMember<T> {

    private final String id;

    private final T value;
}
