package uk.gov.hmcts.reform.probate.model.cases.standingsearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;
import uk.gov.hmcts.reform.probate.model.cases.CaseMatch;
import uk.gov.hmcts.reform.probate.model.cases.CasePayment;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.FullAliasName;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.UploadDocument;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "StandingSearchData", parent = CaseData.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
public class StandingSearchData extends CaseData {

    private ApplicationType applicationType;

    private RegistryLocation registryLocation;

    private String deceasedNameSection;

    private String deceasedForenames;

    private String deceasedSurname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deceasedDateOfDeath;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deceasedDateOfBirth;

    private String deceasedAnyOtherNames;

    private List<CollectionMember<FullAliasName>> deceasedFullAliasNameList;

    private Address deceasedAddress;

    private String applicantNameSection;

    private String applicantForenames;

    private String applicantSurname;

    private String applicantEmailAddress;

    private Address applicantAddress;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long numberOfCopies;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expiryDate;

    private List<CollectionMember<CaseMatch>> caseMatches;

    private List<CollectionMember<UploadDocument>> documentsUploaded;

    private List<CollectionMember<CasePayment>> payments;

    private String recordId;

    private String legacyId;

    private String legacyType;

    private String legacyCaseViewUrl;
}
