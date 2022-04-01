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
import static org.junit.Assert.assertEquals;

public class CaveatDataTest {

    private ObjectMapper objectMapper;

    private CaveatData caveatData;

    private CaveatData bulkScanCitizenCaveatData;

    private CaveatData bulkScanSolicitorCaveatData;

    private String json;

    private String bulkScanJson;

    private String bulkScanSolicitorJson;

    @Before
    public void setUp() throws Exception {
        json = TestUtils.getJsonFromFile("caveatData.json");
        bulkScanJson = TestUtils.getJsonFromFile("bulkScanCitizenCaveatData.json");
        bulkScanSolicitorJson = TestUtils.getJsonFromFile("bulkScanSolicitorCaveatData.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        caveatData = CaveatCreator.createCaveatCase();
        bulkScanCitizenCaveatData = CaveatCreator.createCaveatCaseWithCitizenBulkScanData();
        bulkScanSolicitorCaveatData = CaveatCreator.createCaveatCaseWithSolicitorBulkScanData();
    }

    @Test
    public void shouldDeserializeCaveatDataCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(json, CaseData.class);

        assertEquals(caseData, caveatData);
    }

    @Test
    public void shouldSerializeCaveatDataCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(caveatData);

        JSONAssert.assertEquals(json, actualJson, true);
    }

    @Test
    public void shouldSerializeCaveatDataFromBulkScanCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(bulkScanCitizenCaveatData);

        JSONAssert.assertEquals(bulkScanJson, actualJson, true);
    }

    @Test
    public void shouldSerializeSolicitorCaveatDataFromBulkScanCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(bulkScanSolicitorCaveatData);

        JSONAssert.assertEquals(bulkScanSolicitorJson, actualJson, true);
    }
}
