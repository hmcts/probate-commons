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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CasePayment;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;
import uk.gov.hmcts.reform.probate.model.validation.AssertExpression;
import uk.gov.hmcts.reform.probate.model.validation.groups.SubmissionGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "GrantOfRepresentation", parent = CaseData.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AssertExpression(value = "!(#isTrue(deceasedAnyOtherNames) && #isEmpty(deceasedAliasNameList))",
        groups = SubmissionGroup.class)
@AssertExpression(value = "!(#L(ihtNetValue) <= 250000 && !#isTrue(deceasedHasAssetsOutsideUK))", groups = SubmissionGroup.class)
@AssertExpression(value = "!(#isTrue(deceasedHasAssetsOutsideUK) && #L(assetsOverseasNetValue) == 0)",
        groups = SubmissionGroup.class)
@AssertExpression(value = "deceasedDateOfBirth.isBefore(deceasedDateOfDeath)", groups = SubmissionGroup.class)
@AssertExpression(value = "#L(ihtNetValue) <= #L(ihtGrossValue)", groups = SubmissionGroup.class)
@AssertExpression(value = "!((#L(ihtNetValue) > 250000) && !#isSpouse(primaryApplicantRelationshipToDeceased) "
        + "&& (deceasedSpouseNotApplyingReason == null))", groups = SubmissionGroup.class)
@AssertExpression(value = "{'ADOPTED_CHILD', 'CHILD'}.contains(#R(primaryApplicantRelationshipToDeceased)) ? "
        + " deceasedOtherChildren != null "
        + ": true", groups = SubmissionGroup.class)
@AssertExpression(value = "#isTrue(deceasedDivorcedInEnglandOrWales) ? "
        + "{'DIVORCED', 'JUDICIALLY_SEPARATED'}.contains(#MS(deceasedMartialStatus)) "
        + ": !{'DIVORCED', 'JUDICIALLY_SEPARATED'}.contains(#MS(deceasedMartialStatus))",
        groups = SubmissionGroup.class)
@AssertExpression(value = "#isTrue(deceasedOtherChildren) ? childrenOverEighteenSurvived != null : true",
        groups = SubmissionGroup.class)
@AssertExpression(value = "#R(primaryApplicantRelationshipToDeceased) == 'ADOPTED_CHILD' ? "
        + "primaryApplicantAdoptionInEnglandOrWales != null : true", groups = SubmissionGroup.class)
@AssertExpression(value = "#R(primaryApplicantRelationshipToDeceased) == 'PARTNER' ? deceasedAnyChildren != null : true",
        groups = SubmissionGroup.class)
@AssertExpression(value = "#isTrue(deceasedOtherChildren) && #isTrue(childrenOverEighteenSurvived) ? "
        + "childrenDied != null : true", groups = SubmissionGroup.class)
@AssertExpression(value = "#isTrue(deceasedOtherChildren) && #isTrue(childrenOverEighteenSurvived) "
        + "&& #isTrue(childrenDied) ? "
        + "grandChildrenSurvivedUnderEighteen != null : true", groups = SubmissionGroup.class)
public class GrantOfRepresentation extends CaseData {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private ApplicationType applicationType;

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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAddressFound;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedMarriedAfterWillOrCodicilDate;

    @NotNull(groups = SubmissionGroup.class)
    @Size(min = 2, groups = SubmissionGroup.class)
    private String deceasedForenames;

    @NotNull(groups = SubmissionGroup.class)
    @Size(min = 2, groups = SubmissionGroup.class)
    private String deceasedSurname;

