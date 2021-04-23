package uk.gov.hmcts.reform.probate.model.payments;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.payments.FeeDto;
import uk.gov.hmcts.reform.probate.model.payments.PaymentDto;
import uk.gov.hmcts.reform.probate.model.payments.PaymentDto.LinkDto;
import uk.gov.hmcts.reform.probate.model.payments.PaymentDto.LinksDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PaymentDtoTest {

    private ObjectMapper objectMapper;

    private String jsonFromFile;
    
    private String jsonSerializedVersionFromFile;
    
    private PaymentDto paymentDto;
    
    private static String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    
    private static PaymentDto getTestPaymentUpdateObject() throws Exception {
        final FeeDto fee1 = new FeeDto();
        fee1.setCode("FEE0226");
        fee1.setVersion("1");
        fee1.setVolume(Integer.valueOf("1"));
        fee1.setCalculatedAmount(new BigDecimal("215.00"));
        
        final FeeDto fee2 = new FeeDto();
        fee2.setCode("FEE0003");
        fee2.setVersion("3");
        fee2.setVolume(Integer.valueOf("2"));
        fee2.setCalculatedAmount(new BigDecimal("1.00"));
        
        final FeeDto fee3 = new FeeDto();
        fee3.setCode("FEE0003");
        fee3.setVersion("3");
        fee3.setVolume(Integer.valueOf("1"));
        fee3.setCalculatedAmount(new BigDecimal("0.50"));
        
        LinkDto self = new LinkDto();
        self.setHref("http://payment.service.gov.uk");
        self.setMethod("GET");
        LinksDto links = new LinksDto();
        links.setSelf(self);
        
        DateFormat jsonDateFormat = new SimpleDateFormat(JSON_DATE_FORMAT);
        Date dateCreated = jsonDateFormat.parse("2019-05-13T10:06:45.285+0000");
        Date dateUpdated = jsonDateFormat.parse("2019-05-13T10:07:20.123+0000");
                
        final PaymentDto paymentDtoData = new PaymentDto();
        paymentDtoData.setAmount(new BigDecimal("216.50"));
        paymentDtoData.setDescription("Probate payment system update for CCD Grant of Representation.");
        paymentDtoData.setReference("RC-1554-3862-1252-2651");
        paymentDtoData.setCurrency("GBP");
        paymentDtoData.setCcdCaseNumber("1554131023277701");
        paymentDtoData.setChannel("online");
        paymentDtoData.setMethod("card");
        paymentDtoData.setExternalProvider("gov pay");
        paymentDtoData.setStatus("success");
        paymentDtoData.setExternalReference("06kd1v30vm45hqvggphdjqbeqa");
        paymentDtoData.setDateCreated(new Date());
        paymentDtoData.setSiteId("P223");
        paymentDtoData.setLinks(links);
        paymentDtoData.setDateCreated(dateCreated);
        paymentDtoData.setDateUpdated(dateUpdated);
        
        List<FeeDto> fees = Arrays.asList(fee1, fee2, fee3);
        paymentDtoData.setFees(fees);

        return paymentDtoData;
    }

    @Before
    public void setUp() throws Exception {
        jsonFromFile = TestUtils.getJsonFromFile("paymentDto.json");
        jsonSerializedVersionFromFile = TestUtils.getJsonFromFile("paymentDto-serialized.json");
        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);
        paymentDto = getTestPaymentUpdateObject();
    }

    @Test
    public void shouldDeserializePaymentUpdateCorrectly() throws IOException {
        PaymentDto paymentDtoData = objectMapper.readValue(jsonFromFile, PaymentDto.class);

        assertThat(paymentDto, is(equalTo(paymentDtoData)));
    }

    @Test
    public void shouldSerializePaymentUpdateCorrectly() throws IOException, JSONException {
        String json = objectMapper.writeValueAsString(paymentDto);

        JSONAssert.assertEquals(jsonSerializedVersionFromFile, json, true);
    }

}
