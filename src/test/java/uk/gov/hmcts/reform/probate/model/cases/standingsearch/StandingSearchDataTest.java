package uk.gov.hmcts.reform.probate.model.cases.standingsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.StandingSearchDataCreator;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StandingSearchDataTest {

    private ObjectMapper objectMapper;

    private StandingSearchData standingSearchData;

    private String jsonFromFile;

    @Before
    public void setUp() throws Exception {
        jsonFromFile = TestUtils.getJsonFromFile("standingSearchData.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        standingSearchData = StandingSearchDataCreator.createStandingSearchData();
    }

    @Test
    public void shouldDeserializeStandingSearchDataCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(jsonFromFile, CaseData.class);

        assertThat(standingSearchData, is(equalTo(caseData)));
    }

    @Test
    public void shouldSerializeStandingSearchDataCorrectly() throws IOException, JSONException {
        String json = objectMapper.writeValueAsString(standingSearchData);

        JSONAssert.assertEquals(jsonFromFile, json, true);
    }

}