    @NotNull(groups = SubmissionGroup.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate deceasedDateOfDeath;

    @NotNull(groups = SubmissionGroup.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate deceasedDateOfBirth;

    @NotNull(groups = SubmissionGroup.class)
    private MaritalStatus deceasedMartialStatus;

    @NotNull(groups = SubmissionGroup.class)
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyOtherNames;

    private List<CollectionMember<AliasName>> deceasedAliasNameList;

    private SpouseNotApplyingReason deceasedSpouseNotApplyingReason;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedDivorcedInEnglandOrWales;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedOtherChildren;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyChildren;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenDied;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenDiedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenDiedUnderEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenSurvived;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenOverEighteenSurvived;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenUnderEighteenSurvived;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grandChildrenSurvived;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grandChildrenSurvivedUnderEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grandChildrenSurvivedOverEighteen;

    private IhtFormType ihtFormId;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean ihtFormCompletedOnline;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtNetValue;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtGrossValue;

    private String ihtReferenceNumber;

    private Address primaryApplicantAddress;

    private String primaryApplicantFreeTextAddress;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
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

    @NotNull(groups = SubmissionGroup.class)
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
    private Boolean deceasedHasAssetsOutsideUK;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long assetsOverseasNetValue;

    private String uploadDocumentUrl;

    private String registryAddress;

    private String registryEmail;

    private String registrySequenceNumber;

    private GrantType caseType;

    @Builder
    public GrantOfRepresentation(String primaryApplicantEmailAddress, List<CollectionMember<CasePayment>> payments,
                                 ApplicationType applicationType, LocalDate applicationSubmittedDate, Boolean softStop,
                                 String registryLocation, Long outsideUkGrantCopies, Long extraCopiesOfGrant,
                                 Boolean deceasedDomicileInEngWales, Address deceasedAddress,
                                 String deceasedFreeTextAddress, Boolean deceasedAddressFound,
                                 Boolean deceasedMarriedAfterWillOrCodicilDate, String deceasedForenames,
                                 String deceasedSurname, LocalDate deceasedDateOfDeath,
                                 LocalDate deceasedDateOfBirth, MaritalStatus deceasedMartialStatus,
                                 Boolean deceasedAnyOtherNames, List<CollectionMember<AliasName>> deceasedAliasNameList,
                                 SpouseNotApplyingReason deceasedSpouseNotApplyingReason, Boolean deceasedOtherChildren,
                                 Boolean deceasedDivorcedInEnglandOrWales, Boolean childrenDied,
                                 Boolean childrenDiedOverEighteen, Boolean childrenDiedUnderEighteen,
                                 Boolean childrenSurvived, Boolean childrenOverEighteenSurvived,
                                 Boolean childrenUnderEighteenSurvived, Boolean grandChildrenSurvived,
                                 Boolean grandChildrenSurvivedUnderEighteen, Boolean grandChildrenSurvivedOverEighteen,
                                 IhtFormType ihtFormId, Boolean ihtFormCompletedOnline, Long ihtNetValue,
                                 Long ihtGrossValue, String ihtReferenceNumber, Address primaryApplicantAddress,
                                 String primaryApplicantFreeTextAddress, Boolean primaryApplicantAddressFound,
                                 Boolean primaryApplicantIsApplying, String primaryApplicantForenames,
                                 String primaryApplicantSurname, Boolean primaryApplicantSameWillName,
                                 String primaryApplicantAlias, String primaryApplicantAliasReason,
                                 String primaryApplicantOtherReason, String primaryApplicantPhoneNumber,
                                 Relationship primaryApplicantRelationshipToDeceased,
                                 Boolean primaryApplicantAdoptionInEnglandOrWales, Boolean willLatestCodicilHasDate,
                                 Boolean willExists, Boolean willAccessOriginal, Boolean willHasCodicils,
                                 Long willNumberOfCodicils, Long numberOfExecutors,
                                 List<CollectionMember<AdditionalExecutorApplying>> additionalExecutorsApplying,
                                 List<CollectionMember<AdditionalExecutorNotApplying>> additionalExecutorsNotApplying,
                                 String totalFee, Declaration declaration, LegalStatement legalStatement,
                                 Long numberOfApplicants, Boolean deceasedHasAssetsOutsideUK,
                                 Long assetsOverseasNetValue, String uploadDocumentUrl, String registryAddress,
                                 String registryEmail, String registrySequenceNumber, GrantType caseType,
                                 Boolean deceasedAnyChildren) {
        super(primaryApplicantEmailAddress, payments);
        this.applicationType = applicationType;
        this.applicationSubmittedDate = applicationSubmittedDate;
        this.softStop = softStop;
        this.registryLocation = registryLocation;
        this.outsideUkGrantCopies = outsideUkGrantCopies;
        this.extraCopiesOfGrant = extraCopiesOfGrant;
        this.deceasedDomicileInEngWales = deceasedDomicileInEngWales;
        this.deceasedAddress = deceasedAddress;
        this.deceasedFreeTextAddress = deceasedFreeTextAddress;
        this.deceasedAddressFound = deceasedAddressFound;
        this.deceasedMarriedAfterWillOrCodicilDate = deceasedMarriedAfterWillOrCodicilDate;
        this.deceasedForenames = deceasedForenames;
        this.deceasedSurname = deceasedSurname;
        this.deceasedDateOfDeath = deceasedDateOfDeath;
        this.deceasedDateOfBirth = deceasedDateOfBirth;
        this.deceasedMartialStatus = deceasedMartialStatus;
        this.deceasedAnyOtherNames = deceasedAnyOtherNames;
        this.deceasedAliasNameList = deceasedAliasNameList;
        this.deceasedSpouseNotApplyingReason = deceasedSpouseNotApplyingReason;
        this.deceasedOtherChildren = deceasedOtherChildren;
        this.deceasedDivorcedInEnglandOrWales = deceasedDivorcedInEnglandOrWales;
        this.childrenDied = childrenDied;
        this.childrenDiedOverEighteen = childrenDiedOverEighteen;
        this.childrenDiedUnderEighteen = childrenDiedUnderEighteen;
        this.childrenSurvived = childrenSurvived;
        this.childrenOverEighteenSurvived = childrenOverEighteenSurvived;
        this.childrenUnderEighteenSurvived = childrenUnderEighteenSurvived;
        this.grandChildrenSurvived = grandChildrenSurvived;
        this.grandChildrenSurvivedUnderEighteen = grandChildrenSurvivedUnderEighteen;
        this.grandChildrenSurvivedOverEighteen = grandChildrenSurvivedOverEighteen;
        this.ihtFormId = ihtFormId;
        this.ihtFormCompletedOnline = ihtFormCompletedOnline;
        this.ihtNetValue = ihtNetValue;
        this.ihtGrossValue = ihtGrossValue;
        this.ihtReferenceNumber = ihtReferenceNumber;
        this.primaryApplicantAddress = primaryApplicantAddress;
        this.primaryApplicantFreeTextAddress = primaryApplicantFreeTextAddress;
        this.primaryApplicantAddressFound = primaryApplicantAddressFound;
        this.primaryApplicantIsApplying = primaryApplicantIsApplying;
        this.primaryApplicantForenames = primaryApplicantForenames;
        this.primaryApplicantSurname = primaryApplicantSurname;
        this.primaryApplicantSameWillName = primaryApplicantSameWillName;
        this.primaryApplicantAlias = primaryApplicantAlias;
        this.primaryApplicantAliasReason = primaryApplicantAliasReason;
        this.primaryApplicantOtherReason = primaryApplicantOtherReason;
        this.primaryApplicantPhoneNumber = primaryApplicantPhoneNumber;
        this.primaryApplicantRelationshipToDeceased = primaryApplicantRelationshipToDeceased;
        this.primaryApplicantAdoptionInEnglandOrWales = primaryApplicantAdoptionInEnglandOrWales;
        this.willLatestCodicilHasDate = willLatestCodicilHasDate;
        this.willExists = willExists;
        this.willAccessOriginal = willAccessOriginal;
        this.willHasCodicils = willHasCodicils;
        this.willNumberOfCodicils = willNumberOfCodicils;
        this.numberOfExecutors = numberOfExecutors;
        this.additionalExecutorsApplying = additionalExecutorsApplying;
        this.additionalExecutorsNotApplying = additionalExecutorsNotApplying;
        this.totalFee = totalFee;
        this.declaration = declaration;
        this.legalStatement = legalStatement;
        this.numberOfApplicants = numberOfApplicants;
        this.deceasedHasAssetsOutsideUK = deceasedHasAssetsOutsideUK;
        this.assetsOverseasNetValue = assetsOverseasNetValue;
        this.uploadDocumentUrl = uploadDocumentUrl;
        this.registryAddress = registryAddress;
        this.registryEmail = registryEmail;
        this.registrySequenceNumber = registrySequenceNumber;
        this.caseType = caseType;
        this.deceasedAnyChildren = deceasedAnyChildren;
    }
}
