package uk.gov.hmcts.reform.probate.model.cases;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RegistryLocationTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetRegistryLocationByName() {
        RegistryLocation registryLocation;

        registryLocation = RegistryLocation.findRegistryLocationByName("birmingham");
        assertThat(registryLocation, is(RegistryLocation.BIRMINGHAM));

        registryLocation = RegistryLocation.findRegistryLocationByName("OXFORD");
        assertThat(registryLocation, is(RegistryLocation.OXFORD));

        registryLocation = RegistryLocation.findRegistryLocationByName("ManchesTer");
        assertThat(registryLocation, is(RegistryLocation.MANCHESTER));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenRegistryLocationDoesNotExist() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Unsupported RegistryLocation Birminghen");

        RegistryLocation registryLocation = RegistryLocation.findRegistryLocationByName("Birminghen");
        fail();
    }

}
