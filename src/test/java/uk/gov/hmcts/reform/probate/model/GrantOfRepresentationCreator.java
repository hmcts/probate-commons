package uk.gov.hmcts.reform.probate.model;

import com.google.common.collect.Lists;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.CaseType;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.Payment;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.Declaration;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

import java.time.LocalDate;

public class GrantOfRepresentationCreator {

    public static GrantOfRepresentation createIntestacyCase() {
        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setType(CaseType.GRANT_OF_REPRESENTATION);
        grantOfRepresentation.setApplicationType(ProbateType.INTESTACY);
        grantOfRepresentation.setPrimaryApplicantEmailAddress("jon.snow@thenorth.com");
        grantOfRepresentation.setPrimaryApplicantForenames("Jon");
        grantOfRepresentation.setPrimaryApplicantSurname("Snow");
        Address primaryApplicantAddress = new Address();
        primaryApplicantAddress.setAddressLine1("Pret a Manger St. Georges Hospital Blackshaw Road London SW17 0QT");
        grantOfRepresentation.setPrimaryApplicantAddress(primaryApplicantAddress);
        grantOfRepresentation.setPrimaryApplicantAddressFound(true);
        grantOfRepresentation.setPrimaryApplicantFreeTextAddress("Pret a Manger St. Georges Hospital Blackshaw Road");
        grantOfRepresentation.setPrimaryApplicantPhoneNumber("123455678");
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.ADOPTED_CHILD);
        grantOfRepresentation.setPrimaryApplicantAdoptionInEnglandOrWales(true);

        grantOfRepresentation.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        grantOfRepresentation.setDeceasedSurname("Stark");
        grantOfRepresentation.setDeceasedForenames("Ned");
        grantOfRepresentation.setDeceasedDateOfBirth(LocalDate.of(1930, 1, 1));
        grantOfRepresentation.setDeceasedDateOfDeath(LocalDate.of(2018, 1, 1));
        Address deceasedAddress = new Address();
        deceasedAddress.setAddressLine1("Winterfell, Westeros");
        grantOfRepresentation.setDeceasedAddress(deceasedAddress);
        grantOfRepresentation.setDeceasedFreeTextAddress("Winterfell, Westeros");
        grantOfRepresentation.setDeceasedAddressFound(true);
        grantOfRepresentation.setDeceasedOtherNames(true);
        CollectionMember<AliasName> aliasNameCollectionMember = new CollectionMember<>();
        AliasName aliasName = new AliasName();
        aliasName.setForenames("King");
        aliasName.setLastName("North");
        aliasNameCollectionMember.setValue(aliasName);
        grantOfRepresentation.setDeceasedAliasNameList(Lists.newArrayList(aliasNameCollectionMember));
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.MARRIED);
        grantOfRepresentation.setDeceasedDivorcedInEnglandOrWales(false);
        grantOfRepresentation.setDeceasedOtherChildren(true);
        grantOfRepresentation.setDeceasedAnyDeceasedChildrenDieBeforeDeceased(false);
        grantOfRepresentation.setDeceasedAnyDeceasedGrandchildrenUnderEighteen(false);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(true);
        grantOfRepresentation.setDeceasedAnyChildren(false);
        grantOfRepresentation.setDeceasedOtherNames(false);

        grantOfRepresentation.setRegistryLocation("Birmingham");
        grantOfRepresentation.setAssetsOverseas(true);
        grantOfRepresentation.setAssetsOverseasNetValue(10050L);
        grantOfRepresentation.setIhtForm(IhtFormType.IHT205);
        grantOfRepresentation.setIhtFormCompletedOnline(true);
        grantOfRepresentation.setIhtGrossValue(100000L);
        grantOfRepresentation.setIhtNetValue(100000L);
        grantOfRepresentation.setIhtReferenceNumber("GOT123456");

        Declaration declaration = new Declaration();
        declaration.setDeclarationCheckbox(true);
        grantOfRepresentation.setDeclaration(declaration);

        grantOfRepresentation.setExtraCopiesOfGrant(5L);
        grantOfRepresentation.setOutsideUkGrantCopies(6L);

        final CollectionMember<Payment> paymentCollectionMember = new CollectionMember<>();
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setDate(LocalDate.of(2018, 12, 3));
        payment.setReference("RC-1537-1988-5489-1985");
        payment.setAmount(22050L);
        payment.setMethod("online");
        payment.setTransactionId("r23k178busa0rp2mh27m0vchja");
        payment.setSiteId("P223");
        paymentCollectionMember.setValue(payment);
        grantOfRepresentation.setPayments(Lists.newArrayList(paymentCollectionMember));
        grantOfRepresentation.setUploadDocumentUrl("http://document-management/document/12345");
        return grantOfRepresentation;
    }

    private GrantOfRepresentationCreator() {
    }
}