package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CasePayment;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.DocumentLink;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.ProbateCalculatedFees;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.SolsAliasName;
import uk.gov.hmcts.reform.probate.model.cases.UploadDocument;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;
import uk.gov.hmcts.reform.probate.model.validation.groups.crossfieldcheck.IntestacyCrossFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.crossfieldcheck.PaCrossFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.fieldcheck.IntestacyFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.fieldcheck.PaFieldCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.nullcheck.IntestacyNullCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.nullcheck.PaNullCheck;
import uk.gov.hmcts.reform.probate.model.validation.groups.submission.IntestacySubmission;
import uk.gov.hmcts.reform.probate.model.validation.groups.submission.PaSubmission;
import uk.gov.hmcts.reform.probate.utils.CollectorUtils;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "GrantOfRepresentationData", parent = CaseData.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrantOfRepresentationData extends CaseData {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private ApplicationType applicationType;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Email(groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    @Size(min = 2, groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    private String primaryApplicantEmailAddress;

    @NotNull(groups = {PaSubmission.class, IntestacySubmission.class})
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate applicationSubmittedDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean softStop;

    private RegistryLocation registryLocation;

    private String registryAddress;

    private String registryEmailAddress;

    private Long registrySequenceNumber;

    @JsonProperty(value = "outsideUKGrantCopies")
    private Long outsideUkGrantCopies;

    private Long extraCopiesOfGrant;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedDomicileInEngWales;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Valid
    private Address deceasedAddress;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAddressFound;

    private String deceasedAddresses;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedMarriedAfterWillOrCodicilDate;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Size(min = 2, groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    private String deceasedForenames;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Size(min = 2, groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    private String deceasedSurname;

    //TODO: Remove?
    private String deceasedPostCode;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate deceasedDateOfDeath;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate deceasedDateOfBirth;

    private MaritalStatus deceasedMartialStatus;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyOtherNames;

    private List<CollectionMember<AliasName>> deceasedAliasNameList;

    private List<CollectionMember<SolsAliasName>> solsDeceasedAliasNamesList;

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

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean ihtFormCompletedOnline;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtNetValue;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtGrossValue;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean assetsOutside;

    private String ihtReferenceNumber;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Valid
    private Address primaryApplicantAddress;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantAddressFound;

    private String primaryApplicantAddresses;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantIsApplying;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private String primaryApplicantForenames;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private String primaryApplicantSurname;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantSameWillName;

    private String primaryApplicantAlias;

    private String primaryApplicantAliasReason;

    private String primaryApplicantOtherReason;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private String primaryApplicantPhoneNumber;

    private Relationship primaryApplicantRelationshipToDeceased;

    private String primaryApplicantPostCode;

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

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willHasCodicils;

    private Long willNumberOfCodicils;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Min(value = 1, groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    private Long numberOfExecutors;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean executorsAllAlive;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean otherExecutorsApplying;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean executorsHaveAlias;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean paperForm;

    private List<CollectionMember<ExecutorApplying>> executorsApplying;

    private List<CollectionMember<ExecutorNotApplying>> executorsNotApplying;

    private String totalFee;

    @NotNull(groups = {PaSubmission.class, IntestacySubmission.class})
    @Valid
    private Declaration declaration;

    @NotNull(groups = {PaSubmission.class, IntestacySubmission.class})
    @Valid
    private LegalStatement legalStatement;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Min(value = 1, groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    private Long numberOfApplicants;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    @SuppressWarnings({"AbbreviationAsWordInName"})
    private Boolean deceasedHasAssetsOutsideUK;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long assetsOutsideNetValue;

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @JsonProperty(value = "caseType")
    private GrantType grantType;

    @NotNull(groups = {PaSubmission.class, IntestacySubmission.class})
    @Size(min = 1, groups = {PaSubmission.class, IntestacySubmission.class})
    private List<CollectionMember<CasePayment>> payments;

    private String recordId;

    private String legacyId;

    private String legacyType;

    private String legacyCaseViewUrl;

    //todo alignment with BO case data needed here
    private String solsSolicitorAppReference;

    private Address solsSolicitorAddress;

    private String solsSolicitorFirmName;

    // Will this be required if we remove submissionReference??
    @SuppressWarnings({"AbbreviationAsWordInName"})
    private Long applicationID;

    @NotNull(groups = {PaSubmission.class, IntestacySubmission.class})
    @AssertTrue(groups = {PaSubmission.class, IntestacySubmission.class})
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean declarationCheckbox;

    //@NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private String legalDeclarationJson;

    //@NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private String checkAnswersSummaryJson;

    private ProbateCalculatedFees fees;

    private List<CollectionMember<UploadDocument>> boDocumentsUploaded;

    private DocumentLink statementOfTruthDocument;

    @Transient
    public void setInvitationDetailsForExecutorApplying(String email, String invitationId, String leadApplicantName) {
        ExecutorApplying e = this.getExecutorApplyingByEmailAddress(email);
        e.setApplyingExecutorInvitationId(invitationId);
        e.setApplyingExecutorLeadName(leadApplicantName);
    }

    @Transient
    public void setInvitationDetailsForExecutorApplying(String email, String invitationId, boolean agreed) {
        ExecutorApplying e = this.getExecutorApplyingByEmailAddress(email);
        e.setApplyingExecutorInvitationId(invitationId);
        e.setApplyingExecutorAgreed(agreed);
    }

    @Transient
    public void deleteInvitation(String invitationId) {
        this.getExecutorApplyingByInviteId(invitationId).setApplyingExecutorInvitationId(null);
    }

    @Transient
    public void updateInvitationContactDetailsForExecutorApplying(String invitationId, String email,
                                                                  String phoneNumber) {
        ExecutorApplying e = this.getExecutorApplyingByInviteId(invitationId);
        e.setApplyingExecutorPhoneNumber(phoneNumber);
        e.setApplyingExecutorEmail(email);
    }

    @Transient
    public void setInvitationAgreedFlagForExecutorApplying(String invitationId, Boolean invitationAgreed) {
        this.getExecutorApplyingByInviteId(invitationId).setApplyingExecutorAgreed(invitationAgreed);
    }

    @Transient
    public Boolean haveAllExecutorsAgreed() {
        return this.getExecutorsApplying().stream().allMatch(executorApplying ->
            executorApplying.getValue().getApplyingExecutorAgreed() != null
                && executorApplying.getValue().getApplyingExecutorAgreed())
            && this.getDeclarationCheckbox();
    }

    @Transient
    public void resetExecutorsApplyingAgreedFlags() {
        this.getExecutorsApplying().forEach(executorsApplying ->
            executorsApplying.getValue().setApplyingExecutorAgreed(null));
    }

    @Transient
    public ExecutorApplying getExecutorApplyingByInviteId(String invitationId) {
        return this.getExecutorsApplying().stream()
            .filter(executorApplying -> executorApplying.getValue().getApplyingExecutorInvitationId() != null
                && executorApplying.getValue().getApplyingExecutorInvitationId()
                .equals(invitationId)).map(CollectionMember::getValue)
            .collect(CollectorUtils.toSingleton());
    }

    @Transient
    public ExecutorApplying getExecutorApplyingByEmailAddress(String emailAddress) {
        return this.getExecutorsApplying().stream()
            .filter(executorApplying -> executorApplying.getValue().getApplyingExecutorEmail() != null
                && executorApplying.getValue().getApplyingExecutorEmail()
                .equals(emailAddress)).map(CollectionMember::getValue)
            .collect(CollectorUtils.toSingleton());
    }

    @Transient
    public Boolean haveInvitesBeenSent() {
        return this.getExecutorsApplying() != null ? this.getExecutorsApplying().stream()
            .allMatch(e -> e.getValue().getApplyingExecutorInvitationId() != null) : null;
    }

    @Transient
    @AssertTrue(message = "deceasedDateOfBirth must be before deceasedDateOfDeath",
        groups = {IntestacyCrossFieldCheck.class, PaCrossFieldCheck.class})
    public boolean isDeceasedDateOfBirthBeforeDeceasedDateOfDeath() {
        return ObjectUtils.allNotNull(deceasedDateOfBirth, deceasedDateOfDeath)
            && deceasedDateOfBirth.isBefore(deceasedDateOfDeath);
    }

    @Transient
    @AssertTrue(message = "deceasedAliasNameList must not be empty", groups = {IntestacyCrossFieldCheck.class})
    public Boolean isAliasNameListPopulated() {
        return ObjectUtils.allNotNull(deceasedAnyOtherNames)
            && (deceasedAnyOtherNames && CollectionUtils.isEmpty(deceasedAliasNameList));
    }

    @SuppressWarnings({"AbbreviationAsWordInName"})
    @Transient
    @AssertTrue(message = "when ihtNetValue is less than 2500000, deceasedHasAssetsOutsideUk cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isDeceasedAssetsOutsideUKPopulated() {
        return ObjectUtils.allNotNull(ihtNetValue) && (ihtNetValue <= 2500000L && deceasedHasAssetsOutsideUK == null);
    }

    @Transient
    @AssertTrue(message = "when relationshipToDeceasedIsAdoptedChild is ADOPTED_CHILD, "
        + "deceasedOtherChildren cannot be null and primaryApplicantAdoptionInEnglandOrWales cannot be false",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isDeceasedOtherChildPopulatedWhenRelationshipToDeceasedIsAdoptedChild() {
        return ObjectUtils.allNotNull(primaryApplicantRelationshipToDeceased, primaryApplicantAdoptionInEnglandOrWales)
            && (primaryApplicantRelationshipToDeceased.equals(Relationship.ADOPTED_CHILD)
            && !primaryApplicantAdoptionInEnglandOrWales && deceasedOtherChildren != null);
    }

    @Transient
    @AssertTrue(message = "when relationshipToDeceasedIsChild is CHILD, deceasedOtherChildren cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isDeceasedOtherChildPopulatedWhenRelationshipToDeceasedIsChild() {
        return ObjectUtils.allNotNull(primaryApplicantRelationshipToDeceased)
            && (primaryApplicantRelationshipToDeceased.equals(Relationship.CHILD) && deceasedOtherChildren == null);

    }

    @Transient
    @AssertTrue(message = "when deceasedMartialStatus is DIVORCED, deceasedDivorcedInEnglandOrWales cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isDivorcedInEnglandOrWalesPopulatedWhenDeceasedDivorced() {
        return ObjectUtils.allNotNull(deceasedMartialStatus)
            && (deceasedMartialStatus.equals(MaritalStatus.DIVORCED) && deceasedDivorcedInEnglandOrWales == null);
    }

    @Transient
    @AssertTrue(message = "when deceasedMartialStatus is JUDICIALLY_SEPARATED, "
        + "deceasedDivorcedInEnglandOrWales cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isDivorcedInEnglandOrWalesPopulatedWhenDeceasedSeperated() {
        return ObjectUtils.allNotNull(deceasedMartialStatus)
            && (deceasedMartialStatus.equals(MaritalStatus.JUDICIALLY_SEPARATED)
            && deceasedDivorcedInEnglandOrWales == null);
    }

    @Transient
    @AssertTrue(message = "when deceasedOtherChildren is true, childrenOverEighteenSurvived cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isAllDeceasedChildrenOverEighteenPopulatedWhenDeceasedHasOtherChildren() {
        return ObjectUtils.allNotNull(deceasedOtherChildren) && (deceasedOtherChildren
            && childrenOverEighteenSurvived == null);
    }

    @Transient
    @AssertTrue(message = "when deceasedOtherChildren is true, childrenDied cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isChildrenDiedPopulatedWhenDeceasedHasOtherChildren() {
        return ObjectUtils.allNotNull(deceasedOtherChildren && (deceasedOtherChildren && childrenDied == null));
    }

    @Transient
    @AssertTrue(message = "when deceasedOtherChildren and childrenDied are true, "
        + "childrenOverEighteenSurvived cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isGrandChildrenSurvivedUnderEighteenPopulatedWhenMandatory() {
        return ObjectUtils.allNotNull(deceasedOtherChildren, childrenOverEighteenSurvived, childrenDied)
            && (deceasedOtherChildren && childrenDied && childrenOverEighteenSurvived == null);
    }
}
