package uk.gov.hmcts.reform.probate.model.forms.caveat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.PaymentStatus;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Equality;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class CaveatFormTest {

    private ObjectMapper objectMapper;

    private CaveatForm caveatForm;

    private String formJsonFromFile;

    @BeforeEach
    public void setUp() throws Exception {
        formJsonFromFile = TestUtils.getJsonFromFile("caveatForm.json");

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);

        caveatForm = new CaveatForm();
        caveatForm.setType(ProbateType.CAVEAT);

        CaveatApplicant caveatApplicant = new CaveatApplicant();
        caveatApplicant.setEmail("jon.snow@thenorth.com");
        caveatApplicant.setFirstName("Jon");
        caveatApplicant.setLastName("Snow");
        Address caveatAddress = Address.builder()
                .addressLine1("156 Blackshaw Road")
                .postTown("London")
                .postCode("SW17 0QT")
                .build();
        caveatApplicant.setAddress(caveatAddress);
        caveatForm.setApplicant(caveatApplicant);

        CaveatDeceased caveatDeceased = new CaveatDeceased();
        caveatDeceased.setFirstName("Ned");
        caveatDeceased.setLastName("Stark");
        caveatDeceased.setDateOfBirth(LocalDate.of(1930, 1, 1));
        caveatDeceased.setDateOfDeath(LocalDate.of(2018, 1, 1));
        Address deceasedAddress = Address.builder()
                .addressLine1("Winterfell, Westeros")
                .build();
        caveatDeceased.setAddress(deceasedAddress);
        caveatDeceased.setAlias(true);
        AliasOtherNames aliasOtherNames = new AliasOtherNames();
        aliasOtherNames.setFirstName("King");
        aliasOtherNames.setLastName("North");
        caveatDeceased.setOtherNames(ImmutableMap.of("name_0", aliasOtherNames));
        caveatForm.setDeceased(caveatDeceased);

        CcdCase ccdCase = new CcdCase();
        ccdCase.setId(1535574519543819L);
        ccdCase.setState("CaseCreated");
        ccdCase.setLastModifiedDate(LocalDate.of(2018, 1, 1));
        ccdCase.setLastModifiedDateTime(LocalDateTime.of(2018, 1, 1,
                14, 30, 15, 123_000_000));
        caveatForm.setCcdCase(ccdCase);

        caveatForm.setExpiryDate(LocalDate.of(2020, 2, 2));

        Registry registry = new Registry();
        registry.setName("Birmingham");
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

        Equality equality = Equality.builder().pcqId("1000").build();
        caveatForm.setEquality(equality);
    }

    @Test
    void shouldConstructWithEventDescription() {
        CaveatForm caveatForm = new CaveatForm(ProbateType.CAVEAT, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, "eventDescription");

        assertThat(caveatForm.getEventDescription(), is(equalTo("eventDescription")));
    }

    @Test
    void shouldDeserializeCaveatFormCorrectly() throws IOException {
        Form form = objectMapper.readValue(formJsonFromFile, Form.class);

        assertThat(form, is(equalTo(caveatForm)));
    }

    @Test
    void shouldSerializeCaveatFormCorrectly() throws IOException, JSONException {
        String caveatFormAsJsonStr = objectMapper.writeValueAsString(caveatForm);

        JSONAssert.assertEquals(formJsonFromFile, caveatFormAsJsonStr, true);
    }
}
