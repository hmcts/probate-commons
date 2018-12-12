package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;
import uk.gov.hmcts.reform.probate.model.validation.AssertExpression;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "GrantOfRepresentation", parent = CaseData.class)
@Data
@EqualsAndHashCode(callSuper = false)
@AssertExpression("!(#isTrue(deceasedOtherNames) && #isEmpty(deceasedAliasNameList))")
@AssertExpression("!(#L(ihtNetValue) <= 250000 && !#isTrue(assetsOverseas))")
@AssertExpression("!(#isTrue(assetsOverseas) && #L(assetsOverseasNetValue) == 0)")
@AssertExpression("deceasedDateOfBirth.isBefore(deceasedDateOfDeath)")
@AssertExpression("#L(ihtNetValue) <= #L(ihtGrossValue)")
@AssertExpression("!((#L(ihtNetValue) > 250000) && !#isSpouse(primaryApplicantRelationshipToDeceased) "
        + "&& (deceasedSpouseNotApplyingReason == null))")
@AssertExpression("{'ADOPTED_CHILD', 'CHILD'}.contains(#R(primaryApplicantRelationshipToDeceased)) ? "
        + " deceasedOtherChildren != null "
        + ": true")
@AssertExpression("#isTrue(deceasedDivorcedInEnglandOrWales) ? "
        + "{'DIVORCED', 'JUDICIALLY_SEPARATED'}.contains(#MS(deceasedMaritalStatus)) "
        + ": !{'DIVORCED', 'JUDICIALLY_SEPARATED'}.contains(#MS(deceasedMaritalStatus))")
@AssertExpression("#isTrue(deceasedOtherChildren) ? deceasedAllDeceasedChildrenOverEighteen != null : true")
@AssertExpression("#R(primaryApplicantRelationshipToDeceased) == 'ADOPTED_CHILD' ? "
        + "primaryApplicantAdoptionInEnglandOrWales != null : true")
@AssertExpression("#R(primaryApplicantRelationshipToDeceased) == 'SPOUSE' ? deceasedAnyChildren != null : true")
@AssertExpression("#isTrue(deceasedOtherChildren) && #isTrue(deceasedAllDeceasedChildrenOverEighteen) ? "
        + "deceasedAnyDeceasedChildrenDieBeforeDeceased != null : true")
@AssertExpression("#isTrue(deceasedOtherChildren) && #isTrue(deceasedAllDeceasedChildrenOverEighteen) "
        + "&& #isTrue(deceasedAnyDeceasedChildrenDieBeforeDeceased) ? "
        + "deceasedAnyDeceasedGrandchildrenUnderEighteen != null : true")
public class GrantOfRepresentation extends CaseData {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate applicationSubmittedDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean softStop;

    private String registryLocation;

    @JsonProperty(value = "outsideUKGrantCopies")
    private Long outsideUkGrantCopies;

    private Long extraCopiesOfGrant;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedDomicileInEngWales;

    private Address deceasedAddress;

    private String deceasedFreeTextAddress;

    @JsonSerialize(using = ToStringSerializer.class)
    private Boolean deceasedAddressFound;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedMarriedAfterWillOrCodicilDate;

    @NotNull
    @Size(min = 2)
    private String deceasedForenames;

    @NotNull
    @Size(min = 2)
    private String deceasedSurname;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate deceasedDateOfDeath;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate deceasedDateOfBirth;

    @NotNull
    private MaritalStatus deceasedMaritalStatus;

    @NotNull
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedOtherNames;

    private List<CollectionMember<AliasName>> deceasedAliasNameList;

    private SpouseNotApplyingReason deceasedSpouseNotApplyingReason;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyChildren;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedOtherChildren;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedDivorcedInEnglandOrWales;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyDeceasedChildrenDieBeforeDeceased;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAllDeceasedChildrenOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyDeceasedGrandchildrenUnderEighteen;

    private IhtFormType ihtForm;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean ihtFormCompletedOnline;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtNetValue;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtGrossValue;

    private String ihtReferenceNumber;

    private String primaryApplicantEmailAddress;

    private Address primaryApplicantAddress;

    private String primaryApplicantFreeTextAddress;

    @JsonSerialize(using = ToStringSerializer.class)
    private Boolean primaryApplicantAddressFound;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantIsApplying;

    private String primaryApplicantForenames;

    private String primaryApplicantSurname;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantSameWillName;

    private String primaryApplicantAlias;

    private String primaryApplicantAliasReason;

    private String primaryApplicantOtherReason;

    private String primaryApplicantPhoneNumber;

    @NotNull
    private Relationship primaryApplicantRelationshipToDeceased;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantAdoptionInEnglandOrWales;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willLatestCodicilHasDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willExists;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willAccessOriginal;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willHasCodicils;

    private Long willNumberOfCodicils;

    private Long numberOfExecutors;

    @JsonProperty(value = "executorsApplying")
    private List<CollectionMember<AdditionalExecutorApplying>> additionalExecutorsApplying;

    @JsonProperty(value = "executorsNotApplying")
    private List<CollectionMember<AdditionalExecutorNotApplying>> additionalExecutorsNotApplying;

    private String totalFee;

    private Declaration declaration;

    private LegalStatement legalStatement;

    private Long numberOfApplicants;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean assetsOverseas;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long assetsOverseasNetValue;

    private String uploadDocumentUrl;
}
