package uk.gov.hmcts.reform.probate.model.cases;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.BIRMINGHAM;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.MANCHESTER;
import static uk.gov.hmcts.reform.probate.model.cases.RegistryLocation.OXFORD;

public class RegistryLocationTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetRegistryLocationByName() {
        RegistryLocation registryLocation;

        registryLocation = RegistryLocation.findRegistryLocationByName("birmingham");
        assertEquals(BIRMINGHAM, registryLocation);

        registryLocation = RegistryLocation.findRegistryLocationByName("OXFORD");
        assertEquals(OXFORD, registryLocation);

        registryLocation = RegistryLocation.findRegistryLocationByName("ManchesTer");
        assertEquals(MANCHESTER, registryLocation);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenRegistryLocationDoesNotExist() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Unsupported RegistryLocation Birminghen");

        RegistryLocation registryLocation = RegistryLocation.findRegistryLocationByName("Birminghen");
        fail();
    }

}
