package uk.gov.hmcts.reform.probate.model.cases.validation;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.PaymentStatus;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.AliasName;
import uk.gov.hmcts.reform.probate.model.cases.CaseType;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.Payment;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.Declaration;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class AssertExpressionValidatorTest {

    private Validator validator;

    private GrantOfRepresentation grantOfRepresentation;

    private LocalDate afterDate = LocalDate.of(2018, 9, 12);
    private LocalDate beforeDate = LocalDate.of(1954, 9, 18);

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        grantOfRepresentation = new GrantOfRepresentation();
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

        CollectionMember<Payment> paymentCollectionMember = new CollectionMember<>();
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
    }

    @Test
    public void shouldVReturnFalseIfDeceasedHasOtherNamesAndAliasListIsNull() {
        grantOfRepresentation.setDeceasedOtherNames(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAliasNameList(Collections.emptyList());
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldVReturnTrueIfDeceasedHasOtherNamesAndAliasListPopulated() {
        grantOfRepresentation.setDeceasedOtherNames(Boolean.FALSE);
        assertThat(validator.validate(grantOfRepresentation), hasSize(0));
    }

    @Test
    public void shouldFailValidationWhenDodIsBeforeDob() {
        grantOfRepresentation.setDeceasedDateOfBirth(afterDate);
        grantOfRepresentation.setDeceasedDateOfDeath(beforeDate);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenDodIsAfterDob() {
        grantOfRepresentation.setDeceasedDateOfBirth(beforeDate);
        grantOfRepresentation.setDeceasedDateOfDeath(afterDate);
        assertThat(validator.validate(grantOfRepresentation), hasSize(0));
    }

    @Test
    public void shouldPassValidationForValidGrantOfRepresentation() {
        assertThat(validator.validate(grantOfRepresentation), hasSize(0));
    }

    @Test
    public void shouldFailValidationWhenRelationshipToDeceasedIsAdoptedChildAndDeceasedOtherChildrenIsNull() {
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.ADOPTED_CHILD);
        grantOfRepresentation.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        grantOfRepresentation.setPrimaryApplicantAdoptionInEnglandOrWales(Boolean.FALSE);
        grantOfRepresentation.setDeceasedOtherChildren(null);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenRelationshipToDeceasedIsChildAndDeceasedOtherChildrenIsNull() {
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentation.setDeceasedOtherChildren(null);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenRelationshipToDeceasedIsChildAndDeceasedOtherChildrenIsPopulated() {
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        assertThat(validator.validate(grantOfRepresentation), hasSize(0));
    }

    @Test
    public void shouldFailValidationWhenDeceasedMaritalStatusIsDivorcedAndDivorcedInEnglandOrWalesIsNull() {
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.DIVORCED);
        grantOfRepresentation.setDeceasedDivorcedInEnglandOrWales(null);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldFailValidationDeceasedMaritalStatusIsSeparatedAndDivorcedInEnglandOrWalesIsNull() {
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.JUDICIALLY_SEPARATED);
        grantOfRepresentation.setDeceasedDivorcedInEnglandOrWales(null);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));

    }

    @Test
    public void shouldFailValidationWhenDeceasedHasOtherChildrenAnAllDeceasedChildrenOverEighteenIsNull() {
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(null);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldPassValidationWhenDeceasedHasOtherChildrenAllDeceasedChildrenOverEighteenIsPopulated() {
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.FALSE);
        assertThat(validator.validate(grantOfRepresentation), hasSize(0));
    }

    @Test
    public void shouldFailDeceasedHasOtherChildrenAllDeceasedChildrenOverEighteenAnyDeceasedChildrenDieBeforeDeceased()
    {
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAnyDeceasedChildrenDieBeforeDeceased(null);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldFailWhenDeceasedHasOtherChildrenAndDeceasedGrandchildrenUnderEighteenIsNull() {
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAnyDeceasedChildrenDieBeforeDeceased(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAnyDeceasedGrandchildrenUnderEighteen(null);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenAssetsOverseasNotPopulatedAndIhtNetValueLessThanOrEqualTo250000() {
        grantOfRepresentation.setIhtNetValue(250000L);
        assertThat(validator.validate(grantOfRepresentation), hasSize(1));
    }

    @Test
    public void shouldPassValidationWhenAssetsOverseasNotPopulatedAndIhtNetValueMoreThan250000() {
        grantOfRepresentation.setIhtNetValue(250001L);
        grantOfRepresentation.setIhtGrossValue(250002L);
        assertThat(validator.validate(grantOfRepresentation), hasSize(0));
    }

    @Test
    public void shouldRaiseMultipleConstraintViolations() {
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.JUDICIALLY_SEPARATED);
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(null);
        grantOfRepresentation.setDeceasedDateOfBirth(afterDate);
        grantOfRepresentation.setDeceasedDateOfDeath(beforeDate);
        
        assertThat(validator.validate(grantOfRepresentation), hasSize(3));
    }
}
