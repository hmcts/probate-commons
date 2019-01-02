package uk.gov.hmcts.reform.probate.model.cases.caveat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.CaveatCreator;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CaveatTest {

    private ObjectMapper objectMapper;

    private Caveat caveat;

    private String gorJsonFromFile;

    @Before
    public void setUp() throws Exception {
        gorJsonFromFile = TestUtils.getJsonFromFile("caveat.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        caveat = CaveatCreator.createCaveatCase();
    }

    @Test
    public void shouldDeserializeIntestacyFormCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(gorJsonFromFile, CaseData.class);

        assertThat(caveat, is(equalTo(caseData)));
    }

    @Test
    public void shouldSerializeIntestacyFormCorrectly() throws IOException, JSONException {
        String caveatGorAsJsonStr = objectMapper.writeValueAsString(caveat);

        JSONAssert.assertEquals(gorJsonFromFile, caveatGorAsJsonStr, true);
    }
}
