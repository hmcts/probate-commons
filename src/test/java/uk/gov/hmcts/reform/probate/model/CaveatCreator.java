package uk.gov.hmcts.reform.probate.model;

import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.FullAliasName;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.caveat.CaveatData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class CaveatCreator {

    public static CaveatData createCaveatCase() {
        CaveatData caveatData = new CaveatData();
        caveatData.setApplicationId("Id");
        caveatData.setApplicationType(ApplicationType.PERSONAL);
        caveatData.setCaveatorAddress(getAddress("caveator"));
        caveatData.setCaveatorEmailAddress("caveator@email.com");
        caveatData.setCaveatorForenames("caveator forename");
        caveatData.setCaveatorSurname("caveator surname");
        caveatData.setDeceasedAnyOtherNames(true);
        caveatData.setDeceasedAddress(getAddress("deceased"));
        CollectionMember<FullAliasName> fullAliasNameCollectionMember = new CollectionMember<>();
        fullAliasNameCollectionMember.setValue(FullAliasName.builder().fullAliasName("fullAliasName").build());
        caveatData.setDeceasedFullAliasNameList(Arrays.asList(fullAliasNameCollectionMember));
        caveatData.setDeceasedDateOfBirth(LocalDate.of(1966, 3, 4));
        caveatData.setDeceasedDateOfDeath(LocalDate.of(2018, 11, 20));
        caveatData.setApplicationSubmittedDate(LocalDate.of(2018, 11, 20));
        caveatData.setDeceasedForenames("deceased forename");
        caveatData.setDeceasedSurname("deceased surname");
        caveatData.setRegistryLocation(RegistryLocation.OXFORD);
        caveatData.setExpiryDate(LocalDate.of(2019, 2, 14));
        caveatData.setPaperForm(false);
        return caveatData;
    }

    public static CaveatData createCaveatCaseWithBulkScanData() {
        CaveatData caveatData = createCaveatCase();
        CollectionMember<ScannedDocument> scannedDocumentMember1 = new CollectionMember<>();
        scannedDocumentMember1.setValue(getScannedDocument("1"));
        CollectionMember<ScannedDocument> scannedDocumentMember2 = new CollectionMember<>();
        scannedDocumentMember2.setValue(getScannedDocument("2"));
        caveatData.setScannedDocuments((List<CollectionMember<ScannedDocument>>)
                Arrays.asList(scannedDocumentMember1, scannedDocumentMember2));
        return caveatData;
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

    private static ScannedDocument getScannedDocument(String docReference) {
        ProbateDocumentLink url = ProbateDocumentLink.builder()
                .documentBinaryUrl("http://localhost/" + docReference + "000.pdf")
                .documentFilename(docReference + "000.pdf")
                .documentUrl("http://localhost/" + docReference + "000.pdf")
                .build();
        LocalDateTime dateTime = LocalDateTime.parse("2019-07-15T12:34:56.789Z", DateTimeFormatter.ISO_DATE_TIME);
        return ScannedDocument.builder().controlNumber(docReference + "000")
                .fileName(docReference + "000.pdf")
                .type("form")
                .subtype("PA8A")
                .scannedDate(dateTime)
                .exceptionRecordReference(null)
                .deliveryDate(dateTime)
                .url(url)
                .build();
    }

    private CaveatCreator() {
    }
}
