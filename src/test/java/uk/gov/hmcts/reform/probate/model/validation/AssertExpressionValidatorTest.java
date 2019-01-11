package uk.gov.hmcts.reform.probate.model.validation;

import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.GrantOfRepresentationData;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;
import uk.gov.hmcts.reform.probate.model.validation.groups.SubmissionGroup;

import java.time.LocalDate;
import java.util.Collections;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class AssertExpressionValidatorTest {

    private Validator validator;

    private GrantOfRepresentationData grantOfRepresentationData;

    private LocalDate afterDate = LocalDate.of(2018, 9, 12);
    private LocalDate beforeDate = LocalDate.of(1954, 9, 18);

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        grantOfRepresentationData = GrantOfRepresentationCreator.createIntestacyCase();
    }

    @Test
    public void shouldVReturnFalseIfDeceasedHasOtherNamesAndAliasListIsNull() {
        grantOfRepresentationData.setDeceasedAnyOtherNames(Boolean.TRUE);
        grantOfRepresentationData.setDeceasedAliasNameList(Collections.emptyList());
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldVReturnTrueIfDeceasedHasOtherNamesAndAliasListPopulated() {
        grantOfRepresentationData.setDeceasedAnyOtherNames(Boolean.FALSE);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(0));
    }

    @Test
    public void shouldFailValidationWhenDodIsBeforeDob() {
        grantOfRepresentationData.setDeceasedDateOfBirth(afterDate);
        grantOfRepresentationData.setDeceasedDateOfDeath(beforeDate);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenDodIsAfterDob() {
        grantOfRepresentationData.setDeceasedDateOfBirth(beforeDate);
        grantOfRepresentationData.setDeceasedDateOfDeath(afterDate);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(0));
    }

    @Test
    public void shouldPassValidationForValidGrantOfRepresentation() {
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(0));
    }

    @Test
    public void shouldFailValidationWhenRelationshipToDeceasedIsAdoptedChildAndDeceasedOtherChildrenIsNull() {
        grantOfRepresentationData.setPrimaryApplicantRelationshipToDeceased(Relationship.ADOPTED_CHILD);
        grantOfRepresentationData.setDeceasedSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        grantOfRepresentationData.setPrimaryApplicantAdoptionInEnglandOrWales(Boolean.FALSE);
        grantOfRepresentationData.setDeceasedOtherChildren(null);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenRelationshipToDeceasedIsChildAndDeceasedOtherChildrenIsNull() {
        grantOfRepresentationData.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentationData.setDeceasedOtherChildren(null);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenRelationshipToDeceasedIsChildAndDeceasedOtherChildrenIsPopulated() {
        grantOfRepresentationData.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentationData.setDeceasedOtherChildren(Boolean.TRUE);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(0));
    }

    @Test
    public void shouldFailValidationWhenDeceasedMaritalStatusIsDivorcedAndDivorcedInEnglandOrWalesIsNull() {
        grantOfRepresentationData.setDeceasedMartialStatus(MaritalStatus.DIVORCED);
        grantOfRepresentationData.setDeceasedDivorcedInEnglandOrWales(null);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldFailValidationDeceasedMaritalStatusIsSeparatedAndDivorcedInEnglandOrWalesIsNull() {
        grantOfRepresentationData.setDeceasedMartialStatus(MaritalStatus.JUDICIALLY_SEPARATED);
        grantOfRepresentationData.setDeceasedDivorcedInEnglandOrWales(null);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));

    }

    @Test
    public void shouldFailValidationWhenDeceasedHasOtherChildrenAnAllDeceasedChildrenOverEighteenIsNull() {
        grantOfRepresentationData.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(null);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldPassValidationWhenDeceasedHasOtherChildrenAllDeceasedChildrenOverEighteenIsPopulated() {
        grantOfRepresentationData.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(Boolean.FALSE);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(0));
    }

    @Test
    public void shouldFailDeceasedHasOtherChildrenAllDeceasedChildrenOverEighteenDeceasedChildrenDieBeforeDeceased() {
        grantOfRepresentationData.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(Boolean.TRUE);
        grantOfRepresentationData.setChildrenDied(null);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldFailWhenDeceasedHasOtherChildrenAndDeceasedGrandchildrenUnderEighteenIsNull() {
        grantOfRepresentationData.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(Boolean.TRUE);
        grantOfRepresentationData.setChildrenDied(Boolean.TRUE);
        grantOfRepresentationData.setGrandChildrenSurvivedUnderEighteen(null);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldFailValidationWhenAssetsOverseasNotPopulatedAndIhtNetValueLessThanOrEqualTo250000() {
        grantOfRepresentationData.setIhtNetValue(250000L);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(1));
    }

    @Test
    public void shouldPassValidationWhenAssetsOverseasNotPopulatedAndIhtNetValueMoreThan250000() {
        grantOfRepresentationData.setIhtNetValue(250001L);
        grantOfRepresentationData.setIhtGrossValue(250002L);
        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(0));
    }

    @Test
    public void shouldRaiseMultipleConstraintViolations() {
        grantOfRepresentationData.setDeceasedMartialStatus(MaritalStatus.JUDICIALLY_SEPARATED);
        grantOfRepresentationData.setPrimaryApplicantRelationshipToDeceased(Relationship.CHILD);
        grantOfRepresentationData.setDeceasedOtherChildren(Boolean.TRUE);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(null);
        grantOfRepresentationData.setDeceasedDateOfBirth(afterDate);
        grantOfRepresentationData.setDeceasedDateOfDeath(beforeDate);

        assertThat(validator.validate(grantOfRepresentationData, Default.class, SubmissionGroup.class), hasSize(3));
    }
}
