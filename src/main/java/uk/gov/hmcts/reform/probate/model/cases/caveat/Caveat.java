package uk.gov.hmcts.reform.probate.model.cases.caveat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.cases.*;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;
import uk.gov.hmcts.reform.probate.model.validation.AssertExpression;
import uk.gov.hmcts.reform.probate.model.validation.groups.SubmissionGroup;

import java.time.LocalDate;
import java.util.List;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@GroupSequence({Caveat.class, SubmissionGroup.class})
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Caveat", parent = CaseData.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AssertExpression(value = "deceasedDateOfBirth.isBefore(deceasedDateOfDeath)", groups = SubmissionGroup.class)
@AssertExpression(value = "!(#isTrue(deceasedAnyOtherNames) && #isEmpty(deceasedFullAliasNameList))",
        groups = SubmissionGroup.class)
public class Caveat extends CaseData {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    RegistryLocation registryLocation = RegistryLocation.OXFORD;

    @NotNull
    @Size(min = 2, groups = SubmissionGroup.class)
    private String deceasedForenames;

    @NotNull
    @Size(min = 2, groups = SubmissionGroup.class)
    private String deceasedSurname;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deceasedDateOfDeath;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deceasedDateOfBirth;

    @NotNull
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyOtherNames;

    private List<CollectionMember<AliasOtherNames>> deceasedFullAliasNameList;

    private Address deceasedAddress;

    @NotNull
    @Size(min = 2, groups = SubmissionGroup.class)
    private String caveatorForenames;

    @NotNull
    @Size(min = 2, groups = SubmissionGroup.class)
    private String caveatorSurname;

    @NotNull
    private String caveatorEmailAddress;

    @NotNull
    private Address caveatorAddress;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expiryDate;

}
