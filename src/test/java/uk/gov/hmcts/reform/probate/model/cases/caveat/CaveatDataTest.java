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

public class CaveatDataTest {

    private ObjectMapper objectMapper;

    private CaveatData caveatData;

    private CaveatData bulkScanCaveatData;

    private String json;

    private String bulkScanJson;

    @Before
    public void setUp() throws Exception {
        json = TestUtils.getJsonFromFile("caveatData.json");
        bulkScanJson = TestUtils.getJsonFromFile("bulkScanCaveatData.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        caveatData = CaveatCreator.createCaveatCase();
        bulkScanCaveatData = CaveatCreator.createCaveatCaseWithBulkScanData();
    }

    @Test
    public void shouldDeserializeCaveatDataCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(json, CaseData.class);

        assertThat(caveatData, is(equalTo(caseData)));
    }

    @Test
    public void shouldSerializeCaveatDataCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(caveatData);

        JSONAssert.assertEquals(json, actualJson, true);
    }

    @Test
    public void shouldSerializeCaveatDataFromBulkScanCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(bulkScanCaveatData);

        JSONAssert.assertEquals(bulkScanJson, actualJson, true);
    }
}
