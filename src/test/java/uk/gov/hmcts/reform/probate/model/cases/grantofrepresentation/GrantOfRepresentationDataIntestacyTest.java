package uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.GrantOfRepresentationCreator;
import uk.gov.hmcts.reform.probate.model.cases.CaseData;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static uk.gov.hmcts.reform.probate.model.TestUtils.getJsonFromFile;

public class GrantOfRepresentationDataIntestacyTest {

    private ObjectMapper objectMapper;

    private GrantOfRepresentationData grantOfRepresentationData;

    private GrantOfRepresentationData grantOfRepresentationDataWithOrg;

    private GrantOfRepresentationData grantOfRepresentationDataWithLastEvidence;

    private GrantOfRepresentationData grantOfRepresentationDataStoppedWithEvidence;

    private GrantOfRepresentationData bulkScanCitizenGrantOfRepresentationData;

    private GrantOfRepresentationData bulkScanSolicitorGrantOfRepresentationData;

    private String gorJsonFromFile;

    private String gorWithOrgJsonFromFile;

    private String gorWithEvidenceJsonFromFile;

    private String gorStoppedWithEvidenceJsonFromFile;

    private String gorWithCaseHandOffFlagFromFile;

    private String bulkScanCitizenGorJsonFromFile;

    private String bulkScanSolicitorGorJsonFromFile;

    @BeforeEach
    public void setUp() throws Exception {
        gorJsonFromFile = getJsonFromFile("intestacyGrantOfRepresentation.json");
        gorWithOrgJsonFromFile = getJsonFromFile("intestacyGrantOfRepresentationWithOrg.json");
        gorWithEvidenceJsonFromFile = getJsonFromFile("intestacyGrantOfRepresentationWithLastEvidenceAddedDate.json");
        gorStoppedWithEvidenceJsonFromFile = getJsonFromFile("intestacyGrantOfRepresentationStoppedWithEvidence.json");
        gorWithCaseHandOffFlagFromFile = getJsonFromFile("intestacyFormCaseHandOffFlag.json");
        bulkScanCitizenGorJsonFromFile =
                getJsonFromFile("bulkScanIntestacyCitizenGrantOfRepresentation.json");
        bulkScanSolicitorGorJsonFromFile =
                getJsonFromFile("bulkScanIntestacySolicitorGrantOfRepresentation.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        grantOfRepresentationData = GrantOfRepresentationCreator.createIntestacyCase();
        grantOfRepresentationDataWithOrg = GrantOfRepresentationCreator.createIntestacyCaseWithOrg();
        grantOfRepresentationDataWithLastEvidence = GrantOfRepresentationCreator.createIntestacyCaseWithLastEvidence();
        grantOfRepresentationDataStoppedWithEvidence
                = GrantOfRepresentationCreator.createStoppedIntestacyCaseWithEvidence();
        bulkScanCitizenGrantOfRepresentationData =
                GrantOfRepresentationCreator.createCitizenIntestacyCaseWithBulkScanData();
        bulkScanSolicitorGrantOfRepresentationData =
                GrantOfRepresentationCreator.createSolicitorIntestacyCaseWithBulkScanData();
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

    @Test
    public void shouldDeserializeGrantOfRepresentationWithOrgDataCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(gorWithOrgJsonFromFile, CaseData.class);

        assertThat(grantOfRepresentationDataWithOrg, is(equalTo(caseData)));
    }

    @Test
    public void shouldSerializeGrantOfRepresentationDataWithOrgCorrectly() throws IOException, JSONException {
        String intestacyGorAsJsonStr = objectMapper.writeValueAsString(grantOfRepresentationDataWithOrg);

        JSONAssert.assertEquals(gorWithOrgJsonFromFile, intestacyGorAsJsonStr, true);
    }

    @Test
    public void shouldDeserializeGrantOfRepresentationWithEvidenceCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(gorWithEvidenceJsonFromFile, CaseData.class);

        assertThat(grantOfRepresentationDataWithLastEvidence, is(equalTo(caseData)));
    }

    @Test
    public void shouldSerializeGrantOfRepresentationDataWithEvidenceCorrectly() throws IOException, JSONException {
        String intestacyGorAsJsonStr = objectMapper.writeValueAsString(grantOfRepresentationDataWithLastEvidence);

        JSONAssert.assertEquals(gorWithEvidenceJsonFromFile, intestacyGorAsJsonStr, true);
    }

    @Test
    public void shouldDeserializeStoppedGrantOfRepresentationWithEvidenceCorrectly() throws IOException {
        CaseData caseData = objectMapper.readValue(gorStoppedWithEvidenceJsonFromFile, CaseData.class);

        assertThat(grantOfRepresentationDataStoppedWithEvidence, is(equalTo(caseData)));
    }

    @Test
    public void shouldSerializeStoppedGrantOfRepresentationWithEvidenceCorrectly() throws IOException, JSONException {
        String intestacyGorAsJsonStr = objectMapper.writeValueAsString(grantOfRepresentationDataStoppedWithEvidence);

        JSONAssert.assertEquals(gorStoppedWithEvidenceJsonFromFile, intestacyGorAsJsonStr, true);
    }

    @Test
    public void shouldSerializeCitizenGrantOfRepresentationDataFromBulkScanCorrectly()
            throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(bulkScanCitizenGrantOfRepresentationData);

        JSONAssert.assertEquals(bulkScanCitizenGorJsonFromFile, actualJson, true);
    }

    @Test
    public void shouldSerializeSolicitorGrantOfRepresentationDataFromBulkScanCorrectly()
            throws IOException, JSONException {
        String actualJson = objectMapper.writeValueAsString(bulkScanSolicitorGrantOfRepresentationData);

        JSONAssert.assertEquals(bulkScanSolicitorGorJsonFromFile, actualJson, true);
    }

    @Test
    public void shouldDeserializeCaseHandOffFlag() throws IOException {
        CaseData caseData = objectMapper.readValue(gorWithCaseHandOffFlagFromFile, CaseData.class);
        grantOfRepresentationData.setCaseHandedOffToLegacySite(true);
        assertThat(grantOfRepresentationData, is(equalTo(caseData)));
    }
}
