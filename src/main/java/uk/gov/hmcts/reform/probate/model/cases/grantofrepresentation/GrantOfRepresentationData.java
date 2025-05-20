package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import uk.gov.hmcts.reform.probate.model.AdoptiveRelative;
import uk.gov.hmcts.reform.probate.model.AliasReason;
import uk.gov.hmcts.reform.probate.model.AttorneyNamesAndAddress;
import uk.gov.hmcts.reform.probate.model.BulkScanEnvelope;
import uk.gov.hmcts.reform.probate.model.IhtFormEstate;
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.ProbateDocument;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.ScannedDocument;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CasePayment;
import uk.gov.hmcts.reform.probate.model.cases.ChangeOfRepresentative;
import uk.gov.hmcts.reform.probate.model.cases.ChangeOrganisationRequest;
import uk.gov.hmcts.reform.probate.model.cases.CitizenResponse;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.CombinedName;
import uk.gov.hmcts.reform.probate.model.cases.DeathCertificate;
import uk.gov.hmcts.reform.probate.model.cases.DocumentLink;
import uk.gov.hmcts.reform.probate.model.cases.HandoffReason;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.OrganisationPolicy;
import uk.gov.hmcts.reform.probate.model.cases.ProbateCalculatedFees;
import uk.gov.hmcts.reform.probate.model.cases.RegistrarDirection;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.RemovedRepresentative;
import uk.gov.hmcts.reform.probate.model.cases.SolsAliasName;
import uk.gov.hmcts.reform.probate.model.cases.SolsPaymentMethods;
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
import java.time.LocalDateTime;
import java.util.List;

