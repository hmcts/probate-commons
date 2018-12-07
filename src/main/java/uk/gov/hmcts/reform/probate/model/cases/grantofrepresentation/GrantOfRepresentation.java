package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.Payment;
import uk.gov.hmcts.reform.probate.model.cases.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.validation.AliasNamesMandatory;
import uk.gov.hmcts.reform.probate.model.cases.validation.AssetsOverseasMandatory;
import uk.gov.hmcts.reform.probate.model.cases.validation.DateOfDeathBeforeDateOfBirth;
import uk.gov.hmcts.reform.probate.model.cases.validation.NetIHTLessThanGrossIHT;
import uk.gov.hmcts.reform.probate.model.cases.validation.NetValueAssetsOverseasMandatory;
import uk.gov.hmcts.reform.probate.model.cases.validation.NotNullIfAllFieldsHaveValue;
import uk.gov.hmcts.reform.probate.model.cases.validation.NotNullIfFieldHasEitherValue;
import uk.gov.hmcts.reform.probate.model.cases.validation.NotNullIfFieldHasValue;
import uk.gov.hmcts.reform.probate.model.cases.validation.SpouseNotApplyingReasonMandatory;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@ApiModel(value = "GrantOfRepresentation", parent = CaseData.class)
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AssetsOverseasMandatory
@NetValueAssetsOverseasMandatory
@DateOfDeathBeforeDateOfBirth
@NetIHTLessThanGrossIHT
@AliasNamesMandatory
@SpouseNotApplyingReasonMandatory
@NotNullIfFieldHasEitherValue.List({
        @NotNullIfFieldHasEitherValue(
                fieldName = "relationshipToDeceased",
                fieldValues = {"ADOPTED_CHILD", "CHILD"},
                dependFieldName = "deceasedOtherChildren"),
        @NotNullIfFieldHasEitherValue(
                fieldName = "deceasedMaritalStatus",
                fieldValues = {"DIVORCED", "JUDICIALLY_SEPERATED"},
                dependFieldName = "divorcedInEnglandOrWales")
})
@NotNullIfFieldHasValue.List({
        @NotNullIfFieldHasValue(
                fieldName="deceasedOtherChildren",
                fieldValue = "true",
                dependFieldName = "allDeceasedChildrenOverEighteen"),
        @NotNullIfFieldHasValue(
                fieldName="relationshipToDeceased",
                fieldValue = "ADOPTED_CHILD",
                dependFieldName = "adoptionInEnglandOrWales"),
        @NotNullIfFieldHasValue(
                fieldName="relationshipToDeceased",
                fieldValue = "SPOUSE",
                dependFieldName = "deceasedAnyChildren")
})
@NotNullIfAllFieldsHaveValue.List({
        @NotNullIfAllFieldsHaveValue(
                fieldNames = {"deceasedOtherChildren", "allDeceasedChildrenOverEighteen"},
                fieldValues = {"true","true"},
                dependFieldName = "anyDeceasedChildrenDieBeforeDeceased"),
        @NotNullIfAllFieldsHaveValue(
                fieldNames = {"deceasedOtherChildren", "allDeceasedChildrenOverEighteen","anyDeceasedChildrenDieBeforeDeceased"},
                fieldValues = {"true","true","true"},
                dependFieldName = "anyDeceasedGrandchildrenUnderEighteen"
        )
})
public class GrantOfRepresentation extends CaseData {

    private LocalDate applicationSubmittedDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean softStop;

    private String registryLocation;

    private ProbateType applicationType;

    @JsonProperty(value = "outsideUKGrantCopies")
    private Integer outsideUkGrantCopies;

    private Long extraCopiesOfGrant;

    @NotNull
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyOtherNames;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedDomicileInEngWales;

    private Address deceasedAddress;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedMarriedAfterWillOrCodicilDate;

    @NotNull
    @Size(min=2)
    private String deceasedForenames;

    @NotNull
    @Size(min=2)
    private String deceasedSurname;

    @NotNull
    private LocalDate deceasedDateOfDeath;

    @NotNull
    private LocalDate deceasedDateOfBirth;

    @NotNull
    private MaritalStatus deceasedMaritalStatus;

    private List<CollectionMember<AliasName>> deceasedAliasNameList;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyChildren;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedOtherChildren;

    @NotNull
    private Relationship relationshipToDeceased;

    private SpouseNotApplyingReason spouseNotApplyingReason;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private  Boolean adoptionInEnglandOrWales;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private  Boolean divorcedInEnglandOrWales;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedChildrenDieBeforeDeceased;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean allDeceasedChildrenOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedGrandchildrenUnderEighteen;

    private String ihtFormId;

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

    private List<CollectionMember<Payment>> payments;

    private LegalStatement legalStatement;

    private Long numberOfApplicants;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean assetsOverseas;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long netValueAssestsOverseas;


}
