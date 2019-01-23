package uk.gov.hmcts.reform.probate.model.forms.caveat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.PaymentStatus;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CaveatFormTest {

    private ObjectMapper objectMapper;

    private CaveatForm caveatForm;

    private String formJsonFromFile;

    @Before
    public void setUp() throws Exception {
        formJsonFromFile = TestUtils.getJsonFromFile("caveatForm.json");

        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);

        caveatForm = new CaveatForm();
        caveatForm.setType(ProbateType.CAVEAT);

        CaveatApplicant caveatApplicant = new CaveatApplicant();
        caveatApplicant.setFreeTextAddress("Pret a Manger St. Georges Hospital Blackshaw Road");
        caveatApplicant.setAddressFound(true);
        caveatApplicant.setEmail("jon.snow@thenorth.com");
        caveatApplicant.setFirstName("Jon");
        caveatApplicant.setLastName("Snow");
        caveatApplicant.setAddress("Pret a Manger St. Georges Hospital Blackshaw Road London SW17 0QT");
        caveatApplicant.setPostCode("SW17 0QT");
        caveatApplicant.setPhoneNumber("123455678");
        caveatForm.setApplicant(caveatApplicant);

        CaveatDeceased caveatDeceased = new CaveatDeceased();
        caveatDeceased.setFirstName("Ned");
        caveatDeceased.setLastName("Stark");
        caveatDeceased.setDateOfBirth(LocalDate.of(1930, 1, 1));
        caveatDeceased.setDateOfDeath(LocalDate.of(2018, 1, 1));
        caveatDeceased.setAddress("Winterfell, Westeros");
        caveatDeceased.setAddressFound(false);
        caveatDeceased.setFreeTextAddress("Winterfell, Westeros");
        caveatDeceased.setAddressFound(true);
        caveatDeceased.setPostCode("SW17 0QT");
        caveatDeceased.setAlias(true);
        AliasOtherNames aliasOtherNames = new AliasOtherNames();
        aliasOtherNames.setFirstName("King");
        aliasOtherNames.setLastName("North");
        caveatDeceased.setOtherNames(ImmutableMap.of("name_0", aliasOtherNames));
        caveatForm.setDeceased(caveatDeceased);

        CcdCase ccdCase = new CcdCase();
        ccdCase.setId(1535574519543819L);
        ccdCase.setState("CaseCreated");
        caveatForm.setCcdCase(ccdCase);

        caveatForm.setExpiryDate(LocalDate.of(2020, 2, 2));

        Registry registry = new Registry();
        registry.setName("Birmingham");
        registry.setEmail("birmingham@email.com");
        registry.setAddress("Line 1 Bham\nLine 2 Bham\nLine 3 Bham\nPostCode Bham");
        registry.setSequenceNumber(20075L);
        caveatForm.setRegistry(registry);

        Payment payment = new Payment();
        String dateStr = "2018-12-03T15:58:44.954+0000";
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601);
        payment.setDate(formatter.parse(dateStr));
        payment.setAmount(new BigDecimal("220.5"));
        payment.setSiteId("P223");
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setMethod("online");
        payment.setReference("RC-1543-8527-2465-2900");
        payment.setTransactionId("v5bf26kn5rq9rvdq7gsvn7v11d");
        payment.setAmount(new BigDecimal("220.5"));
        caveatForm.setPayments(Lists.newArrayList(payment));
    }

    @Test
    public void shouldDeserializeIntestacyFormCorrectly() throws IOException {
        Form form = objectMapper.readValue(formJsonFromFile, Form.class);

        assertThat(form, is(equalTo(caveatForm)));
    }

    @Test
    public void shouldSerializeIntestacyFormCorrectly() throws IOException, JSONException {
        String caveatFormAsJsonStr = objectMapper.writeValueAsString(caveatForm);

        JSONAssert.assertEquals(formJsonFromFile, caveatFormAsJsonStr, true);
    }
}
