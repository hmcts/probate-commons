package uk.gov.hmcts.reform.probate.model;

import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.FullAliasName;
import uk.gov.hmcts.reform.probate.model.cases.Organisation;
import uk.gov.hmcts.reform.probate.model.cases.OrganisationPolicy;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.SolsPaymentMethods;
import uk.gov.hmcts.reform.probate.model.cases.caveat.CaveatData;
import uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFee;
import uk.gov.hmcts.reform.probate.model.cases.caveat.ProbateFeeNotIncludedReason;

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
        caveatData.setDeceasedFullAliasNameList(List.of(fullAliasNameCollectionMember));
        caveatData.setDeceasedDateOfBirth(LocalDate.of(1966, 3, 4));
        caveatData.setDeceasedDateOfDeath(LocalDate.of(2018, 11, 20));
        caveatData.setApplicationSubmittedDate(LocalDate.of(2018, 11, 20));
        caveatData.setDeceasedForenames("deceased forename");
        caveatData.setDeceasedSurname("deceased surname");
        caveatData.setRegistryLocation(RegistryLocation.OXFORD);
        caveatData.setExpiryDate(LocalDate.of(2019, 2, 14));
        caveatData.setPaperForm(false);
        caveatData.setPcqId("1003");
        caveatData.setEvidenceHandled(false);
        caveatData.setMessageContent("some message content");
        return caveatData;
    }

    public static CaveatData createCaveatCaseWithOrganisationInfo() {
        CaveatData caveatData = createCaveatCase();
        caveatData.setApplicantOrganisationPolicy(OrganisationPolicy.builder()
                .organisation(Organisation.builder()
                        .organisationID("orgId")
                        .organisationName("orgName")
                        .build())
                .orgPolicyCaseAssignedRole("orgPolicyCaseAssignedRole")
                .orgPolicyReference("orgPolicyReference")
                .build());
        return caveatData;
    }

    public static CaveatData createCaveatCaseWithCitizenBulkScanData() {
        return setBasicBulkScanData();
    }

    public static CaveatData createCaveatCaseWithSolicitorBulkScanData() {
        CaveatData caveatData = setBasicBulkScanData();
        caveatData.setApplicationType(ApplicationType.SOLICITORS);
        caveatData.setSolsFeeAccountNumber("PBA-123456");
        caveatData.setSolsPaymentMethods(SolsPaymentMethods.FEE_ACCOUNT);
        caveatData.setSolsSolicitorAppReference("APP-123456");
        caveatData.setSolsSolicitorFirmName("solicitor firm");
        caveatData.setSolsSolicitorPhoneNumber("0909 909 9909");
        caveatData.setSolsSolicitorRepresentativeName("representative name");
        caveatData.setCaveatorPhoneNumber("123456789");
        caveatData.setProbateFee(ProbateFee.PROBATE_FEE_NOT_INCLUDED);
        caveatData.setProbateFeeNotIncludedReason(ProbateFeeNotIncludedReason.OTHER);
        caveatData.setHelpWithFeesReference("helpWithFeesReference");
        caveatData.setProbateFeeNotIncludedExplanation("feeNotIncludedExplanation");
        caveatData.setProbateFeeAccountNumber("AccountNumber1");
        caveatData.setProbateFeeAccountReference("AccountReference1");
        caveatData.setDxNumber("123456789");
        caveatData.setPractitionerAcceptsServiceByEmail(true);
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

    private static CaveatData setBasicBulkScanData() {
        CaveatData caveatData = createCaveatCase();
        caveatData.setRegistryLocation(RegistryLocation.CTSC);
        caveatData.setPaperForm(Boolean.TRUE);
        CollectionMember<ScannedDocument> scannedDocumentMember1 = new CollectionMember<>();
        scannedDocumentMember1.setValue(getScannedDocument("1"));
        CollectionMember<ScannedDocument> scannedDocumentMember2 = new CollectionMember<>();
        scannedDocumentMember2.setValue(getScannedDocument("2"));
        caveatData.setScannedDocuments(Arrays.asList(scannedDocumentMember1, scannedDocumentMember2));
        caveatData.setBulkScanCaseReference("123");
        CollectionMember<BulkScanEnvelope> bulkScanEnvelopeCollectionMember = new CollectionMember<>();
        bulkScanEnvelopeCollectionMember.setValue(getBulkScanEnvelope());
        caveatData.setBulkScanEnvelopes(List.of(bulkScanEnvelopeCollectionMember));
        return caveatData;
    }

    private static BulkScanEnvelope getBulkScanEnvelope() {
        return BulkScanEnvelope.builder().id("1")
                .action("Action")
                .build();
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
