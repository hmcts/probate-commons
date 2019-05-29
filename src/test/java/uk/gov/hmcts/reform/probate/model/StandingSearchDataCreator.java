package uk.gov.hmcts.reform.probate.model;

import com.google.common.collect.Lists;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.DocumentLink;
import uk.gov.hmcts.reform.probate.model.cases.FullAliasName;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.UploadDocument;
import uk.gov.hmcts.reform.probate.model.cases.standingsearch.StandingSearchData;

import java.time.LocalDate;
import java.util.List;

public class StandingSearchDataCreator {

    public static StandingSearchData createStandingSearchData() {
        return StandingSearchData.builder()
                .applicantAddress(getAddress("ssApplicant"))
                .applicantEmailAddress("ssApplicantEmailAddress")
                .applicantForenames("ssApplicantForenames")
                .applicantNameSection("ssApplicantNameSection")
                .applicantSurname("ssApplicantSurname")
                .applicationType(ApplicationType.PERSONAL)
                .deceasedAddress(getAddress("ssDeceased"))
                .deceasedAnyOtherNames(true)
                .deceasedDateOfBirth(LocalDate.of(1966, 3, 4))
                .deceasedDateOfDeath(LocalDate.of(2018, 11, 20))
                .deceasedForenames("ssDeceasedForeNames")
                .deceasedFullAliasNameList(getFullAliasNameList())
                .deceasedNameSection("ssDeceasedNameSection")
                .deceasedSurname("ssDeceasedSurname")
                .documentsUploaded(getDocumentsUploaded())
                .expiryDate(LocalDate.of(2019, 1, 1))
                .applicationSubmittedDate(LocalDate.of(2019, 1, 1))
                .numberOfCopies(12L)
                .registryLocation(RegistryLocation.WINCHESTER)
                .build();
    }

    private static List<CollectionMember<UploadDocument>> getDocumentsUploaded() {
        CollectionMember<UploadDocument> documentCollectionMember = new CollectionMember<>();
        documentCollectionMember.setValue(UploadDocument.builder()
                .comment("ssComment")
                .documentLink(DocumentLink.builder()
                        .documentBinaryUrl("ssDocumentBinaryUrl")
                        .documentFilename("ssDocumentFileName")
                        .documentUrl("ssDocumentUrl")
                        .build())
                .build());
        return Lists.newArrayList(documentCollectionMember);
    }

    private static List<CollectionMember<FullAliasName>> getFullAliasNameList() {
        CollectionMember<FullAliasName> fullAliasNameCollectionMember = new CollectionMember<>();
        fullAliasNameCollectionMember.setValue(FullAliasName.builder()
                .fullAliasName("fullAliasName").build());
        return Lists.newArrayList(fullAliasNameCollectionMember);
    }

    private static Address getAddress(String name) {
        Address address = new Address();
        address.setAddressLine1(name + " address line 1");
        address.setAddressLine2(name + " address line 2");
        address.setAddressLine3(name + " address line 3");
        address.setCounty(name + " county");
        address.setPostTown(name + " post town");
        address.setPostCode(name + " post code");
        address.setCountry(name + " country");
        return address;
    }

    private StandingSearchDataCreator() {
    }
}
