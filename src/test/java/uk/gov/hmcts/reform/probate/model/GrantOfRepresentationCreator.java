package uk.gov.hmcts.reform.probate.model;

import com.google.common.collect.Lists;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CasePayment;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.SolsAliasName;
import uk.gov.hmcts.reform.probate.model.cases.SolsPaymentMethods;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.Declaration;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.ExecutorApplying;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.ExecutorNotApplying;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.ExecutorNotApplyingReason;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantType;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.LegalStatement;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.LegalStatementExecutorApplying;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GrantOfRepresentationCreator {

    public static GrantOfRepresentationData createProbateCase() {
        GrantOfRepresentationData grantOfRepresentationData = new GrantOfRepresentationData();
        grantOfRepresentationData.setApplicationType(ApplicationType.PERSONAL);
        grantOfRepresentationData.setGrantType(GrantType.GRANT_OF_PROBATE);
        createPrimaryApplicantDetails(grantOfRepresentationData);

        createDeceasedDetails(grantOfRepresentationData);

        createAliasDetails(grantOfRepresentationData);

        grantOfRepresentationData.setWillHasCodicils(false);
        grantOfRepresentationData.setWillNumberOfCodicils(0L);
        grantOfRepresentationData.setNumberOfApplicants(1L);
        grantOfRepresentationData.setNumberOfExecutors(1L);
        grantOfRepresentationData.setDeclarationCheckbox(true);
        grantOfRepresentationData.setRegistryLocation(RegistryLocation.BIRMINGHAM);
        grantOfRepresentationData.setDeceasedHasAssetsOutsideUK(true);
        grantOfRepresentationData.setAssetsOutsideNetValue(10050L);
        createIhtDetails(grantOfRepresentationData);

        grantOfRepresentationData.setExtraCopiesOfGrant(5L);
        grantOfRepresentationData.setOutsideUkGrantCopies(6L);

        final CollectionMember<CasePayment> paymentCollectionMember = new CollectionMember<>();
        CasePayment payment = new CasePayment();
        payment.setStatus(PaymentStatus.SUCCESS);

        createPaymentDetails(grantOfRepresentationData, paymentCollectionMember, payment);

        createLegacyDetails(grantOfRepresentationData);

        createSolicitorDetails(grantOfRepresentationData);

        grantOfRepresentationData.setPaperForm(false);

        grantOfRepresentationData.setApplicationSubmittedDate(LocalDate.now());
        grantOfRepresentationData.setLegalStatement(LegalStatement.builder()
            .intro("This statement is based on the information you&rsquo;ve given in your application. "
                + "It will be stored as a public record.")
            .applicant("I, Sansa Stark of Ronald McDonald House St. Georges Hospital Blackshaw Road London "
                + "SW17 0QT United Kingdom, make the following statement:")
            .deceased("Rob Stark was born on 1 January 1900 and died on 1 January 2019, "
                + "domiciled in England and Wales.")
            .deceasedOtherNames("")
            .deceasedEstateValue("The gross value for the estate amounts to £20000.00 "
                + "and the net value for the estate amounts to £20000.00.")
            .deceasedEstateLand("To the best of my knowledge, information and belief, "
                + "there was no land vested in Rob Stark which was settled previously to the death "
                + "(and not by the will) of Rob Stark and which remained "
                + "settled land notwithstanding such death.")
            .executorsApplying(
                Lists.newArrayList(
                    CollectionMember.<LegalStatementExecutorApplying>builder()
                        .value(LegalStatementExecutorApplying.builder()
                            .name("I am an executor named in the will as Sansa Stark, "
                                + "and I am applying for probate.")
                            .sign("I will send to the probate registry what I believe to be the "
                                + "true "
                                + "and original last will and testament of Rob Stark.")
                            .build())
                        .build()
                ))
            .build());
        grantOfRepresentationData.setDeclaration(Declaration.builder()
            .accept("I confirm that I will administer the estate of the person who died according to law, "
                + "and that my application is truthful.")
            .confirm("I confirm that I will administer the estate of Rob Stark, according to law. I will:")
            .requests("If the probate registry (court) asks me to do so, I will:")
            .confirmItem1("I understand that:")
            .confirmItem2("keep full details (an inventory) of the estate")
            .confirmItem3("keep a full account of how the estate has been administered")
            .requestsItem1("provide the full details of the estate and how it has been administered")
            .requestsItem2("return the grant of probate to the court")
            .understandItem1("my application will be rejected if I do not answer any questions "
                + "about the information I have given")
            .understandItem2("criminal proceedings for fraud may be brought against me if I am found "
                + "to have been deliberately untruthful or dishonest")
            .build());
        return grantOfRepresentationData;
    }

    public static void addExecutorApplying(GrantOfRepresentationData grantOfRepresentationData,
                                           String applyingExecutorEmail, String applyingExecutorName,
                                           Boolean isApplicant) {
        if (grantOfRepresentationData.getExecutorsApplying() == null) {
            grantOfRepresentationData.setExecutorsApplying(new ArrayList<>());
        }
        ExecutorApplying executorApplying = new ExecutorApplying();
        executorApplying.setApplyingExecutorEmail(applyingExecutorEmail);
        executorApplying.setApplyingExecutorName(applyingExecutorName);
        executorApplying.setApplyingExecutorApplicant(isApplicant);
        grantOfRepresentationData.getExecutorsApplying().add(
            new CollectionMember<ExecutorApplying>("1", executorApplying));
    }

    public static void addExecutorApplying(GrantOfRepresentationData grantOfRepresentationData,
                                           String applyingExecutorEmail, String applyingExecutorName) {
        if (grantOfRepresentationData.getExecutorsApplying() == null) {
            grantOfRepresentationData.setExecutorsApplying(new ArrayList<>());
        }
        ExecutorApplying executorApplying = new ExecutorApplying();
        executorApplying.setApplyingExecutorEmail(applyingExecutorEmail);
        executorApplying.setApplyingExecutorName(applyingExecutorName);
        grantOfRepresentationData.getExecutorsApplying().add(
            new CollectionMember<ExecutorApplying>("1", executorApplying));
    }

    public static void addExecutorNotApplying(GrantOfRepresentationData grantOfRepresentationData,
                                              String notApplyingExecutorName,
                                              ExecutorNotApplyingReason notApplyingExecutorReason) {
        if (grantOfRepresentationData.getExecutorsNotApplying() == null) {
            grantOfRepresentationData.setExecutorsNotApplying(new ArrayList<>());
        }
        ExecutorNotApplying executorNotApplying = new ExecutorNotApplying();
        executorNotApplying.setNotApplyingExecutorName(notApplyingExecutorName);
        executorNotApplying.setNotApplyingExecutorReason(notApplyingExecutorReason);
        grantOfRepresentationData.getExecutorsNotApplying().add(
            new CollectionMember<ExecutorNotApplying>("1", executorNotApplying));
    }

    public static void addAdoptiveRelative(GrantOfRepresentationData grantOfRepresentationData,
                                           String adoptedName, String relationship, InOut adoptedInOut) {
        if (grantOfRepresentationData.getAdoptiveRelatives() == null) {
            grantOfRepresentationData.setAdoptiveRelatives(new ArrayList<>());
        }
        AdoptiveRelative adoptiveRelative = AdoptiveRelative.builder()
            .adoptedInOrOut(adoptedInOut).name(adoptedName).relationship(relationship).build();
        grantOfRepresentationData.getAdoptiveRelatives().add(
            new CollectionMember<AdoptiveRelative>("1", adoptiveRelative));
    }

    private static void createSolicitorDetails(GrantOfRepresentationData grantOfRepresentationData) {
        Address solicitorAddress = Address.builder()
            .addressLine1("Sol Address Line 1")
            .addressLine2("Sol Address Line 2")
            .addressLine3("Sol Address Line 3")
            .country("UK")
            .county("Middlesex")
            .postCode("HA1 4ET")
            .postTown("Harrow")
            .build();
        grantOfRepresentationData.setSolsSolicitorAddress(solicitorAddress);
        grantOfRepresentationData.setSolsSolicitorAppReference("Solicitor Application Reference");
        grantOfRepresentationData.setSolsSolicitorFirmName("Solicitor Firm Name");
    }

    private static void createAttorneyObBehalfOfDetails(GrantOfRepresentationData grantOfRepresentationData) {
        Address attorneyOnBehalfOfAddress = Address.builder()
            .addressLine1("Attorney Address Line 1")
            .addressLine2("Attorney Address Line 2")
            .addressLine3("Attorney Address Line 3")
            .country("UK")
            .county("Middlesex")
            .postCode("HA1 4ET")
            .postTown("Harrow")
            .build();
        final CollectionMember<AttorneyNamesAndAddress> attorneyNameAndAddressMember = new CollectionMember<>();
        AttorneyNamesAndAddress attorneyNameAndAddress = AttorneyNamesAndAddress.builder()
            .address(attorneyOnBehalfOfAddress)
            .name("1st Attorney Harrow")
            .build();
        attorneyNameAndAddressMember.setValue(attorneyNameAndAddress);
        grantOfRepresentationData.setAttorneyOnBehalfOfNameAndAddress((List<CollectionMember<AttorneyNamesAndAddress>>)
            Arrays.asList(attorneyNameAndAddressMember));
        grantOfRepresentationData.setApplyingAsAnAttorney(Boolean.TRUE);
    }

    private static void createIhtDetails(GrantOfRepresentationData grantOfRepresentationData) {
        grantOfRepresentationData.setIhtFormId(IhtFormType.optionIHT205);
        grantOfRepresentationData.setIhtFormCompletedOnline(true);
        grantOfRepresentationData.setIhtGrossValue(100000L);
        grantOfRepresentationData.setIhtNetValue(100000L);
        grantOfRepresentationData.setIhtNetValueField("100000");
        grantOfRepresentationData.setIhtGrossValueField("100000");
        grantOfRepresentationData.setIhtReferenceNumber("GOT123456");
    }

    private static void createDeceasedDetails(GrantOfRepresentationData grantOfRepresentationData) {
        grantOfRepresentationData.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        grantOfRepresentationData.setDeceasedSurname("Stark");
        grantOfRepresentationData.setDeceasedForenames("Ned");
        grantOfRepresentationData.setDeceasedDateOfBirth(LocalDate.of(1930, 1, 1));
        grantOfRepresentationData.setDeceasedDateOfDeath(LocalDate.of(2018, 1, 1));
        Address deceasedAddress = new Address();
        deceasedAddress.setAddressLine1("Winterfell, Westeros");
        deceasedAddress.setPostCode("SW17 TYH");
        grantOfRepresentationData.setDeceasedAddress(deceasedAddress);
        grantOfRepresentationData.setDeceasedAnyOtherNames(true);
    }

    private static void createPrimaryApplicantDetails(GrantOfRepresentationData grantOfRepresentationData) {
        grantOfRepresentationData.setPrimaryApplicantEmailAddress("jon.snow@thenorth.com");
        grantOfRepresentationData.setPrimaryApplicantForenames("Jon");
        grantOfRepresentationData.setPrimaryApplicantSurname("Snow");
        Address primaryApplicantAddress = new Address();
        primaryApplicantAddress.setAddressLine1("Pret a Manger St. Georges Hospital Blackshaw Road London SW17 0QT");
        primaryApplicantAddress.setPostCode("SW17 0QT");
        grantOfRepresentationData.setPrimaryApplicantAddress(primaryApplicantAddress);
        grantOfRepresentationData.setPrimaryApplicantPhoneNumber("123455678");
        grantOfRepresentationData.setPrimaryApplicantRelationshipToDeceased(Relationship.ADOPTED_CHILD);
        grantOfRepresentationData.setPrimaryApplicantAdoptionInEnglandOrWales(true);
    }


    public static GrantOfRepresentationData createIntestacyCase() {
        GrantOfRepresentationData grantOfRepresentationData = new GrantOfRepresentationData();
        grantOfRepresentationData.setApplicationType(ApplicationType.PERSONAL);
        grantOfRepresentationData.setGrantType(GrantType.INTESTACY);

        createPrimaryApplicantDetails(grantOfRepresentationData);

        createDeceasedDetails(grantOfRepresentationData);

        createAliasDetails(grantOfRepresentationData);

        grantOfRepresentationData.setDeceasedMaritalStatus(MaritalStatus.MARRIED);
        grantOfRepresentationData.setDeceasedDivorcedInEnglandOrWales(false);
        grantOfRepresentationData.setDeceasedOtherChildren(true);
        grantOfRepresentationData.setChildrenDied(false);
        grantOfRepresentationData.setGrandChildrenSurvivedUnderEighteen(false);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(true);
        grantOfRepresentationData.setChildrenUnderEighteenSurvivedText("2");
        grantOfRepresentationData.setDeceasedAnyChildren(false);
        grantOfRepresentationData.setDeceasedAnyOtherNames(false);
        grantOfRepresentationData.setRegistryLocation(RegistryLocation.BIRMINGHAM);
        grantOfRepresentationData.setDeceasedHasAssetsOutsideUK(true);
        grantOfRepresentationData.setAssetsOutsideNetValue(10050L);

        createIhtDetails(grantOfRepresentationData);

        grantOfRepresentationData.setDeclarationCheckbox(true);

        grantOfRepresentationData.setExtraCopiesOfGrant(5L);
        grantOfRepresentationData.setOutsideUkGrantCopies(6L);

        final CollectionMember<CasePayment> paymentCollectionMember = new CollectionMember<>();
        CasePayment payment = new CasePayment();
        payment.setStatus(PaymentStatus.SUCCESS);

        createPaymentDetails(grantOfRepresentationData, paymentCollectionMember, payment);

        createLegacyDetails(grantOfRepresentationData);

        createSolicitorDetails(grantOfRepresentationData);

        createAttorneyObBehalfOfDetails(grantOfRepresentationData);

        grantOfRepresentationData.setPaperForm(false);

        grantOfRepresentationData.setPcqId("1004");

        return grantOfRepresentationData;
    }

    public static GrantOfRepresentationData createCitizenIntestacyCaseWithBulkScanData() {
        GrantOfRepresentationData grantOfRepresentationData = setBasicBulkScanData();
        grantOfRepresentationData.setApplicationType(ApplicationType.PERSONAL);
        grantOfRepresentationData.setSolsSolicitorAddress(null);
        grantOfRepresentationData.setSolsSolicitorAppReference(null);
        grantOfRepresentationData.setSolsSolicitorFirmName(null);
        return grantOfRepresentationData;
    }

    public static GrantOfRepresentationData createSolicitorIntestacyCaseWithBulkScanData() {
        GrantOfRepresentationData grantOfRepresentationData = setBasicBulkScanData();
        grantOfRepresentationData.setApplicationType(ApplicationType.SOLICITORS);
        grantOfRepresentationData.setSolsFeeAccountNumber("PBA-123456");
        grantOfRepresentationData.setSolsPaymentMethods(SolsPaymentMethods.FEE_ACCOUNT);
        grantOfRepresentationData.setSolsSolicitorPhoneNumber("0909 909 9909");
        return grantOfRepresentationData;
    }

    private static GrantOfRepresentationData setBasicBulkScanData() {
        GrantOfRepresentationData grantOfRepresentationData = createIntestacyCase();
        CollectionMember<ScannedDocument> scannedDocumentMember1 = new CollectionMember<>();
        scannedDocumentMember1.setValue(getScannedDocument("1"));
        CollectionMember<ScannedDocument> scannedDocumentMember2 = new CollectionMember<>();
        scannedDocumentMember2.setValue(getScannedDocument("2"));
        grantOfRepresentationData.setScannedDocuments((List<CollectionMember<ScannedDocument>>)
            Arrays.asList(scannedDocumentMember1, scannedDocumentMember2));
        addExecutorNotApplying(grantOfRepresentationData, "Bob Dylan", ExecutorNotApplyingReason.MENTALLY_INCAPABLE);
        addExecutorNotApplying(grantOfRepresentationData, "Peter Smith", ExecutorNotApplyingReason.POWER_RESERVED);
        addAdoptiveRelative(grantOfRepresentationData, "Bob Taylor", "Cousin", InOut.OUT);
        addAdoptiveRelative(grantOfRepresentationData, "Mark Ronson", "Cousin", InOut.IN);
        grantOfRepresentationData.setRegistryLocation(RegistryLocation.CTSC);
        grantOfRepresentationData.setPaperForm(Boolean.TRUE);
        grantOfRepresentationData.setBulkScanCaseReference("123");
        grantOfRepresentationData.setAdopted(true);
        grantOfRepresentationData.setHalfBloodNeicesAndNephews(true);
        grantOfRepresentationData.setHalfBloodNeicesAndNephewsOverEighteen("1");
        grantOfRepresentationData.setNotifiedApplicants(false);
        grantOfRepresentationData.setPrimaryApplicantAdoptionInEnglandOrWales(false);
        grantOfRepresentationData.setChildrenDied(true);
        grantOfRepresentationData.setChildrenDiedOverEighteenText("1");
        grantOfRepresentationData.setChildrenOverEighteenSurvivedText("2");
        grantOfRepresentationData.setGrandChildrenSurvived(true);
        grantOfRepresentationData.setAllDeceasedChildrenOverEighteen(true);
        grantOfRepresentationData.setAnyDeceasedChildrenDieBeforeDeceased(false);
        grantOfRepresentationData.setAnyDeceasedGrandChildrenUnderEighteen(true);
        return grantOfRepresentationData;
    }

    private static void createLegacyDetails(GrantOfRepresentationData grantOfRepresentationData) {
        grantOfRepresentationData.setLegacyId("123456");
        grantOfRepresentationData.setLegacyType("Legacy LEGACY GRANT");
        grantOfRepresentationData.setLegacyCaseViewUrl("http://locahost:8080/cases/1");
    }

    private static void createPaymentDetails(GrantOfRepresentationData grantOfRepresentationData,
                                             CollectionMember<CasePayment> paymentCollectionMember,
                                             CasePayment payment) {
        Date date = Date.from(LocalDate.of(2018, 12, 3)
            .atStartOfDay(ZoneId.systemDefault()).toInstant());
        payment.setDate(date);
        payment.setReference("RC-1537-1988-5489-1985");
        payment.setAmount(22050L);
        payment.setMethod("online");
        payment.setTransactionId("r23k178busa0rp2mh27m0vchja");
        payment.setSiteId("P223");
        paymentCollectionMember.setValue(payment);
        grantOfRepresentationData.setPayments(Lists.newArrayList(paymentCollectionMember));

    }

    private static void createAliasDetails(GrantOfRepresentationData grantOfRepresentationData) {
        CollectionMember<AliasName> aliasNameCollectionMember = new CollectionMember<>();
        AliasName aliasName = new AliasName();
        aliasName.setForenames("King");
        aliasName.setLastName("North");
        aliasNameCollectionMember.setValue(aliasName);
        grantOfRepresentationData.setDeceasedAliasNameList(Lists.newArrayList(aliasNameCollectionMember));

        CollectionMember<SolsAliasName> solsAliasNameCollectionMember = new CollectionMember<>();
        SolsAliasName fullAliasName = new SolsAliasName();
        fullAliasName.setSolsAliasname("King North");
        solsAliasNameCollectionMember.setValue(fullAliasName);
        grantOfRepresentationData.setSolsDeceasedAliasNamesList(Lists.newArrayList(solsAliasNameCollectionMember));

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
            .subtype("PA1A")
            .scannedDate(dateTime)
            .exceptionRecordReference(null)
            .deliveryDate(dateTime)
            .url(url)
            .build();
    }

    private GrantOfRepresentationCreator() {
    }
}
