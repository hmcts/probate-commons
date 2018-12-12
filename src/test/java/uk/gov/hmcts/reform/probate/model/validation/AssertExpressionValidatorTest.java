package uk.gov.hmcts.reform.probate.model.validation;

import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentation;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;

import java.time.LocalDate;
import java.util.Collections;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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

        grantOfRepresentation = GrantOfRepresentationCreator.createIntestacyCase();
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
    public void shouldFailDeceasedHasOtherChildrenAllDeceasedChildrenOverEighteenDeceasedChildrenDieBeforeDeceased() {
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