import static uk.gov.hmcts.reform.probate.model.YesNo.NO;
import static uk.gov.hmcts.reform.probate.model.YesNo.YES;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "GrantOfRepresentationData", parent = CaseData.class)
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrantOfRepresentationData extends CaseData {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @NotNull(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private ApplicationType applicationType;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    @Email(groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    @Size(min = 2, groups = {IntestacyFieldCheck.class, PaFieldCheck.class})
    private String primaryApplicantEmailAddress;

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

    private MaritalStatus deceasedMaritalStatus;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyOtherNames;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyOtherNameOnWill;

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
    private Boolean allDeceasedChildrenOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedChildrenDieBeforeDeceased;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyDeceasedGrandChildrenUnderEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenDied;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedForeignDeathCertInEnglish;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedForeignDeathCertTranslation;

    @JsonProperty("childrenDiedOverEighteen")
    public String childrenDiedOverEighteenText;

    @JsonProperty("childrenDiedUnderEighteen")
    public String childrenDiedUnderEighteenText;

    @JsonIgnore
    public Boolean childrenDiedOverEighteen;

    @JsonIgnore
    public Boolean childrenDiedUnderEighteen;

    public void setChildrenDiedOverEighteenText(String stringValue) {
        this.childrenDiedOverEighteenText = stringValue;
        this.childrenDiedOverEighteen = BooleanUtils.toBooleanObject(stringValue);
    }

    public void setChildrenDiedOverEighteen(Boolean booleanValue) {
        this.childrenDiedOverEighteen = booleanValue;
        this.childrenDiedOverEighteenText
            = BooleanUtils.toString(booleanValue, YES.getDescription(), NO.getDescription(), null);
    }

    public void setChildrenDiedUnderEighteenText(String stringValue) {
        this.childrenDiedUnderEighteenText = stringValue;
        this.childrenDiedUnderEighteen
            = BooleanUtils.toBooleanObject(stringValue);
    }

    public void setChildrenDiedUnderEighteen(Boolean booleanValue) {
        this.childrenDiedUnderEighteen = booleanValue;
        this.childrenDiedUnderEighteenText
            = BooleanUtils.toString(booleanValue, YES.getDescription(), NO.getDescription(), null);
    }

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean childrenSurvived;

    @JsonProperty("childrenOverEighteenSurvived")
    public String childrenOverEighteenSurvivedText;

    @JsonProperty("childrenUnderEighteenSurvived")
    public String childrenUnderEighteenSurvivedText;

    @JsonIgnore
    public Boolean childrenOverEighteenSurvived;

    @JsonIgnore
    public Boolean childrenUnderEighteenSurvived;

    public void setChildrenOverEighteenSurvivedText(String stringValue) {
        this.childrenOverEighteenSurvivedText = stringValue;
        this.childrenOverEighteenSurvived = BooleanUtils.toBooleanObject(stringValue);
    }

    public void setChildrenOverEighteenSurvived(Boolean booleanValue) {
        this.childrenOverEighteenSurvived = booleanValue;
        this.childrenOverEighteenSurvivedText
            = BooleanUtils.toString(booleanValue, YES.getDescription(), NO.getDescription(), null);
    }

    public void setChildrenUnderEighteenSurvivedText(String stringValue) {
        this.childrenUnderEighteenSurvivedText = stringValue;
        this.childrenUnderEighteenSurvived
            = BooleanUtils.toBooleanObject(stringValue);
    }

    public void setChildrenUnderEighteenSurvived(Boolean booleanValue) {
        this.childrenUnderEighteenSurvived = booleanValue;
        this.childrenUnderEighteenSurvivedText
            = BooleanUtils.toString(booleanValue, YES.getDescription(), NO.getDescription(), null);
    }

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grandChildrenSurvived;

    @JsonProperty("grandChildrenSurvivedUnderEighteen")
    public String grandChildrenSurvivedUnderEighteenText;

    @JsonProperty("grandChildrenSurvivedOverEighteen")
    public String grandChildrenSurvivedOverEighteenText;

    @JsonIgnore
    public Boolean grandChildrenSurvivedUnderEighteen;

    @JsonIgnore
    public Boolean grandChildrenSurvivedOverEighteen;

    public void setGrandChildrenSurvivedUnderEighteenText(String stringValue) {
        this.grandChildrenSurvivedUnderEighteenText = stringValue;
        this.grandChildrenSurvivedUnderEighteen = BooleanUtils.toBooleanObject(stringValue);
    }

    public void setGrandChildrenSurvivedUnderEighteen(Boolean booleanValue) {
        this.grandChildrenSurvivedUnderEighteen = booleanValue;
        this.grandChildrenSurvivedUnderEighteenText
            = BooleanUtils.toString(booleanValue, YES.getDescription(), NO.getDescription(), null);
    }

    public void setGrandChildrenSurvivedOverEighteenText(String stringValue) {
        this.grandChildrenSurvivedOverEighteenText = stringValue;
        this.grandChildrenSurvivedOverEighteen
            = BooleanUtils.toBooleanObject(stringValue);
    }

    public void setGrandChildrenSurvivedOverEighteen(Boolean booleanValue) {
        this.grandChildrenSurvivedOverEighteen = booleanValue;
        this.grandChildrenSurvivedOverEighteenText
            = BooleanUtils.toString(booleanValue, YES.getDescription(), NO.getDescription(), null);
    }

    private IhtFormType ihtFormId;


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
    private Boolean ihtFormEstateValuesCompleted;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean calcCheckCompleted;

    private IhtFormEstate ihtFormEstate;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtEstateGrossValue;

    private String ihtEstateGrossValueField;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtEstateNetValue;

    private String ihtEstateNetValueField;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtEstateNetQualifyingValue;

    private String ihtEstateNetQualifyingValueField;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedHadLateSpouseOrCivilPartner;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean ihtUnusedAllowanceClaimed;

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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantHasAlias;

    private String primaryApplicantAlias;

    private String deceasedAliasFirstNameOnWill;

    private String deceasedAliasLastNameOnWill;

    private AliasReason primaryApplicantAliasReason;

    private String primaryApplicantOtherReason;

    @NotBlank(groups = {IntestacyNullCheck.class, PaNullCheck.class})
    private String primaryApplicantPhoneNumber;

    private Relationship primaryApplicantRelationshipToDeceased;

    private String paRelationshipToDeceasedOther;

    private String primaryApplicantPostCode;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantAdoptionInEnglandOrWales;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantNotRequiredToSendDocuments;

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
    private Boolean willAccessNotarial;

    private String noOriginalWillAccessReason;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willHasVisibleDamage;

    private Damage willDamage;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willDamageReasonKnown;

    private String willDamageReasonDescription;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willDamageCulpritKnown;

    private CombinedName willDamageCulpritName;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willDamageDateKnown;

    private String willDamageDate;

    @NotNull(groups = {PaNullCheck.class})
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willHasCodicils;

    private Long willNumberOfCodicils;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean codicilsHasVisibleDamage;

    private Damage codicilsDamage;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean codicilsDamageReasonKnown;

    private String codicilsDamageReasonDescription;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean codicilsDamageCulpritKnown;

    private CombinedName codicilsDamageCulpritName;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean codicilsDamageDateKnown;

    private String codicilsDamageDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedWrittenWishes;

    @NotNull(groups = {PaNullCheck.class})
    @Min(value = 1, groups = {PaFieldCheck.class})
    private Long numberOfExecutors;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean executorsNamed;

    @Deprecated
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean executorsAllAlive;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean anyExecutorsDied;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean otherExecutorsApplying;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean executorsHaveAlias;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean paperForm;

    private String channelChoice;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean languagePreferenceWelsh;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedDiedEngOrWales;

    private DeathCertificate deceasedDeathCertificate;

    private List<CollectionMember<ExecutorApplying>> executorsApplying;

    private List<CollectionMember<ExecutorNotApplying>> executorsNotApplying;

    private String totalFee;

    @NotNull(groups = {PaSubmission.class, IntestacySubmission.class})
    @Valid
    private Declaration declaration;

    @Valid
    private Declaration welshDeclaration;

    @NotNull(groups = {PaSubmission.class, IntestacySubmission.class})
    @Valid
    private LegalStatement legalStatement;

    @Valid
    private LegalStatement welshLegalStatement;

    @NotNull(groups = {PaNullCheck.class})
    @Min(value = 1, groups = {PaFieldCheck.class})
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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean solsSolicitorIsApplying;

    private SolsPaymentMethods solsPaymentMethods;

    private String solsFeeAccountNumber;

    @SuppressWarnings({"AbbreviationAsWordInName"})
    private String solsSOTName;

    @SuppressWarnings({"AbbreviationAsWordInName"})
    private String solsSOTForenames;

    @SuppressWarnings({"AbbreviationAsWordInName"})
    private String solsSOTSurname;

    private String solsSolicitorEmail;

    private String solsSolicitorPhoneNumber;

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

    private String ihtGrossValueField;

    private String ihtNetValueField;

    private List<CollectionMember<UploadDocument>> boDocumentsUploaded;

    private DocumentLink statementOfTruthDocument;

    private DocumentLink solsCoversheetDocument;

    private String pcqId;

    /* START: Additional Bulk Scanning PA1A PA1P Form fields for case creation */

    private List<CollectionMember<ScannedDocument>> scannedDocuments;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean adopted;

    private List<CollectionMember<AdoptiveRelative>> adoptiveRelatives;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean spouseOrPartner;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean parentsExistSurvived;
    private String parentsExistUnderEighteenSurvived;
    private String parentsExistOverEighteenSurvived;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean wholeBloodSiblingsSurvived;
    private String wholeBloodSiblingsSurvivedUnderEighteen;
    private String wholeBloodSiblingsSurvivedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean wholeBloodSiblingsDied;
    private String wholeBloodSiblingsDiedUnderEighteen;
    private String wholeBloodSiblingsDiedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean wholeBloodNeicesAndNephews;
    private String wholeBloodNeicesAndNephewsUnderEighteen;
    private String wholeBloodNeicesAndNephewsOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean halfBloodSiblingsSurvived;
    private String halfBloodSiblingsSurvivedUnderEighteen;
    private String halfBloodSiblingsSurvivedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean halfBloodSiblingsDied;
    private String halfBloodSiblingsDiedUnderEighteen;
    private String halfBloodSiblingsDiedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean halfBloodNeicesAndNephews;
    private String halfBloodNeicesAndNephewsUnderEighteen;
    private String halfBloodNeicesAndNephewsOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grandparentsDied;
    private String grandparentsDiedUnderEighteen;
    private String grandparentsDiedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean wholeBloodUnclesAndAuntsSurvived;
    private String wholeBloodUnclesAndAuntsSurvivedUnderEighteen;
    private String wholeBloodUnclesAndAuntsSurvivedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean wholeBloodUnclesAndAuntsDied;
    private String wholeBloodUnclesAndAuntsDiedUnderEighteen;
    private String wholeBloodUnclesAndAuntsDiedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean wholeBloodCousinsSurvived;
    private String wholeBloodCousinsSurvivedUnderEighteen;
    private String wholeBloodCousinsSurvivedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean halfBloodUnclesAndAuntsSurvived;
    private String halfBloodUnclesAndAuntsSurvivedUnderEighteen;
    private String halfBloodUnclesAndAuntsSurvivedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean halfBloodUnclesAndAuntsDied;
    private String halfBloodUnclesAndAuntsDiedUnderEighteen;
    private String halfBloodUnclesAndAuntsDiedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean halfBloodCousinsSurvived;
    private String halfBloodCousinsSurvivedUnderEighteen;
    private String halfBloodCousinsSurvivedOverEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean applyingAsAnAttorney;

    private List<CollectionMember<AttorneyNamesAndAddress>> attorneyOnBehalfOfNameAndAddress;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean mentalCapacity;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean courtOfProtection;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean epaOrLpa;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean epaRegistered;

    private String domicilityCountry;

    // If domicilityEntrustingDocument or domicilitySuccessionIHTCert is true
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean domicilityIHTCert;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate willDate;

    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willsOutsideOfUK;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willGiftUnderEighteen;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean notifiedApplicants;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean foreignAsset;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long foreignAssetEstateValue;

    private String primaryApplicantSecondPhoneNumber;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long applicationFeePaperForm;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long feeForCopiesPaperForm;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long totalFeePaperForm;

    private String paperPaymentMethod;
    private String paymentReferenceNumberPaperform;

    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate dateOfMarriageOrCP;

    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate dateOfDivorcedCPJudicially;

    private String courtOfDecree;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean boEmailRequestInfoNotificationRequested;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean boSendToBulkPrintRequested;

    private String bulkScanCaseReference;

    private List<CollectionMember<BulkScanEnvelope>> bulkScanEnvelopes;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate grantDelayedNotificationDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate grantStoppedDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grantDelayedNotificationIdentified;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grantDelayedNotificationSent;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate grantAwaitingDocumentationNotificationDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean grantAwaitingDocumentatioNotificationSent;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate lastEvidenceAddedDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean documentUploadedAfterCaseStopped;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean evidenceHandled;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime moveToDormantDateTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastModifiedDateForDormant;

    private List<CollectionMember<ProbateDocument>> probateNotificationsGenerated;

    private List<CollectionMember<DeathRecord>> deathRecords;

    private OrganisationPolicy applicantOrganisationPolicy;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean caseHandedOffToLegacySite;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean dormantNotificationSent;

    private List<CollectionMember<RegistrarDirection>> registrarDirections;
    private RegistrarDirection registrarDirectionToAdd;

    private ChangeOfRepresentative changeOfRepresentative;
    private List<CollectionMember<ChangeOfRepresentative>> changeOfRepresentatives;
    private RemovedRepresentative removedRepresentative;
    private ChangeOrganisationRequest changeOrganisationRequestField;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean hmrcLetterId;

    private String uniqueProbateCodeId;

    private String serviceRequestReference;

    private String paymentTaken;

    private String applicationSubmittedBy;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean documentsReceivedNotificationSent;

    private SolicitorWillType solsWillType;

    private String solsWillTypeReason;

    private List<CollectionMember<HandoffReason>> boHandoffReasonList;

    private String citizenResponse;

    private String expectedResponseDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean citizenResponseCheckbox;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean documentUploadIssue;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean informationNeeded;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean informationNeededByPost;

    private List<CollectionMember<UploadDocument>> citizenDocumentsUploaded;

    private List<CollectionMember<CitizenResponse>> citizenResponses;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean isSaveAndClose;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate firstStopReminderSentDate;

    /* END: Additional Bulk Scanning PA1A PA1P Form fields for case creation */

    @Transient
    public void setInvitationDetailsForExecutorApplying(String email, String invitationId, String leadApplicantName,
                                                        String executorName) {
        ExecutorApplying e = this.getExecutorApplyingByEmailAddress(email, executorName);
        if (e != null) {
            e.setApplyingExecutorInvitationId(invitationId);
            e.setApplyingExecutorLeadName(leadApplicantName);
        }
    }

    @Transient
    public void setInvitationDetailsForExecutorApplying(String email, String invitationId,
                                                        boolean agreed) {
        ExecutorApplying e = this.getExecutorApplyingByEmailAddress(email);
        if (e != null) {
            e.setApplyingExecutorInvitationId(invitationId);
            e.setApplyingExecutorAgreed(agreed);
        }
    }

    @Transient
    public void deleteInvitation(String invitationId) {
        this.getExecutorApplyingByInviteId(invitationId).setApplyingExecutorInvitationId(null);
    }

    @Transient
    public void updateInvitationContactDetailsForExecutorApplying(String invitationId, String email,
                                                                  String phoneNumber) {
        ExecutorApplying e = this.getExecutorApplyingByInviteId(invitationId);
        if (e != null) {
            e.setApplyingExecutorPhoneNumber(phoneNumber);
            e.setApplyingExecutorEmail(email);
        }
    }

    @Transient
    public void setInvitationAgreedFlagForExecutorApplying(String invitationId, Boolean invitationAgreed) {
        this.getExecutorApplyingByInviteId(invitationId).setApplyingExecutorAgreed(invitationAgreed);
    }

    @Transient
    public Boolean haveAllExecutorsAgreed() {
        return this.getExecutorsApplying().stream().filter(executorApplying ->
            (executorApplying.getValue().getApplyingExecutorApplicant() == null
                || !executorApplying.getValue().getApplyingExecutorApplicant())).allMatch(executorApplying ->
            executorApplying.getValue().getApplyingExecutorAgreed() != null
                && executorApplying.getValue().getApplyingExecutorAgreed())
            && (this.getDeclarationCheckbox() != null && this.getDeclarationCheckbox());
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
    public ExecutorApplying getExecutorApplyingByEmailAddress(String emailAddress, String executorName) {
        return this.getExecutorsApplying().stream()
            .filter(executorApplying -> executorApplying.getValue().getApplyingExecutorEmail() != null
                && executorApplying.getValue().getApplyingExecutorEmail().equalsIgnoreCase(emailAddress)
                && executorApplying.getValue().getApplyingExecutorName() != null
                && executorApplying.getValue().getApplyingExecutorName().equals(executorName)
            ).map(CollectionMember::getValue)
            .collect(CollectorUtils.toSingleton());
    }

    @Transient
    public ExecutorApplying getExecutorApplyingByEmailAddress(String emailAddress) {
        return this.getExecutorsApplying().stream()
            .filter(executorApplying -> executorApplying.getValue().getApplyingExecutorEmail() != null
                && executorApplying.getValue().getApplyingExecutorEmail().equalsIgnoreCase(emailAddress)
            ).map(CollectionMember::getValue)
            .collect(CollectorUtils.toSingleton());
    }

    @Transient
    public Boolean haveInvitesBeenSent() {
        return this.getExecutorsApplying() != null
            ? this.getExecutorsApplying().stream().filter(e -> e.getValue().getApplyingExecutorApplicant() == null
            || !e.getValue().getApplyingExecutorApplicant())
            .allMatch(e -> e.getValue().getApplyingExecutorInvitationId() != null) : null;
    }

    @Transient
    public Boolean isDeceasedDeathCertInEnglish() {
        if (this.getDeceasedDiedEngOrWales() != null && this.getDeceasedForeignDeathCertInEnglish() != null) {
            return this.getDeceasedDiedEngOrWales() ? null : this.getDeceasedForeignDeathCertInEnglish();
        }
        return null;
    }

    @Transient
    public Boolean isDeceasedForeignDeathCertTranslated() {
        if (this.getDeceasedDiedEngOrWales() != null
                && this.getDeceasedForeignDeathCertInEnglish() != null
                        && this.getDeceasedForeignDeathCertTranslation() != null) {
            return this.getDeceasedDiedEngOrWales() || this.getDeceasedForeignDeathCertInEnglish()
                    ? null : this.getDeceasedForeignDeathCertTranslation();
        }
        return null;
    }

    @Transient
    public String getDeceasedDeathCert() {
        if (this.getDeceasedDeathCertificate() != null && this.getDeceasedDiedEngOrWales() != null) {
            return this.getDeceasedDiedEngOrWales() ? this.getDeceasedDeathCertificate().getDescription() : null;
        }
        return null;
    }

    @Transient
    @AssertTrue(message = "deceasedDateOfBirth must be on or before deceasedDateOfDeath",
        groups = {IntestacyCrossFieldCheck.class, PaCrossFieldCheck.class})
    public boolean isDeceasedDateOfBirthBeforeDeceasedDateOfDeath() {
        return ObjectUtils.allNotNull(deceasedDateOfBirth, deceasedDateOfDeath)
            && !deceasedDateOfBirth.isAfter(deceasedDateOfDeath);
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
    @AssertTrue(message = "when deceasedMaritalStatus is DIVORCED, deceasedDivorcedInEnglandOrWales cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isDivorcedInEnglandOrWalesPopulatedWhenDeceasedDivorced() {
        return ObjectUtils.allNotNull(deceasedMaritalStatus)
            && (deceasedMaritalStatus.equals(MaritalStatus.DIVORCED) && deceasedDivorcedInEnglandOrWales == null);
    }

    @Transient
    @AssertTrue(message = "when deceasedMaritalStatus is JUDICIALLY_SEPARATED, "
        + "deceasedDivorcedInEnglandOrWales cannot be null",
        groups = {IntestacyCrossFieldCheck.class})
    public Boolean isDivorcedInEnglandOrWalesPopulatedWhenDeceasedSeperated() {
        return ObjectUtils.allNotNull(deceasedMaritalStatus)
            && (deceasedMaritalStatus.equals(MaritalStatus.JUDICIALLY_SEPARATED)
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
