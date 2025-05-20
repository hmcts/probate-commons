package uk.gov.hmcts.reform.probate.model.cases;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistryLocationTest {

    @Test
    void shouldGetRegistryLocationByName() {
        RegistryLocation registryLocation;

        registryLocation = RegistryLocation.findRegistryLocationByName("birmingham");
        assertThat(registryLocation, is(RegistryLocation.BIRMINGHAM));

        registryLocation = RegistryLocation.findRegistryLocationByName("OXFORD");
        assertThat(registryLocation, is(RegistryLocation.OXFORD));

        registryLocation = RegistryLocation.findRegistryLocationByName("ManchesTer");
        assertThat(registryLocation, is(RegistryLocation.MANCHESTER));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenRegistryLocationDoesNotExist() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
            RegistryLocation registryLocation = RegistryLocation.findRegistryLocationByName("Birminghen");
        });
        assertThat(iae.getMessage(), is("Unsupported RegistryLocation Birminghen"));
    }

}
