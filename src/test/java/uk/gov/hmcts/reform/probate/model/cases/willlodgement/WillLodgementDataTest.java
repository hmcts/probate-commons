package uk.gov.hmcts.reform.probate.model.cases.willlodgement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.WillLodgementDataCreator;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.junit.Assert.assertEquals;

public class WillLodgementDataTest {

    private ObjectMapper objectMapper;

    private WillLodgementData willLodgementData;

    private String jsonFromFile;

    @Before
    public void setUp() throws Exception {
        jsonFromFile = TestUtils.getJsonFromFile("willLodgementData.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        willLodgementData = WillLodgementDataCreator.createWillLodgement();
    }

    @Test
    public void shouldDeserializeWillLodgementDataCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(jsonFromFile, CaseData.class);

        assertEquals(caseData, willLodgementData);
    }

    @Test
    public void shouldSerializeWillLodgementDataCorrectly() throws IOException, JSONException {
        String json = objectMapper.writeValueAsString(willLodgementData);

        System.out.println(json);

        JSONAssert.assertEquals(jsonFromFile, json, true);
    }

}
