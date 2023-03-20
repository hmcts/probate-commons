package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.cases.DeathCertificate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static uk.gov.hmcts.reform.probate.model.YesNo.NO;
import static uk.gov.hmcts.reform.probate.model.YesNo.YES;

public class GrantOfRepresentationTest {

    private GrantOfRepresentationData grantOfRepresentationData;
    private String email = "executor@email.com";
    private String firstName = "Jonny";
    private String lastName = "Vegas";
    private String executorApplyingName = "Bobby Bryan";

    @BeforeEach
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
        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying(invitationId, Boolean.TRUE);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email,
            executorApplyingName);
        assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));
    }


    @Test
    public void shouldSetInvitationAgreedFlag() {
        String invitationId = "123455";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
            "Graham Garderner", executorApplyingName);
        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying(invitationId, Boolean.TRUE);


        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
            "mainEmailExecutor", "Bobby Firmino", Boolean.TRUE);


        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email);
        assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));
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
        assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(null)));

        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1, "Helen Fisher");
        assertThat(executorApplying1.getApplyingExecutorAgreed(),
            is(equalTo(null)));
    }


    @Test
    public void shouldDetermineThatAllExecutorsHaveAgreed() {

        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
            "mainEmailExecutor", "Main Exec", Boolean.TRUE);

        grantOfRepresentationData.setDeclarationCheckbox(Boolean.FALSE);
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123455",
            "Graham Garderner", executorApplyingName);

        String emailExecutor1 = "executor1@email.com";
        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
            emailExecutor1, "Bobby Firmino");
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(emailExecutor1, "123456",
            "Graham Garderner", "Bobby Firmino");

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123455", Boolean.TRUE);

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", Boolean.FALSE);

        ExecutorApplying executorApplying = grantOfRepresentationData
            .getExecutorApplyingByEmailAddress(email, executorApplyingName);
        assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));

        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1, "Bobby Firmino");
        assertThat(executorApplying1.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.FALSE)));

        assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.FALSE)));

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", Boolean.TRUE);

        assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.FALSE)));

        grantOfRepresentationData.setDeclarationCheckbox(Boolean.TRUE);

        assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.TRUE)));

        grantOfRepresentationData.setDeclarationCheckbox(null);

        assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.FALSE)));
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
        assertThat(executorApplying.getApplyingExecutorPhoneNumber(),
            is(equalTo(phoneNumber)));
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

        assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(null)));
    }

    @Test
    public void shouldDetermineWhetherInvitesHaveBeenSentForExecutorsReturnsFalse() {

        Boolean result = grantOfRepresentationData.haveInvitesBeenSent();
        assertThat(result,
            is(equalTo(Boolean.FALSE)));
    }

    @Test
    public void shouldDetermineWhetherInvitesHaveBeenSentForExecutorsReturnsTrue() {

        String invitationId = "123455";
        String email = "executor@email.com";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
            "Graham Garderner", executorApplyingName);

        Boolean result = grantOfRepresentationData.haveInvitesBeenSent();
        assertThat(result,
            is(equalTo(Boolean.TRUE)));
    }


    @Test
    public void shouldGetExecutorApplyingByEmailAddressAndNamesForSameEmailAddress() {

        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123455",
            "Graham Garderner", executorApplyingName);

        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
            email, "Bobby Firmino");
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123456",
            "Graham Garderner", "Bobby Firmino");

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123455", Boolean.TRUE);

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", Boolean.FALSE);

        ExecutorApplying executorApplying = grantOfRepresentationData
            .getExecutorApplyingByEmailAddress(email, executorApplyingName);
        assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));
        assertThat(executorApplying.getApplyingExecutorName(),
            is(equalTo(executorApplyingName)));


        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(email, "Bobby Firmino");
        assertThat(executorApplying1.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.FALSE)));
        assertThat(executorApplying1.getApplyingExecutorName(),
            is(equalTo("Bobby Firmino")));


        assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.FALSE)));

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", Boolean.TRUE);

        grantOfRepresentationData.setDeclarationCheckbox(Boolean.TRUE);

        assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void shouldGetChildrenAndGrandChildrenIntestacyBooleanSerialization() {

        grantOfRepresentationData.setChildrenDiedOverEighteen(null);
        grantOfRepresentationData.setChildrenDiedUnderEighteen(Boolean.FALSE);
        grantOfRepresentationData.setChildrenOverEighteenSurvived(Boolean.FALSE);
        grantOfRepresentationData.setChildrenUnderEighteenSurvived(Boolean.TRUE);
        grantOfRepresentationData.setGrandChildrenSurvivedOverEighteen(Boolean.FALSE);
        grantOfRepresentationData.setGrandChildrenSurvivedUnderEighteen(Boolean.TRUE);

        assertThat(grantOfRepresentationData.getChildrenDiedOverEighteenText(),
            is(equalTo(null)));
        assertThat(grantOfRepresentationData.getChildrenDiedUnderEighteenText(),
            is(equalTo(NO.getDescription())));
        assertThat(grantOfRepresentationData.getChildrenOverEighteenSurvivedText(),
            is(equalTo(NO.getDescription())));
        assertThat(grantOfRepresentationData.getChildrenUnderEighteenSurvivedText(),
            is(equalTo(YES.getDescription())));
        assertThat(grantOfRepresentationData.getGrandChildrenSurvivedOverEighteenText(),
            is(equalTo(NO.getDescription())));
        assertThat(grantOfRepresentationData.getGrandChildrenSurvivedUnderEighteenText(),
            is(equalTo(YES.getDescription())));
    }

    @Test
    public void shouldGetChildrenAndGrandChildrenIntestacyTextAsBoolean() {

        grantOfRepresentationData.setChildrenDiedOverEighteenText(null);
        grantOfRepresentationData.setChildrenDiedUnderEighteenText("T");
        grantOfRepresentationData.setChildrenOverEighteenSurvivedText("false");
        grantOfRepresentationData.setChildrenUnderEighteenSurvivedText("Yes");
        grantOfRepresentationData.setGrandChildrenSurvivedOverEighteenText("No");
        grantOfRepresentationData.setGrandChildrenSurvivedUnderEighteenText("true");

        assertThat(grantOfRepresentationData.getChildrenDiedOverEighteen(), is(equalTo(null)));
        assertThat(grantOfRepresentationData.getChildrenDiedUnderEighteen(), is(equalTo(Boolean.TRUE)));
        assertThat(grantOfRepresentationData.getChildrenOverEighteenSurvived(), is(equalTo(Boolean.FALSE)));
        assertThat(grantOfRepresentationData.getChildrenUnderEighteenSurvived(), is(equalTo(Boolean.TRUE)));
        assertThat(grantOfRepresentationData.getGrandChildrenSurvivedOverEighteen(), is(equalTo(Boolean.FALSE)));
        assertThat(grantOfRepresentationData.getGrandChildrenSurvivedUnderEighteen(), is(equalTo(Boolean.TRUE)));
    }


    @Test
    public void shouldDetermineIfDeceasedDeathCertInEnglish() {
        grantOfRepresentationData.setDeceasedDiedEngOrWales(Boolean.TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(Boolean.TRUE);
        assertThat(grantOfRepresentationData.isDeceasedDeathCertInEnglish(),
                is(equalTo(null)));


        grantOfRepresentationData.setDeceasedDiedEngOrWales(Boolean.FALSE);
        assertThat(grantOfRepresentationData.isDeceasedDeathCertInEnglish(),
                is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void shouldDetermineIfDeceasedForeignDeathCertIsTranslated() {
        grantOfRepresentationData.setDeceasedDiedEngOrWales(Boolean.TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(Boolean.TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertTranslation(Boolean.FALSE);
        assertThat(grantOfRepresentationData.isDeceasedForeignDeathCertTranslated(),
                is(equalTo(null)));

        grantOfRepresentationData.setDeceasedDiedEngOrWales(Boolean.FALSE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(Boolean.TRUE);
        grantOfRepresentationData.setDeceasedForeignDeathCertTranslation(Boolean.FALSE);
        assertThat(grantOfRepresentationData.isDeceasedForeignDeathCertTranslated(),
                is(equalTo(null)));

        grantOfRepresentationData.setDeceasedDiedEngOrWales(Boolean.FALSE);
        grantOfRepresentationData.setDeceasedForeignDeathCertInEnglish(Boolean.FALSE);
        assertThat(grantOfRepresentationData.isDeceasedForeignDeathCertTranslated(),
                is(equalTo(Boolean.FALSE)));
    }

    @Test
    public void shouldDetermineDeceasedDeathCertificateValue() {
        grantOfRepresentationData.setDeceasedDiedEngOrWales(Boolean.FALSE);
        grantOfRepresentationData.setDeceasedDeathCertificate(DeathCertificate.DEATH_CERTIFICATE);
        assertThat(grantOfRepresentationData.getDeceasedDeathCert(),
                is(equalTo(null)));

        grantOfRepresentationData.setDeceasedDiedEngOrWales(Boolean.TRUE);
        grantOfRepresentationData.setDeceasedDeathCertificate(DeathCertificate.DEATH_CERTIFICATE);
        assertThat(grantOfRepresentationData.getDeceasedDeathCert(),
                is(equalTo("optionDeathCertificate")));
    }

    @Test
    public void shouldDetermineWillAccess() {
        assertThat(grantOfRepresentationData.getWillAccessOriginal(),
            is(equalTo(false)));
        assertThat(grantOfRepresentationData.getWillAccessNotarial(),
            is(equalTo(true)));
        assertThat(grantOfRepresentationData.getNoOriginalWillAccessReason(),
            is(equalTo("No original will access reason")));

    }

    @Test
    public void shouldAddRegistrarDirections() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        assertThat(grantOfRepresentationData.getRegistrarDirections().size(),
                is(equalTo(2)));
        assertThat(grantOfRepresentationData.getRegistrarDirections().get(0).getValue().getAddedDateTime(),
                is(equalTo(LocalDateTime.parse("2023-01-01T23:45:45.890Z", dateTimeFormatter))));
        assertThat(grantOfRepresentationData.getRegistrarDirections().get(0).getValue().getDecision(),
                is(equalTo("Decision 1")));
        assertThat(grantOfRepresentationData.getRegistrarDirections().get(0).getValue().getFurtherInformation(),
                is(equalTo("Further information 1")));
        assertThat(grantOfRepresentationData.getRegistrarDirections().get(1).getValue().getAddedDateTime(),
                is(equalTo(LocalDateTime.parse("2023-01-02T23:45:45.890Z", dateTimeFormatter))));
        assertThat(grantOfRepresentationData.getRegistrarDirections().get(1).getValue().getDecision(),
                is(equalTo("Decision 2")));
        assertThat(grantOfRepresentationData.getRegistrarDirections().get(1).getValue().getFurtherInformation(),
                is(equalTo(null)));

        assertThat(grantOfRepresentationData.getRegistrarDirectionToAdd().getDecision(),
                is(equalTo("Decision NEWEST")));
        assertThat(grantOfRepresentationData.getRegistrarDirectionToAdd().getFurtherInformation(),
                is(equalTo("Further information NEWEST")));
        assertThat(grantOfRepresentationData.getRegistrarDirectionToAdd().getAddedDateTime(),
                is(not(equalTo(null))));
    }
}
