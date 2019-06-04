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
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.ExecutorApplying;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantType;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class GrantOfRepresentationCreator {

    public static GrantOfRepresentationData createProbateCase() {
        GrantOfRepresentationData grantOfRepresentationData = new GrantOfRepresentationData();
        grantOfRepresentationData.setApplicationType(ApplicationType.PERSONAL);
        grantOfRepresentationData.setGrantType(GrantType.GRANT_OF_PROBATE);
        createPrimaryApplicantDetails(grantOfRepresentationData);

        createDeceasedDetails(grantOfRepresentationData);

        createAliasDetails(grantOfRepresentationData);

        grantOfRepresentationData.setRegistryLocation(RegistryLocation.BIRMINGHAM);
        grantOfRepresentationData.setDeceasedHasAssetsOutsideUK(true);
        grantOfRepresentationData.setAssetsOverseasNetValue(10050L);
        createIhtDetails(grantOfRepresentationData);

        grantOfRepresentationData.setExtraCopiesOfGrant(5L);
        grantOfRepresentationData.setOutsideUkGrantCopies(6L);

        final CollectionMember<CasePayment> paymentCollectionMember = new CollectionMember<>();
        CasePayment payment = new CasePayment();
        payment.setStatus(PaymentStatus.SUCCESS);

        createPaymentDetails(grantOfRepresentationData, paymentCollectionMember, payment);
        grantOfRepresentationData.setUploadDocumentUrl("http://document-management/document/12345");

        createLegacyDetails(grantOfRepresentationData);

        createSolicitorDetails(grantOfRepresentationData);


        return grantOfRepresentationData;
    }

    public static void addExecutorApplying(GrantOfRepresentationData grantOfRepresentationData,
                                           String applyingExecutorEmail) {
        if (grantOfRepresentationData.getExecutorsApplying() == null) {
            grantOfRepresentationData.setExecutorsApplying(new ArrayList<>());
        }
        ExecutorApplying executorApplying = new ExecutorApplying();
        executorApplying.setApplyingExecutorEmail(applyingExecutorEmail);
        grantOfRepresentationData.getExecutorsApplying().add(
            new CollectionMember<ExecutorApplying>("1", executorApplying));
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

    private static void createIhtDetails(GrantOfRepresentationData grantOfRepresentationData) {
        grantOfRepresentationData.setIhtFormId(IhtFormType.IHT205);
        grantOfRepresentationData.setIhtFormCompletedOnline(true);
        grantOfRepresentationData.setIhtGrossValue(100000L);
        grantOfRepresentationData.setIhtNetValue(100000L);
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
        grantOfRepresentationData.setDeceasedAddress(deceasedAddress);
        grantOfRepresentationData.setDeceasedFreeTextAddress("Winterfell, Westeros");
        grantOfRepresentationData.setDeceasedAddressFound(true);
        grantOfRepresentationData.setDeceasedAnyOtherNames(true);
    }

    private static void createPrimaryApplicantDetails(GrantOfRepresentationData grantOfRepresentationData) {
        grantOfRepresentationData.setPrimaryApplicantEmailAddress("jon.snow@thenorth.com");
        grantOfRepresentationData.setPrimaryApplicantForenames("Jon");
        grantOfRepresentationData.setPrimaryApplicantSurname("Snow");
        Address primaryApplicantAddress = new Address();
        primaryApplicantAddress.setAddressLine1("Pret a Manger St. Georges Hospital Blackshaw Road London SW17 0QT");
        grantOfRepresentationData.setPrimaryApplicantAddress(primaryApplicantAddress);
        grantOfRepresentationData.setPrimaryApplicantAddressFound(true);
        grantOfRepresentationData
            .setPrimaryApplicantFreeTextAddress("Pret a Manger St. Georges Hospital Blackshaw Road");
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

        grantOfRepresentationData.setDeceasedMartialStatus(MaritalStatus.MARRIED);
        grantOfRepresentationData.setDeceasedDivorcedInEnglandOrWales(false);
        grantOfRepresentationData.setDeceasedOtherChildren(true);
        grantOfRepresentationData.setChildrenDied(false);
        grantOfRepresentationData.setGrandChildrenSurvivedUnderEighteen(false);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(true);
        grantOfRepresentationData.setDeceasedAnyChildren(false);
        grantOfRepresentationData.setDeceasedAnyOtherNames(false);

        grantOfRepresentationData.setRegistryLocation(RegistryLocation.BIRMINGHAM);
        grantOfRepresentationData.setDeceasedHasAssetsOutsideUK(true);
        grantOfRepresentationData.setAssetsOverseasNetValue(10050L);

        createIhtDetails(grantOfRepresentationData);

        grantOfRepresentationData.setDeclarationCheckbox(true);

        grantOfRepresentationData.setExtraCopiesOfGrant(5L);
        grantOfRepresentationData.setOutsideUkGrantCopies(6L);

        final CollectionMember<CasePayment> paymentCollectionMember = new CollectionMember<>();
        CasePayment payment = new CasePayment();
        payment.setStatus(PaymentStatus.SUCCESS);

        createPaymentDetails(grantOfRepresentationData, paymentCollectionMember, payment);
        grantOfRepresentationData.setUploadDocumentUrl("http://document-management/document/12345");

        createLegacyDetails(grantOfRepresentationData);

        createSolicitorDetails(grantOfRepresentationData);

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

    private GrantOfRepresentationCreator() {
    }
}
