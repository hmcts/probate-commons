package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.cases.DeathCertificate;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static uk.gov.hmcts.reform.probate.model.YesNo.NO;
import static uk.gov.hmcts.reform.probate.model.YesNo.YES;

public class GrantOfRepresentationTest {

    private GrantOfRepresentationData grantOfRepresentationData;
    private String email = "executor@email.com";
    private String firstName = "Jonny";
    private String lastName = "Vegas";
    private String executorApplyingName = "Bobby Bryan";

    @Before
    public void setUpTest() {
        grantOfRepresentationData = GrantOfRepresentationCreator.createProbateCase();
        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
                email, executorApplyingName);
    }

    @Test
    public void shouldSetInvitationAgreedFlagWithFirstNameAndLastName() {
        String invitationId = "123455";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
                "Graham Garderner", executorApplyingName);
        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying(invitationId, TRUE);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email,
                executorApplyingName);
        assertEquals(TRUE, executorApplying.getApplyingExecutorAgreed());
    }


    @Test
    public void shouldSetInvitationAgreedFlag() {
        String invitationId = "123455";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
                "Graham Garderner", executorApplyingName);
        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying(invitationId, TRUE);


        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
                "mainEmailExecutor", "Bobby Firmino", TRUE);


        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email);
        assertEquals(TRUE, executorApplying.getApplyingExecutorAgreed());
    }

    @Test
    public void shouldResetInvitationAgreedFlag() {

        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123455",
                "Graham Garderner", executorApplyingName);

        String emailExecutor1 = "executor1@email.com";
        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
                emailExecutor1, "Helen Fisher");
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(emailExecutor1, "123456",
                "Graham Garderner", "Helen Fisher");

        grantOfRepresentationData.resetExecutorsApplyingAgreedFlags();

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email,
                executorApplyingName);
        assertNull(executorApplying.getApplyingExecutorAgreed());

        ExecutorApplying executorApplying1 =
                grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1, "Helen Fisher");
        assertNull(executorApplying1.getApplyingExecutorAgreed());
    }


    @Test
    public void shouldDetermineThatAllExecutorsHaveAgreed() {

        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
                "mainEmailExecutor", "Main Exec", TRUE);

        grantOfRepresentationData.setDeclarationCheckbox(FALSE);
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123455",
                "Graham Garderner", executorApplyingName);

        String emailExecutor1 = "executor1@email.com";
        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
                emailExecutor1, "Bobby Firmino");
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(emailExecutor1, "123456",
                "Graham Garderner", "Bobby Firmino");

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123455", TRUE);

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", FALSE);

        ExecutorApplying executorApplying = grantOfRepresentationData
                .getExecutorApplyingByEmailAddress(email, executorApplyingName);
        assertEquals(TRUE, executorApplying.getApplyingExecutorAgreed());

        ExecutorApplying executorApplying1 =
                grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1, "Bobby Firmino");
        assertEquals(FALSE, executorApplying1.getApplyingExecutorAgreed());

        assertEquals(FALSE, grantOfRepresentationData.haveAllExecutorsAgreed());

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", TRUE);

        assertEquals(FALSE, grantOfRepresentationData.haveAllExecutorsAgreed());

        grantOfRepresentationData.setDeclarationCheckbox(TRUE);

        assertEquals(TRUE, grantOfRepresentationData.haveAllExecutorsAgreed());

        grantOfRepresentationData.setDeclarationCheckbox(null);

        assertEquals(FALSE, grantOfRepresentationData.haveAllExecutorsAgreed());
    }

    @Test
    public void shouldUpdateContactDetailsForExecutor() {

        String invitationId = "123455";
        String email = "executor@email.com";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
                "Graham Garderner", executorApplyingName);

        String newEmail = "newemail@email.com";
        String phoneNumber = "07989898981";
        grantOfRepresentationData.updateInvitationContactDetailsForExecutorApplying(invitationId, newEmail,
                phoneNumber);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(newEmail,
                executorApplyingName);
        assertEquals(phoneNumber, executorApplying.getApplyingExecutorPhoneNumber());
    }

    @Test
    public void shouldDeleteInvitationForExecutor() {

        String invitationId = "123455";
        String email = "executor@email.com";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
                "Graham Garderner", executorApplyingName);

        grantOfRepresentationData.deleteInvitation(invitationId);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email,
                executorApplyingName);

        assertNull(executorApplying.getApplyingExecutorAgreed());
    }

    @Test
    public void shouldDetermineWhetherInvitesHaveBeenSentForExecutorsReturnsFalse() {

        Boolean result = grantOfRepresentationData.haveInvitesBeenSent();
        assertEquals(FALSE, result);
    }

    @Test
    public void shouldDetermineWhetherInvitesHaveBeenSentForExecutorsReturnsTrue() {

        String invitationId = "123455";
        String email = "executor@email.com";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
                "Graham Garderner", executorApplyingName);

        Boolean result = grantOfRepresentationData.haveInvitesBeenSent();
        assertEquals(TRUE, result);
    }


    @Test
    public void shouldGetExecutorApplyingByEmailAddressAndNamesForSameEmailAddress() {

        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123455",
                "Graham Garderner", executorApplyingName);

        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
                email, "Bobby Firmino");
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123456",
                "Graham Garderner", "Bobby Firmino");

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123455", TRUE);

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", FALSE);

        ExecutorApplying executorApplying = grantOfRepresentationData
                .getExecutorApplyingByEmailAddress(email, executorApplyingName);
        assertEquals(TRUE, executorApplying.getApplyingExecutorAgreed());
        assertEquals(executorApplyingName, executorApplying.getApplyingExecutorName());


        ExecutorApplying executorApplying1 =
                grantOfRepresentationData.getExecutorApplyingByEmailAddress(email, "Bobby Firmino");
        assertEquals(FALSE, executorApplying1.getApplyingExecutorAgreed());
        assertEquals("Bobby Firmino", executorApplying1.getApplyingExecutorName());


        assertEquals(FALSE, grantOfRepresentationData.haveAllExecutorsAgreed());

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", TRUE);

        grantOfRepresentationData.setDeclarationCheckbox(TRUE);

        assertEquals(TRUE, grantOfRepresentationData.haveAllExecutorsAgreed());
    }

    @Test
    public void shouldGetChildrenAndGrandChildrenIntestacyBooleanSerialization() {

        grantOfRepresentationData.setChildrenDiedOverEighteen(null);
        grantOfRepresentationData.setChildrenDiedUnderEighteen(FALSE);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(FALSE);
        grantOfRepresentationData.setChildrenUnderEighteenSurvived(TRUE);
        grantOfRepresentationData.setGrandChildrenSurvivedOverEighteen(FALSE);
        grantOfRepresentationData.setGrandChildrenSurvivedUnderEighteen(TRUE);

        assertNull(grantOfRepresentationData.getChildrenDiedOverEighteenText());
        assertEquals(NO.getDescription(), grantOfRepresentationData.getChildrenDiedUnderEighteenText());
        assertEquals(NO.getDescription(), grantOfRepresentationData.getChildrenOverEighteenSurvivedText());
        assertEquals(YES.getDescription(), grantOfRepresentationData.getChildrenUnderEighteenSurvivedText());
        assertEquals(NO.getDescription(), grantOfRepresentationData.getGrandChildrenSurvivedOverEighteenText());
        assertEquals(YES.getDescription(), grantOfRepresentationData.getGrandChildrenSurvivedUnderEighteenText());
    }

    @Test
    public void shouldGetChildrenAndGrandChildrenIntestacyTextAsBoolean() {

        grantOfRepresentationData.setChildrenDiedOverEighteenText(null);
        grantOfRepresentationData.setChildrenDiedUnderEighteenText("T");
        grantOfRepresentationData.setChildrenOverEighteenSurvivedText("false");
        grantOfRepresentationData.setChildrenUnderEighteenSurvivedText("Yes");
        grantOfRepresentationData.setGrandChildrenSurvivedOverEighteenText("No");
        grantOfRepresentationData.setGrandChildrenSurvivedUnderEighteenText("true");

        assertNull(grantOfRepresentationData.getChildrenDiedOverEighteen());
        assertEquals(TRUE, grantOfRepresentationData.getChildrenDiedUnderEighteen());
        assertEquals(FALSE, grantOfRepresentationData.getChildrenOverEighteenSurvived());
        assertEquals(TRUE, grantOfRepresentationData.getChildrenUnderEighteenSurvived());
        assertEquals(FALSE, grantOfRepresentationData.getGrandChildrenSurvivedOverEighteen());
        assertEquals(TRUE, grantOfRepresentationData.getGrandChildrenSurvivedUnderEighteen());
    }


    @Test
    public void shouldDetermineIfDeceasedDeathCertInEnglish() {
        grantOfRepresentationData.setDeceasedDiedEngOrWales(TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(TRUE);
        assertNull(grantOfRepresentationData.isDeceasedDeathCertInEnglish());


        grantOfRepresentationData.setDeceasedDiedEngOrWales(FALSE);
        assertEquals(TRUE, grantOfRepresentationData.isDeceasedDeathCertInEnglish());
    }

    @Test
    public void shouldDetermineIfDeceasedForeignDeathCertIsTranslated() {
        grantOfRepresentationData.setDeceasedDiedEngOrWales(TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertTranslation(FALSE);
        assertNull(grantOfRepresentationData.isDeceasedForeignDeathCertTranslated());

        grantOfRepresentationData.setDeceasedDiedEngOrWales(FALSE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertTranslation(FALSE);
        assertNull(grantOfRepresentationData.isDeceasedForeignDeathCertTranslated());

        grantOfRepresentationData.setDeceasedDiedEngOrWales(FALSE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(FALSE);
        assertEquals(FALSE, grantOfRepresentationData.isDeceasedForeignDeathCertTranslated());
    }

    @Test
    public void shouldDetermineDeceasedDeathCertificateValue() {
        grantOfRepresentationData.setDeceasedDiedEngOrWales(FALSE);
        grantOfRepresentationData.setDeceasedDeathCertificate(DeathCertificate.DEATH_CERTIFICATE);
        assertNull(grantOfRepresentationData.getDeceasedDeathCert());

        grantOfRepresentationData.setDeceasedDiedEngOrWales(TRUE);
        grantOfRepresentationData.setDeceasedDeathCertificate(DeathCertificate.DEATH_CERTIFICATE);
        assertEquals("optionDeathCertificate", grantOfRepresentationData.getDeceasedDeathCert());
    }
}
