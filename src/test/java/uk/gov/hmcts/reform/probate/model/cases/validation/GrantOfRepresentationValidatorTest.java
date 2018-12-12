package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;


public class GrantOfRepresentationValidatorTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

    }

    @Test
    public void shouldProduceNoContraintViolationsForValidGrantOfRepresentation() throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(0));

    }

    @Test
    public void shouldRaiseContraintViolationWhenRelationshipToDeceasedIsAdoptedChildAndDeceasedOtherChildrenIsNull()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.ADOPTED_CHILD);
        grantOfRepresentation.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        grantOfRepresentation.setPrimaryApplicantAdoptionInEnglandOrWales(Boolean.FALSE);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        ConstraintViolation<GrantOfRepresentation> constraintViolation = violations.iterator().next();
        Assert.assertThat(constraintViolation.getMessage(), Matchers.equalTo("NotNullIfFieldHasEitherValue"));
    }

    @Test
    public void shouldRaiseContraintViolationWhenRelationshipToDeceasedIsChildAndDeceasedOtherChildrenIsNull()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentation.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        Assert.assertThat(violations.iterator().next().getMessage(),
                Matchers.equalTo("NotNullIfFieldHasEitherValue"));
    }

    @Test
    public void shouldNotRaiseContraintViolationWhenRelationshipToDeceasedIsChildAndDeceasedOtherChildrenIsPopulated()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentation.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.FALSE);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(0));
    }

    @Test
    public void shouldRaiseContraintViolationWhenDeceasedMaritalStatusIsDivorcedAndDivorcedInEnglandOrWalesIsNull()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.DIVORCED);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        Assert.assertThat(violations.iterator().next().getMessage(),
                Matchers.equalTo("NotNullIfFieldHasEitherValue"));
    }

    @Test
    public void shouldRaiseContraintViolationWhenDeceasedMaritalStatusIsSeperatedAndDivorcedInEnglandOrWalesIsNull()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.JUDICIALLY_SEPARATED);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        Assert.assertThat(violations.iterator().next().getMessage(),
                Matchers.equalTo("NotNullIfFieldHasEitherValue"));
    }

    @Test
    public void shouldRaiseContraintViolationWhenDeceasedHasOtherChildrenAnAllDeceasedChildrenOverEighteenIsNull()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        Assert.assertThat(violations.iterator().next().getMessage(),
                Matchers.equalTo("NotNullIfFieldHasValueValidator"));
    }

    @Test
    public void shouldNotRaiseContraintViolationWhenDeceasedHasOtherChildrenAllDeceasedChildrenOverEighteenIsPopulated()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.FALSE);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(0));
    }

    @Test
    public void shouldFailDeceasedHasOtherChildrenAllDeceasedChildrenOverEighteenAnyDeceasedChildrenDieBeforeDeceased()
            throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.TRUE);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        Assert.assertThat(violations.iterator().next().getMessage(),
                Matchers.equalTo("NotNullIfAllFieldsHaveValueValidator"));
    }

    @Test
    public void shouldFailWhenDeceasedHasOtherChildrenAndDeceasedGrandchildrenUnderEighteenIsNull() throws Exception {
        // given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAnyDeceasedChildrenDieBeforeDeceased(Boolean.TRUE);
        grantOfRepresentation.setDeceasedAllDeceasedChildrenOverEighteen(Boolean.TRUE);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        Assert.assertThat(violations.iterator().next().getMessage(),
                Matchers.equalTo("NotNullIfAllFieldsHaveValueValidator"));
    }


    @Test
    public void shouldRaiseContraintViolationWhenAssetsOverseasNotPopulatedAndIhtNetValueLessThanOrEqualTo250000()
            throws Exception {
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setIhtNetValue(250000L);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(1));
        Assert.assertThat(violations.iterator().next().getMessage(),
                Matchers.equalTo("AssetsOverseasMandatory"));
    }

    @Test
    public void shouldNotRaiseContraintViolationWhenAssetsOverseasNotPopulatedAndIhtNetValueMoreThan250000()
            throws Exception {
        //given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setIhtNetValue(250001L);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(0));
    }

    @Test
    public void shouldRaiseMultipleContraintViolations() throws Exception {
        //given
        GrantOfRepresentation grantOfRepresentation = buildValidGrantOfRepresentation();
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.JUDICIALLY_SEPARATED);
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(3));
    }


    private GrantOfRepresentation buildValidGrantOfRepresentation() {

        LocalDate deceasedDod = LocalDate.of(2018, 9, 12);
        LocalDate deceasedDob = LocalDate.of(1954, 9, 18);

        GrantOfRepresentation grantOfRepresentation = new GrantOfRepresentation();
        grantOfRepresentation.setIhtNetValue(260000L);
        Address address = new Address();
        address.setAddressLine1("addressLine1");
        address.setPostCode("postcode");
        grantOfRepresentation.setDeceasedAddress(address);
        grantOfRepresentation.setPrimaryApplicantRelationshipToDeceased(Relationship.SPOUSE);
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.MARRIED);
        grantOfRepresentation.setDeceasedOtherNames(Boolean.FALSE);
        grantOfRepresentation.setDeceasedAnyChildren(Boolean.TRUE);
        grantOfRepresentation.setDeceasedForenames("John");
        grantOfRepresentation.setDeceasedSurname("Hughes");
        grantOfRepresentation.setDeceasedDateOfDeath(deceasedDod);
        grantOfRepresentation.setDeceasedDateOfBirth(deceasedDob);
        grantOfRepresentation.setIhtGrossValue(290000L);
        return grantOfRepresentation;
    }
}