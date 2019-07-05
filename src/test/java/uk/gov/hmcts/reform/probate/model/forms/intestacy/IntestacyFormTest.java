package uk.gov.hmcts.reform.probate.model.forms.intestacy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import uk.gov.hmcts.reform.probate.model.IhtFormType;
import uk.gov.hmcts.reform.probate.model.PaymentStatus;
import uk.gov.hmcts.reform.probate.model.ProbateType;
import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.TestUtils;
import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;
import uk.gov.hmcts.reform.probate.model.forms.Address;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Copies;
import uk.gov.hmcts.reform.probate.model.forms.Declaration;
import uk.gov.hmcts.reform.probate.model.forms.IhtMethod;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.util.StdDateFormat.DATE_FORMAT_STR_ISO8601;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IntestacyFormTest {

    private ObjectMapper objectMapper;

    private IntestacyForm intestacyForm;

    private String formJsonFromFile;

    @Before
    public void setUp() throws Exception {
        formJsonFromFile = TestUtils.getJsonFromFile("intestacyForm.json");

        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);

        intestacyForm = new IntestacyForm();
        intestacyForm.setType(ProbateType.INTESTACY);
        intestacyForm.setUploadDocumentUrl("http://document-management/document/12345");
        intestacyForm.setApplicantEmail("jon.snow@thenorth.com");

        IntestacyApplicant intestacyApplicant = new IntestacyApplicant();
        intestacyApplicant.setAddressFound(true);
        intestacyApplicant.setFirstName("Jon");
        intestacyApplicant.setLastName("Snow");
        Address applicantAddress = Address.builder().addressLine1("Pret a Manger")
                .addressLine2("St. Georges Hospital")
                .addressLine3("Blackshaw Road")
                .postTown("London")
                .postCode("SW17 0QT")
                .formattedAddress("Pret a Manger St. Georges Hospital Blackshaw Road London SW17 0QT")
                .build();
        intestacyApplicant.setAddress(applicantAddress);
        intestacyApplicant.setPostCode("SW17 0QT");
        intestacyApplicant.setPhoneNumber("123455678");
        intestacyApplicant.setAdoptionInEnglandOrWales(true);
        intestacyApplicant.setRelationshipToDeceased(Relationship.ADOPTED_CHILD);
        intestacyForm.setApplicant(intestacyApplicant);

        IntestacyDeceased intestacyDeceased = new IntestacyDeceased();
        intestacyDeceased.setFirstName("Ned");
        intestacyDeceased.setLastName("Stark");
        intestacyDeceased.setDateOfBirth(LocalDateTime.of(1930, 1, 1,0,0,0));
        intestacyDeceased.setDateOfDeath(LocalDateTime.of(2018, 1, 1,0,0,0));
        Address deceasedAddress = Address.builder().addressLine1("Winterfell")
                .postTown("Kings Landing")
                .formattedAddress("Winterfell Kings Landing Win1 Westeros")
                .postCode("Win1").country("Westeros").build();
        intestacyDeceased.setAddress(deceasedAddress);
        intestacyDeceased.setAddressFound(true);
        intestacyDeceased.setPostCode("SW17 0QT");
        intestacyDeceased.setAlias(true);
        intestacyDeceased.setDomiciledInEnglandOrWales(true);
        AliasOtherNames aliasOtherNames = new AliasOtherNames();
        aliasOtherNames.setFirstName("King");
        aliasOtherNames.setLastName("North");
        intestacyDeceased.setOtherNames(ImmutableMap.of("name_0", aliasOtherNames));
        intestacyDeceased.setDivorcedInEnglandOrWales(false);
        intestacyDeceased.setSpouseNotApplyingReason(SpouseNotApplyingReason.MENTALLY_INCAPABLE);
        intestacyDeceased.setMaritalStatus(MaritalStatus.MARRIED);
        intestacyDeceased.setAllDeceasedChildrenOverEighteen(true);
        intestacyDeceased.setAnyDeceasedChildrenDieBeforeDeceased(false);
        intestacyDeceased.setAnyDeceasedGrandchildrenUnderEighteen(false);
        intestacyDeceased.setAnyChildren(false);
        intestacyDeceased.setOtherChildren(true);

        intestacyDeceased.setAddresses(getAddresses());
        intestacyForm.setDeceased(intestacyDeceased);

        InheritanceTax inheritanceTax = new InheritanceTax();
        inheritanceTax.setForm(IhtFormType.IHT205);
        inheritanceTax.setMethod(IhtMethod.ONLINE);
        inheritanceTax.setNetValue(new BigDecimal("100000"));
        inheritanceTax.setGrossValue(new BigDecimal("100000"));
        inheritanceTax.setIdentifier("GOT123456");
        inheritanceTax.setAssetsOutsideNetValue(new BigDecimal("100.50"));
        inheritanceTax.setAssetsOutside(true);
        intestacyForm.setIht(inheritanceTax);

        Copies copies = new Copies();
        copies.setOverseas(6L);
        copies.setUk(5L);
        intestacyForm.setCopies(copies);

        CcdCase ccdCase = new CcdCase();
        ccdCase.setId(1535574519543819L);
        ccdCase.setState("CaseCreated");
        intestacyForm.setCcdCase(ccdCase);

        Registry registry = new Registry();
        registry.setName("Birmingham");
        registry.setEmail("birmingham@email.com");
        registry.setAddress("Line 1 Bham\nLine 2 Bham\nLine 3 Bham\nPostCode Bham");
        registry.setSequenceNumber(20075L);
        intestacyForm.setRegistry(registry);

        Declaration intestacyDeclaration = Declaration.builder().declarationCheckbox(true).build();
        intestacyForm.setDeclaration(intestacyDeclaration);

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
        intestacyForm.setPayments(Lists.newArrayList(payment));
    }

    private List<Map<String, Object>> getAddresses() {
        Map<String, Object> address1 = new HashMap<>();
        address1.put("building_number", "102");
        address1.put("organisation_name", "MINISTRY OF JUSTICE");
        address1.put("post_town", "LONDON");
        address1.put("postcode", "SW1H 9AJ");
        address1.put("sub_building_name", "SEVENTH FLOOR");
        address1.put("thoroughfare_name", "PETTY FRANCE");
        address1.put("uprn", "10033604583");
        address1.put("formatted_address", "Ministry of Justice\nSeventh Floor\n102 Petty France\nLondon\nSW1H 9AJ");

        Map<String, Object> address2 = new HashMap<>();
        address2.put("building_number", "103");
        address2.put("organisation_name", "MINISTRY OF JUSTICE");
        address2.put("post_town", "LONDON");
        address2.put("postcode", "SW1H 9AJ");
        address2.put("sub_building_name", "SEVENTH FLOOR");
        address2.put("thoroughfare_name", "PETTY FRANCE");
        address2.put("uprn", "10033604583");
        address2.put("formatted_address", "Ministry of Justice\nSeventh Floor\n103 Petty France\nLondon\nSW1H 9AJ");

        List<Map<String,Object>> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);
        return addressList;
    }

    @Test
    public void shouldDeserializeIntestacyFormCorrectly() throws IOException {
        IntestacyForm form = objectMapper.readValue(formJsonFromFile, IntestacyForm.class);

        assertThat(form, is(equalTo(intestacyForm)));
    }

    @Test
    public void shouldSerializeIntestacyFormCorrectly() throws IOException, JSONException {
        String intestacyFormAsJsonStr = objectMapper.writeValueAsString(intestacyForm);

        JSONAssert.assertEquals(formJsonFromFile, intestacyFormAsJsonStr, true);
    }
}
