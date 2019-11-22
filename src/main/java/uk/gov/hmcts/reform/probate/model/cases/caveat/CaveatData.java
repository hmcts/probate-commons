package uk.gov.hmcts.reform.probate.model.cases.caveat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import uk.gov.hmcts.reform.probate.model.ProbateDocument;
import uk.gov.hmcts.reform.probate.model.ScannedDocument;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CasePayment;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.FullAliasName;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoDeserializer;
import uk.gov.hmcts.reform.probate.model.jackson.YesNoSerializer;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Caveat", parent = CaseData.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CaveatData extends CaseData {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @NotNull
    private String applicationId;

    private RegistryLocation registryLocation;

    @NotNull
    @Size(min = 2)
    private String deceasedForenames;

    @NotNull
    @Size(min = 2)
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

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean deceasedAnyOtherNames;

    private List<CollectionMember<FullAliasName>> deceasedFullAliasNameList;

    @NotNull
    private Address deceasedAddress;

    @NotNull
    @Size(min = 2)
    private String caveatorForenames;

    @NotNull
    @Size(min = 2)
    private String caveatorSurname;

    @NotNull
    private String caveatorEmailAddress;

    @NotNull
    private Address caveatorAddress;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expiryDate;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean paperForm;

    private List<CollectionMember<CasePayment>> payments;

    @NotNull
    private ApplicationType applicationType;

    private String recordId;

    private String legacyId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate applicationSubmittedDate;

    private String legacyType;

    private String legacyCaseViewUrl;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean caveatRaisedEmailNotificationRequested;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean sendToBulkPrintRequested;

    private List<CollectionMember<ProbateDocument>> notificationsGenerated;

    private List<CollectionMember<ScannedDocument>> scannedDocuments;

    private String bulkScanCaseReference;

    @JsonDeserialize(using = YesNoDeserializer.class)
    @JsonSerialize(using = YesNoSerializer.class)
    private Boolean languagePreferenceWelsh;

    @Transient
    @AssertTrue(message = "deceasedDateOfBirth must be before deceasedDateOfDeath")
    public boolean isDeceasedDateOfBirthBeforeDeceasedDateOfDeath() {
        return ObjectUtils.allNotNull(deceasedDateOfBirth, deceasedDateOfDeath)
            && deceasedDateOfBirth.isBefore(deceasedDateOfDeath);
    }
}
