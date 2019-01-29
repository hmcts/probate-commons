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
import lombok.AllArgsConstructor;
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
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;
import uk.gov.hmcts.reform.probate.model.validation.groups.SubmissionGroup;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "GrantOfRepresentationData", parent = CaseData.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class GrantOfRepresentationData extends CaseData {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private ApplicationType applicationType;

    private String primaryApplicantEmailAddress;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate applicationSubmittedDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean softStop;

    private RegistryLocation registryLocation;

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
    @SuppressWarnings({"AbbreviationAsWordInName"})
    private Boolean deceasedHasAssetsOutsideUK;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long assetsOverseasNetValue;

    private String uploadDocumentUrl;

    private String registryAddress;

    private String registryEmail;

    private String registrySequenceNumber;

    @JsonProperty(value = "caseType")
    private GrantType grantType;

    private List<CollectionMember<CasePayment>> payments;

}
