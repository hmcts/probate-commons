package uk.gov.hmcts.reform.probate.model.forms.pa;

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
import uk.gov.hmcts.reform.probate.model.forms.CcdCase;
import uk.gov.hmcts.reform.probate.model.forms.Form;
import uk.gov.hmcts.reform.probate.model.forms.IhtMethod;
import uk.gov.hmcts.reform.probate.model.forms.InheritanceTax;
import uk.gov.hmcts.reform.probate.model.forms.Payment;
import uk.gov.hmcts.reform.probate.model.forms.Registry;
import uk.gov.hmcts.reform.probate.model.forms.Will;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PaFormTest {

    private ObjectMapper objectMapper;

    private PaForm paForm;

    private String formJsonFromFile;

    @Before
    public void setUp() throws Exception {
        formJsonFromFile = TestUtils.getJsonFromFile("paForm.json");

        objectMapper = new ObjectMapper();
        objectMapper.disable(FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY);

        paForm = PaForm.builder()
                .type(ProbateType.PA)
                .iht(InheritanceTax.builder()
                        .method(IhtMethod.ONLINE)
                        .netValue(new BigDecimal("20000"))
                        .grossValue(new BigDecimal("20000"))
                        .identifier("IHT1234567")
                        .build())
                .will(Will.builder()
                        .codicils(false)
                        .build())
                .copies(ImmutableMap.<String, PaCopies>builder()
                        .put("uk", PaCopies.builder()
                                .cost(BigDecimal.valueOf(0.5))
                                .number(1)
                                .build())
                        .put("overseas", PaCopies.builder()
                                .cost(BigDecimal.valueOf(0))
                                .number(0)
                                .build())
                        .build())
                .ccdCase(CcdCase.builder()
                        .id(1551365512754035L)
                        .state("CaseCreated")
                        .build())
                .payments(Lists.newArrayList(
                        Payment.builder()
                                .date(Date.from(LocalDate.of(2019, 2, 28).atStartOfDay().toInstant(ZoneOffset.UTC)))
                                .amount(BigDecimal.valueOf(215.5))
                                .siteId("P223")
                                .status(PaymentStatus.SUCCESS)
                                .method("online")
                                .reference("RC-1551-3655-7880-7354")
                                .transactionId("kp5i7giksdji0ucuuaktsgt9t7")
                                .build()
                ))
                .summary(Summary.builder()
                        .readToDeclare(true)
                        .build())
                .deceased(PaDeceased.builder()
                        .alias(false)
                        .address("Winterfell, North, Westeros, GOT123")
                        .married(false)
                        .dateOfBirth(LocalDate.of(1900, 1, 1))
                        .dateOfDeath(LocalDate.of(2019, 1, 1))
                        .lastName("Stark")
                        .firstName("Ned")
                        .build())
                .registry(Registry.builder()
                        .name("Manchester")
                        .email("manchester@email.com")
                        .address("Line 1 man\nLine 2 man\nLine 3 man\nPostCode Manchester\n")
                        .sequenceNumber(30000L)
                        .build())
                .applicant(PaApplicant.builder()
                        .alias("King of the North")
                        .aliasReason("Title Given")
                        .email("jon.snow.got1234@gmail.com")
                        .address("The Wall, North, Westeros, GOT567")
                        .lastName("Snow")
                        .firstName("Jon")
                        .phoneNumber("3234324")
                        .nameAsOnTheWill(true)
                        .build())
                .executors(Executors.builder()
                        .list(Lists.newArrayList(
                                Executor.builder()
                                        .lastName("Snow")
                                        .firstName("Jon")
                                        .isApplying(true)
                                        .isApplicant(false)
                                        .build()
                        ))
                        .executorsNumber(1)
                        .build())
                .declaration(PaDeclaration.builder()
                        .softStop(false)
                        .declaration(
                                PaDeclarationDeclaration.builder()
                                        .accept("I confirm that I will administer the estate of the person who died "
                                                + "according to law, and that my application is truthful.")
                                        .confirm("I confirm that I will administer the estate of Ned Stark, "
                                                + "according to law. I will:")
                                        .requests("If the probate registry (court) asks me to do so, I will:")
                                        .understand("I understand that:")
                                        .confirmItem1("collect the whole estate")
                                        .confirmItem2("keep full details (an inventory) of the estate")
                                        .confirmItem3("keep a full account of how the estate has been administered")
                                        .requestsItem1("provide the full details of the estate "
                                                + "and how it has been administered")
                                        .requestsItem2("return the grant of probate to the court")
                                        .submitWarning("Once you click &lsquo;Save and continue&rsquo;, "
                                                + "you cannot change your answers.")
                                        .understandItem1("my application will be rejected if I do not answer "
                                                + "any questions about the information I have given")
                                        .understandItem2("criminal proceedings for fraud may be brought against "
                                                + "me if I am found to have been deliberately untruthful or dishonest")
                                        .build()
                        )
                        .legalStatement(PaLegalStatement.builder()
                                .intro("This statement is based on the information you&rsquo;ve given "
                                        + "in your application. It will be stored as a public record.")
                                .deceased("Ned Stark was born on 1 January 1900 and died on 1 January 2019, "
                                        + "domiciled in England and Wales.")
                                .applicant("I, Jon Snow of The Wall, North, Westeros, GOT567, "
                                        + "make the following statement:")
                                .executorsApplying(Lists.newArrayList(
                                        PaLegalStatementExecutorApplying.builder()
                                                .name("I am an executor named in the will as Jon Snow, "
                                                        + "and I am applying for probate.")
                                                .sign("I will send to the probate registry what I believe to be the "
                                                        + "true and original last will and testament of Ned Stark.")
                                                .build()
                                ))
                                .deceasedEstateLand("To the best of my knowledge, information and belief, "
                                        + "there was no land vested in Ned Stark which was settled previously to "
                                        + "the death (and not by the will) of Ned Stark and which remained settled "
                                        + "land notwithstanding such death.")
                                .deceasedOtherNames("")
                                .deceasedEstateValue("The gross value for the estate amounts to £20000 and the "
                                        + "net value for the estate amounts to £20000.")
                                .executorsNotApplying(Lists.newArrayList())
                                .build())
                        .hasEmailChanged(false)
                        .declarationCheckbox(true)
                        .build())
                .paymentPending(false)
                .creatingPayment(false)
                .build();


    }

    @Test
    public void shouldDeserializePaFormCorrectly() throws IOException {
        Form form = objectMapper.readValue(formJsonFromFile, Form.class);

        assertThat(form, is(equalTo(paForm)));
    }

    @Test
    public void shouldSerializePaFormCorrectly() throws IOException, JSONException {
        String paFormAsJsonStr = objectMapper.writeValueAsString(paForm);
        System.out.println(paFormAsJsonStr);
        JSONAssert.assertEquals(formJsonFromFile, paFormAsJsonStr, true);
    }

}