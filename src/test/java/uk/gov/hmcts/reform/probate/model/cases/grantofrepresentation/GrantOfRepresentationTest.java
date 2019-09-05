package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying(invitationId, Boolean.TRUE);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email,
            executorApplyingName);
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
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
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
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
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(null)));

        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1, "Helen Fisher");
        Assert.assertThat(executorApplying1.getApplyingExecutorAgreed(),
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
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));

        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1, "Bobby Firmino");
        Assert.assertThat(executorApplying1.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.FALSE)));

        Assert.assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.FALSE)));

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", Boolean.TRUE);

        Assert.assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.FALSE)));

        grantOfRepresentationData.setDeclarationCheckbox(Boolean.TRUE);

        Assert.assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.TRUE)));
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
        Assert.assertThat(executorApplying.getApplyingExecutorPhoneNumber(),
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

        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(null)));
    }

    @Test
    public void shouldDetermineWhetherInvitesHaveBeenSentForExecutorsReturnsFalse() {

        Boolean result = grantOfRepresentationData.haveInvitesBeenSent();
        Assert.assertThat(result,
            is(equalTo(Boolean.FALSE)));
    }

    @Test
    public void shouldDetermineWhetherInvitesHaveBeenSentForExecutorsReturnsTrue() {

        String invitationId = "123455";
        String email = "executor@email.com";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
            "Graham Garderner", executorApplyingName);

        Boolean result = grantOfRepresentationData.haveInvitesBeenSent();
        Assert.assertThat(result,
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
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));
        Assert.assertThat(executorApplying.getApplyingExecutorName(),
            is(equalTo(executorApplyingName)));


        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(email, "Bobby Firmino");
        Assert.assertThat(executorApplying1.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.FALSE)));
        Assert.assertThat(executorApplying1.getApplyingExecutorName(),
            is(equalTo("Bobby Firmino")));


        Assert.assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.FALSE)));

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", Boolean.TRUE);

        grantOfRepresentationData.setDeclarationCheckbox(Boolean.TRUE);

        Assert.assertThat(grantOfRepresentationData.haveAllExecutorsAgreed(),
            is(equalTo(Boolean.TRUE)));
    }

}
