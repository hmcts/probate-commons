package uk.gov.hmcts.reform.probate.model.cases.caveat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.CaveatCreator;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class CaveatDataTest {

    private ObjectMapper objectMapper;

    private CaveatData caveatData;

    private CaveatData caveatDataWithOrganisation;

    private CaveatData bulkScanCitizenCaveatData;

    private CaveatData bulkScanSolicitorCaveatData;

    private String json;

    private String organisationJson;

    private String bulkScanJson;

    private String bulkScanSolicitorJson;

    @BeforeEach
    public void setUp() throws Exception {
        json = TestUtils.getJsonFromFile("caveatData.json");
        organisationJson = TestUtils.getJsonFromFile("caveatDataWithOrg.json");
        bulkScanJson = TestUtils.getJsonFromFile("bulkScanCitizenCaveatData.json");
        bulkScanSolicitorJson = TestUtils.getJsonFromFile("bulkScanSolicitorCaveatData.json");
        objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        caveatData = CaveatCreator.createCaveatCase();
        bulkScanCitizenCaveatData = CaveatCreator.createCaveatCaseWithCitizenBulkScanData();
        caveatDataWithOrganisation = CaveatCreator.createCaveatCaseWithOrganisationInfo();
        bulkScanSolicitorCaveatData = CaveatCreator.createCaveatCaseWithSolicitorBulkScanData();
    }

    @Test
    void shouldDeserializeCaveatDataCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(json, CaseData.class);

        assertThat(caveatData, is(equalTo(caseData)));
    }

    @Test
    void shouldSerializeCaveatDataCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(caveatData);

        JSONAssert.assertEquals(json, actualJson, true);
    }

    @Test
    void shouldDeserializeCaveatDataWithOrgCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(organisationJson, CaseData.class);

        assertThat(caveatDataWithOrganisation, is(equalTo(caseData)));
    }

    @Test
    void shouldSerializeCaveatDataWithOrgCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(caveatDataWithOrganisation);

        JSONAssert.assertEquals(organisationJson, actualJson, true);
    }

    @Test
    void shouldSerializeCaveatDataFromBulkScanCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(bulkScanCitizenCaveatData);

        JSONAssert.assertEquals(bulkScanJson, actualJson, true);
    }

    @Test
    void shouldSerializeSolicitorCaveatDataFromBulkScanCorrectly() throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(bulkScanSolicitorCaveatData);

        JSONAssert.assertEquals(bulkScanSolicitorJson, actualJson, true);
    }

    @Test
    void shouldAddRegistrarDirections() {
        CaveatData caveatData = CaveatCreator.createCaveatCaseWithRegistrarDirections();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        assertThat(caveatData.getRegistrarDirections().size(),
                is(equalTo(2)));
        assertThat(caveatData.getRegistrarDirections().get(0).getValue().getAddedDateTime(),
                is(equalTo(LocalDateTime.parse("2023-01-01T23:45:45.890Z", dateTimeFormatter))));
        assertThat(caveatData.getRegistrarDirections().get(0).getValue().getDecision(),
                is(equalTo("Decision 1")));
        assertThat(caveatData.getRegistrarDirections().get(0).getValue().getFurtherInformation(),
                is(equalTo("Further information 1")));
        assertThat(caveatData.getRegistrarDirections().get(1).getValue().getAddedDateTime(),
                is(equalTo(LocalDateTime.parse("2023-01-02T23:45:45.890Z", dateTimeFormatter))));
        assertThat(caveatData.getRegistrarDirections().get(1).getValue().getDecision(),
                is(equalTo("Decision 2")));
        assertThat(caveatData.getRegistrarDirections().get(1).getValue().getFurtherInformation(),
                is(equalTo(null)));

        assertThat(caveatData.getRegistrarDirectionToAdd().getDecision(),
                is(equalTo("Decision NEWEST")));
        assertThat(caveatData.getRegistrarDirectionToAdd().getFurtherInformation(),
                is(equalTo("Further information NEWEST")));
        assertThat(caveatData.getRegistrarDirectionToAdd().getAddedDateTime(),
                is(not(equalTo(null))));
    }

}
