package uk.gov.hmcts.reform.probate.model.cases.validation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


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
        grantOfRepresentation.setRelationshipToDeceased(Relationship.ADOPTED_CHILD);
        grantOfRepresentation.setSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        grantOfRepresentation.setAdoptionInEnglandOrWales(Boolean.FALSE);
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
        grantOfRepresentation.setRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentation.setSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
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
        grantOfRepresentation.setRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentation.setSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
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
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.JUDICIALLY_SEPERATED);
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
        grantOfRepresentation.setAllDeceasedChildrenOverEighteen(Boolean.FALSE);
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
        grantOfRepresentation.setAllDeceasedChildrenOverEighteen(Boolean.TRUE);
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
        grantOfRepresentation.setAllDeceasedChildrenOverEighteen(Boolean.TRUE);
        grantOfRepresentation.setAnyDeceasedChildrenDieBeforeDeceased(Boolean.TRUE);
        grantOfRepresentation.setAllDeceasedChildrenOverEighteen(Boolean.TRUE);
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
        grantOfRepresentation.setDeceasedMaritalStatus(MaritalStatus.JUDICIALLY_SEPERATED);
        grantOfRepresentation.setRelationshipToDeceased(Relationship.CHILD);
        // when
        Set<ConstraintViolation<GrantOfRepresentation>> violations = validator.validate(grantOfRepresentation);
        // then
        Assert.assertThat(violations.size(), Matchers.equalTo(3));
    }


    private GrantOfRepresentation buildValidGrantOfRepresentation() {

        LocalDate deceasedDod = LocalDate.of(2018, 9, 12);
        LocalDate deceasedDob = LocalDate.of(1954, 9, 18);

        return GrantOfRepresentation.builder()
            .ihtNetValue(260000L)
            .deceasedAddress(Address.builder().addressLine1("addressLine1").postCode("postcode").build())
            .relationshipToDeceased(Relationship.SPOUSE)
            .deceasedMaritalStatus(MaritalStatus.MARRIED)
            .deceasedAnyOtherNames(Boolean.FALSE)
            .deceasedAnyChildren(Boolean.TRUE)
            .deceasedForenames("John")
            .deceasedSurname("Hughes")
            .deceasedDateOfDeath(deceasedDod)
            .deceasedDateOfBirth(deceasedDob)
            .ihtGrossValue(290000L)
            .build();
    }
}