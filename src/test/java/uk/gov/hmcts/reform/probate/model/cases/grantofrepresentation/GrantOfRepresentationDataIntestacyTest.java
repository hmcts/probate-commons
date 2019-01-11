package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GrantOfRepresentationDataIntestacyTest {

    private ObjectMapper objectMapper;

    private GrantOfRepresentationData grantOfRepresentationData;

    private String gorJsonFromFile;

    @Before
    public void setUp() throws Exception {
        gorJsonFromFile = TestUtils.getJsonFromFile("intestacyGrantOfRepresentation.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        grantOfRepresentationData = GrantOfRepresentationCreator.createIntestacyCase();
    }

    @Test
    public void shouldDeserializeGrantOfRepresentationDataCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(gorJsonFromFile, CaseData.class);

        assertThat(grantOfRepresentationData, is(equalTo(caseData)));
    }

    @Test
    public void shouldSerializeGrantOfRepresentationDataCorrectly() throws IOException, JSONException {
        String intestacyGorAsJsonStr = objectMapper.writeValueAsString(grantOfRepresentationData);

        JSONAssert.assertEquals(gorJsonFromFile, intestacyGorAsJsonStr, true);
    }

}
