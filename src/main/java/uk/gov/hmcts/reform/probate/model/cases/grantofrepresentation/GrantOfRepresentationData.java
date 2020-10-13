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
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import uk.gov.hmcts.reform.probate.model.AdoptiveRelative;
import uk.gov.hmcts.reform.probate.model.AliasReason;
import uk.gov.hmcts.reform.probate.model.AttorneyNamesAndAddress;
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.ProbateDocument;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.ScannedDocument;
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
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static uk.gov.hmcts.reform.probate.model.YesNo.NO;
import static uk.gov.hmcts.reform.probate.model.YesNo.YES;

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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean primaryApplicantHasAlias;

    private String primaryApplicantAlias;

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
    private Boolean willLatestCodicilHasDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willExists;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willAccessOriginal;

    @NotNull(groups = {PaNullCheck.class})
    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean willHasCodicils;

    private Long willNumberOfCodicils;

    @NotNull(groups = {PaNullCheck.class})
    @Min(value = 1, groups = {PaFieldCheck.class})
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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean languagePreferenceWelsh;

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

    private List<CollectionMember<ProbateDocument>> probateNotificationsGenerated;

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