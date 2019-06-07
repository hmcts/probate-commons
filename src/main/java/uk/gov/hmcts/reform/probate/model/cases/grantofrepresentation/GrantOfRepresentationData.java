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
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CasePayment;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.ProbateCalculatedFees;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.SolsAliasName;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.time.LocalDate;
import java.util.List;

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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAddressFound;

    private String deceasedAddresses;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedMarriedAfterWillOrCodicilDate;

    private String deceasedForenames;

    private String deceasedSurname;

    private String deceasedPostCode;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate deceasedDateOfDeath;

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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean ihtFormCompletedOnline;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtNetValue;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtGrossValue;

    private String ihtReferenceNumber;

    private Address primaryApplicantAddress;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantAddressFound;

    private String primaryApplicantAddresses;

    private String primaryApplicantPostCode;

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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean declarationCheckbox;

    private String legalDeclarationJson;

    private String checkAnswersSummaryJson;

    private ProbateCalculatedFees fees;

    private String ihtGrossValueField;

    private String ihtNetValueField;
}
