package uk.gov.hmcts.reform.probate.model.cases;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ApiModel(value = "CollectionMember", description = "Represents collection member")
public class CollectionMember<T> {

    private String id;

    private T value;
}
