package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.forms.Form;

import java.io.IOException;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class IntestacyFormTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldDeserializeIntestacyFormCorrectly() throws IOException {
        String jsonFromFile = TestUtils.getJsonFromFile("intestacyForm.json");
        Form form = objectMapper.readValue(jsonFromFile, Form.class);
        assertThat(form, instanceOf(IntestacyForm.class));
    }
}
