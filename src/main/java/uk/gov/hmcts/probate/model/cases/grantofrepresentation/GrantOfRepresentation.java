package uk.gov.hmcts.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.hmcts.probate.model.cases.Address;
import uk.gov.hmcts.probate.model.cases.AliasName;
import uk.gov.hmcts.probate.model.cases.CaseData;
import uk.gov.hmcts.probate.model.cases.CollectionMember;
import uk.gov.hmcts.probate.model.cases.Payment;
import uk.gov.hmcts.probate.model.cases.YesNo;

import java.time.LocalDate;
import java.util.List;

@ApiModel(value = "GrantOfRepresentation", parent = CaseData.class)
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class GrantOfRepresentation extends CaseData {

    private LocalDate applicationSubmittedDate;

    private YesNo deceasedDomicileInEngWales;

    private String ihtFormId;

    private YesNo ihtFormCompletedOnline;

    private YesNo softStop;

    private String registryLocation;

    private String applicationType;

    @JsonProperty(value = "outsideUKGrantCopies")
    private Integer outsideUkGrantCopies;

    private Long extraCopiesOfGrant;

    private YesNo deceasedAnyOtherNames;

    private Long numberOfExecutors;

    private Address deceasedAddress;

    private YesNo willHasCodicils;

    private Long willNumberOfCodicils;

    private Declaration declaration;

    private List<CollectionMember<Payment>> payments;

    private LegalStatement legalStatement;

    private YesNo deceasedMarriedAfterWillOrCodicilDate;

    private String deceasedForenames;

    private String deceasedSurname;

    private LocalDate deceasedDateOfDeath;

    private LocalDate deceasedDateOfBirth;

    private Long numberOfApplicants;

    private YesNo willAccessOriginal;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtNetValue;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long ihtGrossValue;

    private String ihtReferenceNumber;

    private String primaryApplicantEmailAddress;

    private Address primaryApplicantAddress;

    private YesNo primaryApplicantIsApplying;

    private YesNo willExists;

    private String primaryApplicantForenames;

    private String primaryApplicantSurname;

    private YesNo primaryApplicantSameWillName;

    private String primaryApplicantAlias;

    private String primaryApplicantAliasReason;

    private String primaryApplicantOtherReason;

    private String primaryApplicantPhoneNumber;

    private YesNo willLatestCodicilHasDate;

    @JsonProperty(value = "executorsApplying")
    private List<CollectionMember<AdditionalExecutorApplying>> additionalExecutorsApplying;

    @JsonProperty(value = "executorsNotApplying")
    private List<CollectionMember<AdditionalExecutorNotApplying>> additionalExecutorsNotApplying;

    private String totalFee;

    private List<CollectionMember<AliasName>> deceasedAliasNameList;
}
