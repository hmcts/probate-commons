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

    @Before
    public void setUpTest() {
        grantOfRepresentationData = GrantOfRepresentationCreator.createProbateCase();
        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
            email);
    }

    @Test
    public void shouldSetInvitationAgreedFlag() {
        String invitationId = "123455";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
            "Graham Garderner");
        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying(invitationId, Boolean.TRUE);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email);
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));
    }

    @Test
    public void shouldResetInvitationAgreedFlag() {

        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123455",
            "Graham Garderner");

        String emailExecutor1 = "executor1@email.com";
        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
            emailExecutor1);
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(emailExecutor1, "123456",
            "Graham Garderner");

        grantOfRepresentationData.resetExecutorsApplyingAgreedFlags();

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(this.email);
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(null)));

        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1);
        Assert.assertThat(executorApplying1.getApplyingExecutorAgreed(),
            is(equalTo(null)));
    }


    @Test
    public void shouldDetermineThatAllExecutorsHaveAgreed() {

        grantOfRepresentationData.setDeclarationCheckbox(Boolean.FALSE);
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, "123455",
            "Graham Garderner");

        String emailExecutor1 = "executor1@email.com";
        GrantOfRepresentationCreator.addExecutorApplying(grantOfRepresentationData,
            emailExecutor1);
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(emailExecutor1, "123456",
            "Graham Garderner");

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123455", Boolean.TRUE);

        grantOfRepresentationData.setInvitationAgreedFlagForExecutorApplying("123456", Boolean.FALSE);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(this.email);
        Assert.assertThat(executorApplying.getApplyingExecutorAgreed(),
            is(equalTo(Boolean.TRUE)));

        ExecutorApplying executorApplying1 =
            grantOfRepresentationData.getExecutorApplyingByEmailAddress(emailExecutor1);
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
            "Graham Garderner");

        String newEmail = "newemail@email.com";
        String phoneNumber = "07989898981";
        grantOfRepresentationData.updateInvitationContactDetailsForExecutorApplying(invitationId, newEmail,
            phoneNumber);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(newEmail);
        Assert.assertThat(executorApplying.getApplyingExecutorPhoneNumber(),
            is(equalTo(phoneNumber)));
    }

    @Test
    public void shouldDeleteInvitationForExecutor() {

        String invitationId = "123455";
        String email = "executor@email.com";
        grantOfRepresentationData.setInvitationDetailsForExecutorApplying(email, invitationId,
            "Graham Garderner");

        grantOfRepresentationData.deleteInvitation(invitationId);

        ExecutorApplying executorApplying = grantOfRepresentationData.getExecutorApplyingByEmailAddress(email);

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
            "Graham Garderner");

        Boolean result = grantOfRepresentationData.haveInvitesBeenSent();
        Assert.assertThat(result,
            is(equalTo(Boolean.TRUE)));
    }
}
