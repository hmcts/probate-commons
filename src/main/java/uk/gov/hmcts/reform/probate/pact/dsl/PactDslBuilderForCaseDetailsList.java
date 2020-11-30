package uk.gov.hmcts.reform.probate.pact.dsl;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import io.pactfoundation.consumer.dsl.LambdaDslObject;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;

public final class PactDslBuilderForCaseDetailsList {

    private PactDslBuilderForCaseDetailsList(){

    }

    public static final String REGEX_DATE = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    private static final String ALPHABETIC_REGEX = "[/^[A-Za-z_]+$/]+";

    public static DslPart buildStartEventReponse(String eventId, String token, String emailAddress,
                                                 boolean withExecutors, boolean isWelsh, boolean withPayments) {
        return newJsonBody((o) -> {
            o.stringType("event_id", eventId)
                .stringType("token", token)
                .object("case_details", (cd) -> {
                    cd.numberType("id", 2000);
                    cd.stringMatcher("jurisdiction",  ALPHABETIC_REGEX,"DIVORCE");
                    cd.stringMatcher("callback_response_status", ALPHABETIC_REGEX,  "DONE");
                    cd.stringMatcher("case_type", ALPHABETIC_REGEX,  "GRANT_OF_REPRESENTATION");
                    //Map<Object,Object> data property  of  CaseDetails object.
                    cd.object("data", (dataMap) -> {
                        getCaseData(emailAddress, withExecutors,isWelsh ,  withPayments, dataMap);
                    });
                });
        }).build();
    }

    private static void getCaseData(final String emailAddress, final boolean withExecutors, boolean isWelsh ,final boolean withPayments,
                                                                                        final LambdaDslObject dataMap) {
        dataMap.numberType("outsideUKGrantCopies", 6)
                .stringValue("applicationType", "Personal")
                .stringMatcher("applicationSubmittedDate", REGEX_DATE, "2018-05-17")
                .minArrayLike("payments", 1, 1, payment -> payment
                                .stringType("id", "950243f2-c713-4f1b-990d-83eb508bab91")
                                .object("value", (value) ->
                                        value.stringMatcher("amount", "[0-9]+", "2000")
                                                .stringType("method", "card")
                                                .stringMatcher("status", "Success|Failed|Initiated|not_required",
                                                        "Success")
                                                .stringType("reference", "RC-1599-4876-0252-6208")
                                ))
                .stringMatcher("softStop", "Yes|No", "No")
                .stringMatcher("ihtFormId", "IHT205|IHT207|IHT400421", "IHT205")
                .object("declaration", (declaration) ->
                        declaration.stringType("accept", "I confirm that I will administer the estate of the "
                                + "person who died according to law, and that my application is truthful.")
                                .stringType("confirm", "We confirm that we will administer the estate of Suki Hammond,"
                                        + "according to law. We will:")
                                .stringType("confirmItem1", "collect the whole estate")
                                .stringType("confirmItem2", "keep full details (an inventory) of the "
                                        + "estate")
                                .stringType("confirmItem3", "keep a full account of how the estate has been "
                                        + "administered")
                                .stringType("requests", "If the probate registry (court) asks us to do so, we will:")
                                .stringType("requestsItem1", "provide the full details of the estate "
                                        + "and how it has been administered")
                                .stringType("requestsItem2", "return the grant of probate to the court")
                                .stringType("understand", "We understand that:")
                                .stringType("understandItem1", "our application will be rejected if we "
                                        + "do not answer any questions about the information we have given")
                                .stringType("understandItem2", "criminal proceedings for fraud may "
                                        + "be brought against us "
                                        + "if we are found to have been deliberately untruthful or dishonest"))
                .stringType("ihtNetValue", "100000")
                .stringType("ihtGrossValue", "200000")
                .object("legalStatement", (legalStatement) -> legalStatement
                    .stringType("intro", "This statement is based on the information Applicant "
                       + "fn Applicant ln has given in their application. It will be stored as a public record.")
                    .stringType("deceased", "Ned Stark was born on 6 October 1902 and died "
                       + "on 22 December 1983, domiciled in England and Wales.")
                    .stringType("applicant", "We, Jon Snow of place make the following statement:")
                    .minArrayLike("executorsApplying", 1, 1, executorApplying -> executorApplying
                                    .object("value", (value) -> value
                                        .stringType("name", "Jon Snow, an executor named in the "
                                                + "will or codicils, is applying for probate.")
                                        .stringType("sign", "Jon Snow will sign and send to the "
                                           + "probate registry what they believe to be the true and original last "
                                           + "will and testament and any codicils of Ned Stark.")))
                    .stringType("deceasedEstateLand", "To the best of our knowledge, information "
                       + "and belief, there was no land vested in Suki Hammond which was "
                            + "settled previously to the death (and not by the will) of "
                            + "Suki Hammond and which remained settled land notwithstanding such death.")
                    .stringType("deceasedEstateValue", "The gross value for the estate amounts "
                                    + "to &pound;2222 and the net value for the estate amounts to &pound;222.")
                    .stringType("deceasedOtherNames", "They were also known as King North.")
                    .minArrayLike("executorsNotApplying", 1, 1, executorNotApplying -> executorNotApplying
                            .stringType("id", "e307252d-215c-442a-ad27-309de10faae7")
                            .object("value", (value) -> value
                              .stringType("executor", "Burton Leonard, "
                                  + "an executor named in the will or codicils, is not making this "
                                  + "application because they died before Suki Hammond died."))))

                .stringType("applicationType", "Personal")
                .object("deceasedAddress", (address) ->
                        address.stringType("AddressLine1", "Winterfell")
                                .stringType("AddressLine2", "Westeros")
                                .stringType("PostTown", "London")
                                .stringType("PostCode", "SW17 0QT"))
                .stringType("deceasedSurname", "Stark")
                .stringType("registryAddress", "Line 1 Ox\nLine 2 Ox\nLine 3 Ox\nPostCode Ox\n")
                .stringType("willHasCodicils", "No")
                .stringType("ihtNetValueField", "1000000")
                .stringType("registryLocation", "ctsc")
//                .object("welshDeclaration", (declaration) -> declaration
//                               .stringType("accept", "Rwy&rsquo;n cadarnhau y byddaf yn gweinyddu "
//                                 + " ystad yr unigolyn sydd wedi"
//                                 + " marw yn unol &acirc;&rsquo;r gyfraith, a bod fy  nghais yn gywir.")
//                               .stringType("confirm", "Rydym yn cadarnhau y byddwn yn gweinyddu ystad "
//                                        + "Deceased fn Deceased ln, yn unol &acirc;&rsquo;r gyfraith. Byddwn yn:")
//                               .stringType("requests", "Os bydd y gofrestrfa brofiant (y llys) yn "
//                                        + "gofyn inni byddwn yn:")
//                               .stringType("understand", "Rydym yn deall:")
//                               .stringType("confirmItem1", "Rydym yn deall:")
//                               .stringType("confirmItem2", "cadw manylion llawn (rhestr) yr ystad")
//                               .stringType("confirmItem3", "cadw cofnod llawn o&rsquo;r ffordd y mae&rsquo;r "
//                                        + "ystad wedi cael ei gweinyddu")
//                               .stringType("requestsItem1", "darparu manylion llawn yr ystad a&rsquo;r "
//                                        + "ffordd y cafodd ei gweinyddu")
//                               .stringType("requestsItem2", "dychwelyd y grant profiant i&rsquo;r llys")
//                               .stringType("submitWarning", "Unwaith y bydd pawb wedi cytuno i&rsquo;r "
//                                + "datganiad cyfreithiol ac wedi gwneud eu datganiad, ni ellir newid yr wybodaeth hon.")
//                               .stringType("understandItem1", "y bydd ein cais yn cael ei wrthod os na "
//                                + "fyddwn yn ateb unrhyw gwestiynau am yr wybodaeth rydym wedi&rsquo;i rhoi")
//                               .stringType("understandItem2", "y gellir dwyn achos troseddol "
//                                        + "yn ein herbyn am dwyll os penderfynir ein bod heb ddweud y gwir "
//                                        + "neu wedi bod yn anonest yn fwriadol"))
                .stringType("deceasedForenames", "Ned")
                .stringType("executorsAllAlive")
//                .minArrayLike("executorsApplying", 1, 1, executorApplying -> executorApplying
//                                .stringType("id", "b4c0e385-193e-4195-9657-a1091df50b28")
//                                .object("value", (value) -> value
//                                        .stringType("applyingExecutorName", "Exec4 Name")
//                                        .stringType("applyingExecutorEmail", "bruno.cypress@outree.org")
//                                        .stringType("applyingExecutorAgreed", "Yes")
//                                        .object("applyingExecutorAddress", (address) -> address
//                                               .stringType("Country", "United Kingdom")
//                                               .stringType("PostCode", "SW17 0QT")
//                                               .stringType("PostTown", "London")
//                                               .stringType("AddressLine1", "Westeros"))
//                                               .stringType("applyingExecutorPhoneNumber", "07769351102")
//                                               .stringType("applyingExecutorInvitationId", "inviteId")))
                .stringType("numberOfExecutors", "8")
                .stringType("executorsHaveAlias", "No")
                .stringType("extraCopiesOfGrant", "4")
                .stringType("ihtReferenceNumber", "ref")
                .stringType("numberOfApplicants", "2")
                .minArrayLike("boDocumentsUploaded", 1, 1, boDocumentsUploaded -> boDocumentsUploaded.stringType("docs"))
                .stringMatcher("deceasedDateOfBirth", REGEX_DATE, "1930-01-01")
                .stringMatcher("deceasedDateOfDeath", REGEX_DATE, "2018-01-01")
                .stringType("declarationCheckbox", "Yes")
//                .object("welshLegalStatement", welshLegalStatement -> welshLegalStatement
//                        .stringType("intro", "Mae&rsquo;r datganiad hwn wedi&rsquo;i seilio ar yr "
//                                + "wybodaeth y mae Applicant fn Applicant ln wedi&rsquo;i rhoi yn eu cais. Bydd "
//                                + "yn cael ei storio fel cofnod cyhoeddus.")
//                        .stringType("deceased", "Deceased fn Deceased ln ar undefined a bu farw ar "
//                                + "undefined, ac roedd fel arfer yn preswylio yng Nghymru a Lloegr.")
//                        .stringType("applicant", "Rydym ni, Applicant fn Applicant ln o Wells "
//                                + "Cathedral Wells BA5 2PA United Kingdom, Exec4 Name o Wells Cathedral Wells BA5 2PA "
//                          + "United Kingdom, Exec5 Name o Wells Cathedral Wells BA5 2PA United Kingdom a/ac Exec6 Name "
//                                + "o Wells Cathedral Wells BA5 2PA United Kingdom, yn gwneud y datganiad canlynol:")
//                        .minArrayLike("executorsApplying", 1, 1,
//                                executorsApplying -> executorsApplying
//                                        .stringType("id", "b4c0e385-193e-4195-9657-a1091df50b28")
//                                        .object("value", value -> value
//                                          .stringType("name", "Mae Applicant fn Applicant ln, ysgutor a "
//                                                 + "enwir yn yr ewyllys, yn gwneud cais am brofiant.")
//                                          .stringType("sign", "Bydd Applicant fn Applicant ln yn anfon "
//                                            + "i&rsquo;r Gofrestrfa Brofiant yr hyn yr ydym wedi&rsquo;i weld "
//                                            + "a&rsquo;i gredu i fod yn ewyllys a thestament gwir a gwreiddiol "
//                                            + "olaf Deceased fn Deceased ln."))
//                        )
//                        .stringType("deceasedEstateLand", "Hyd eithaf ein gwybodaeth a&rsquo;n cred, nid "
//                         + "oedd unrhyw dir wedi&rsquo;i freinio yn Deceased fn Deceased ln a setlwyd cyn marwolaeth "
//                         + "(ac nid drwy ewyllys) Deceased fn Deceased ln ac a oedd dal yn dir a setlwyd er "
//                         + "gwaethaf y farwolaeth honno.")
//                        .stringType("deceasedOtherNames", "")
//                        .stringType("deceasedEstateValue", "Mae gwerth gros yr ystad yn "
//                                + "&pound;2000000.00 ac mae gwerth net yr ystad yn &pound;1000000.00.")
//                        .minArrayLike("executorsNotApplying", 1, 1, executorsNotApplying -> executorsNotApplying
//                        .stringType("id", "cb62d566-b252-4b8d-abd8-da6eedc26c26")
//                        .object("value", value -> value
//                        .stringType("executor", "executor:Nid yw Exec2 Name, ysgutor a enwir yn "
//                         + "yr ewyllys, yn gwneud y cais hwn oherwydd bu iddynt farw cyn i Deceased "
//                                + "fn Deceased ln farw."))))
//                .minArrayLike("executorsNotApplying", 1, 1, executorNotApplying -> executorNotApplying
//                        .stringType("id", "f803c7f0-d123-476d-acd5-7c736b3d76af")
//                        .object("value", (value) -> value
//                            .stringType("notApplyingExecutorName", "Burton Leonard")
//                            .stringType("notApplyingExecutorIsDead", "Yes")
//                            .stringType("notApplyingExecutorReason", "DiedBefore")
//                            .stringType("notApplyingExecutorDiedBefore", "Yes")))
                .stringType("legalDeclarationJson", "\"{\\\"declarations\\\":[{\\\"headers\\\":"
                            + "[\\\"In the High Court of Justice\\\",\\\"Family Division\\\",\\\"(Probate)\\\"],"
                            + "\\\"sections\\\":[{\\\"headingType\\\":\\\"large\\\",\\\"title\\\":\\\"Legal "
                            + "statement\\\",\\\"declarationItems\\\":[{\\\"title\\\":\\\"We, "
                            + "Applicant fn Applicant ln of Wells Cathedral Wells BA5 2PA United Kingdom, "
                            + "Exec4 Name of Wells Cathedral Wells BA5 2PA United Kingdom, Exec5 Name "
                            + "of Wells Cathedral Wells BA5 2PA United Kingdom and Exec6 Name of Wells "
                            + "Cathedral Wells BA5 2PA United Kingdom, make the following "
                            + "statement:\\\"}]},{\\\"headingType\\\":\\\"small\\\",\\\"title\\\":"
                            + "\\\"The person who died\\\",\\\"declarationItems\\\":[{\\\"title\\\":"
                            + "\\\"Deceased fn Deceased ln was born on 1 January 1950 and died on 1 "
                            + "January 2020, domiciled in England and Wales. \\\"}]},"
                            + "{\\\"headingType\\\":\\\"small\\\",\\\"title\\\":\\\"The "
                            + "estate of the person who died\\\",\\\"declarationItems\\\":[{\\\"title\\\":"
                            + "\\\"The gross value for the estate amounts to £2000000.00 and the net value"
                            + "for the estate amounts to £1000000.00.\\\"},{\\\"title\\\":\\\"To the best "
                            + "of our knowledge, information and belief, there was no land vested in "
                            + "Deceased fn Deceased ln which was settled previously to the death "
                            + "(and not by the will) of Deceased fn Deceased ln and which remained settled "
                            + "land notwithstanding such death.\\\"}]},{\\\"headingType\\\":\\\"small\\\","
                            + "\\\"title\\\":\\\"Executors applying for probate\\\",\\\"declarationItems\\\":"
                            + "[{\\\"title\\\":\\\"Applicant fn Applicant ln, an executor named in the will, "
                            + "is applying for probate.\\\"},{\\\"title\\\":\\\"Applicant fn Applicant ln "
                            + "will send to the probate registry what we have seen and believe to be the "
                            + "true and original last will and testament of Deceased fn Deceased ln.\\\"},"
                            + "{\\\"title\\\":\\\"Exec4 Name, an executor named in the will, is applying "
                            + "for probate.\\\"},{\\\"title\\\":\\\"Exec5 Name, an executor named in the will,"
                            + " is applying for probate.\\\"},{\\\"title\\\":\\\"Exec6 Name, an executor named "
                            + "in the will, is applying for probate.\\\"}]},{\\\"headingType\\\":\\\"small\\\","
                            + "\\\"title\\\":\\\"Executors not applying for probate\\\",\\\"declarationItems\\\":"
                            + "[{\\\"title\\\":\\\"Exec2 Name, an executor named in the will, is not making this "
                            + "application because they died before Deceased fn Deceased ln died.\\\"},{\\\"title"
                            + "\\\":\\\"Exec3 Name, an executor named in the will, is not making this application "
                            + "because they died after Deceased fn Deceased ln died.\\\"},{\\\"title\\\":\\\"Exec7 "
                            + "Name, an executor named in the will, is not making this application but reserves "
                            + "power to do so at a later date. They have been notified in writing.\\\"},{\\\"title"
                            + "\\\":\\\"Exec8 Name, an executor named in the will, is not making this application "
                            + "now and gives up the right to do so in the future.\\\"}]},{\\\"headingType\\\":\\\""
                            + "large\\\",\\\"title\\\":\\\"Declaration\\\",\\\"declarationItems\\\":[{\\\"title\\\""
                            + ":\\\"We confirm that we will administer the estate of Deceased fn Deceased ln, "
                            + "according to law. We will:\\\",\\\"values\\\":[\\\"collect the whole estate\\\","
                            + "\\\"keep full details (an inventory) of the estate\\\",\\\"keep a full account of "
                            + "how the estate has been administered\\\"]},{\\\"title\\\":\\\"If the probate "
                            + "registry (court) asks us to do so, we will:\\\",\\\"values\\\":[\\\"provide the full"
                            + " details of the estate and how it has been administered\\\",\\\"return the grant of "
                            + "probate to the court\\\"]},{\\\"title\\\":\\\"We understand that:\\\",\\\"values"
                            + "\\\":[\\\"our application will be rejected if we do not answer any questions about "
                            + "the information we have given\\\",\\\"criminal proceedings for fraud may be brought "
                            + "against us if we are found to have been deliberately untruthful or "
                            + "dishonest\\\"]}]}]}],\\\"dateCreated\\\":\\\"9/7/2020, 2:54:36 PM\\\","
                            + "\\\"deceased\\\":\\\"Deceased fn Deceased ln\\\"}\"")
                .stringType("outsideUKGrantCopies", "0")
                .stringType("registryEmailAddress", "ctsc@email.com")
                .minArrayLike("deceasedAliasNameList", 0, 0, deceasedAliasNameList ->
                                deceasedAliasNameList.stringType("names"))
                .stringType("deceasedAnyOtherNames", "No")
                .stringType("ihtFormCompletedOnline", "Yes")
                .stringType("otherExecutorsApplying", "Yes")
                .stringType("registrySequenceNumber", "3")
                .stringType("checkAnswersSummaryJson", "\"{\\\"sections\\\":[{\\\"title\\\":\\\""
                     + "About the person who died\\\",\\\"type\\\":\\\"govuk-heading-m\\\",\\\"questionAndAnswers"
                     + "\\\":[{\\\"question\\\":\\\"Do you require a bilingual grant in English and Welsh?\\\",\\\""
                     + "answers\\\":[\\\"No\\\"]},{\\\"question\\\":\\\"First name and any middle names\\\",\\\""
                     + "answers\\\":[\\\"Deceased fn\\\"]},{\\\"question\\\":\\\"Last name\\\",\\\"answers\\\":"
                     + "[\\\""
                     + "Deceased ln\\\"]},{\\\"question\\\":\\\"Did Deceased fn Deceased ln have assets in another "
                     + "name?\\\",\\\"answers\\\":[\\\"No\\\"]},{\\\"question\\\":\\\"Did Deceased fn Deceased ln "
                     + "get married or enter into a civil partnership after the will was signed?\\\",\\\""
                     + "answers\\\""
                     + ":[\\\"No\\\"]},{\\\"question\\\":\\\"What was their date of birth?\\\",\\\"answers\\\":"
                     + "[\\\""
                     + "1 January 1950\\\"]},{\\\"question\\\":\\\"What was the date that they "
                     + "died?\\\",\\\"answers"
                     + "\\\":[\\\"1 January 2020\\\"]},{\\\"question\\\":\\\"What was the permanent address at the "
                     + "time of their death?\\\",\\\"answers\\\":[\\\"Wells Cathedral Wells BA5 2PA United Kingdom"
                     + "\\\"]},{\\\"question\\\":\\\"Were any updates (‘codicils’) made to the will?\\\","
                     + "\\\"answers"
                     + "\\\":[\\\"No\\\"]}]},{\\\"title\\\":\\\"Uploaded documents\\\",\\\"type\\\":\\\"govuk-"
                     + "heading-m\\\",\\\"questionAndAnswers\\\":[{\\\"question\\\":\\\"Death Certificate\\\","
                     + "\\\"answers\\\":[\\\"No documents have been uploaded\\\"]}]},{\\\"title\\\":\\\""
                     + "Inheritance "
                     + "tax\\\",\\\"type\\\":\\\"govuk-heading-m\\\",\\\"questionAndAnswers\\\":[{\\\"question\\\":"
                     + "\\\"How was the Inheritance Tax (IHT) form submitted?\\\",\\\"answers\\\":[\\\"Through the "
                     + "HMRC online service\\\"]},{\\\"question\\\":\\\"Inheritance Tax identifier (IHT)\\\",\\\""
                     + "answers\\\":[\\\"IHT123412341234\\\"]},{\\\"question\\\":\\\"Gross value of the estate in "
                     + "£\\\",\\\"answers\\\":[\\\"2000000\\\"]},{\\\"question\\\":\\\"Net value of the estate in "
                     + "£\\\",\\\"answers\\\":[\\\"1000000\\\"]}]},{\\\"title\\\":\\\"The executors\\\",\\\""
                     + "type\\\""
                     + ":\\\"govuk-heading-m\\\",\\\"questionAndAnswers\\\":[{\\\"question\\\":\\\"How many "
                     + "past and"
                     + " present executors are named on the will and any updates (‘codicils’)?\\\",\\\""
                     + "answers\\\":["
                     + "\\\"8\\\"]},{\\\"question\\\":\\\"Are all the executors alive?\\\",\\\"answers\\\":[\\\"No"
                     + "\\\"]},{\\\"question\\\":\\\"Which executors have died?\\\",\\\"answers\\\":[\\\"Exec2 Name"
                     + "\\\",\\\"Exec3 Name\\\"]},{\\\"question\\\":\\\"Did Exec2 Name die before the person who "
                     + "died?\\\",\\\"answers\\\":[\\\"Yes\\\"]},{\\\"question\\\":\\\"Did Exec3 Name die "
                     + "before the"
                     + "person who died?\\\",\\\"answers\\\":[\\\"No\\\"]}]},{\\\"title\\\":\\\"About you\\\",\\\""
                     + "type\\\":\\\"govuk-heading-s\\\",\\\"questionAndAnswers\\\":[{\\\"question\\\":\\\"First "
                     + "name and any middle names\\\",\\\"answers\\\":[\\\"Applicant fn\\\"]},{\\\""
                     + "question\\\":\\\""
                     + "Last name\\\",\\\"answers\\\":[\\\"Applicant ln\\\"]},{\\\"question\\\":\\\"Is your name ‘"
                     + "Applicant fn Applicant ln’ exactly what appears on the will?\\\",\\\"answers\\\":[\\\""
                     + "Yes\\\"]},{\\\"question\\\":\\\"Phone number\\\",\\\"answers\\\":[\\\"1234512345123\\\"]},"
                     + "{\\\"question\\\":\\\"What is your address?\\\",\\\"answers\\\":[\\\"Wells Cathedral Wells "
                     + "BA5 2PA United Kingdom\\\"]}]},{\\\"title\\\":\\\"Other executors applying for probate\\\","
                     + "\\\"type\\\":\\\"govuk-heading-s\\\",\\\"questionAndAnswers\\\":[{\\\""
                     + "question\\\":\\\"Will "
                     + "any of the other executors be dealing with the estate?\\\",\\\"answers\\\":[\\\"Yes\\\"]},{"
                     + "\\\"question\\\":\\\"Which executors will be dealing with the estate?\\\",\\\"answers\\\":["
                     + "\\\"Exec4 Name\\\",\\\"Exec5 Name\\\",\\\"Exec6 Name\\\"]}]},{\\\"title\\\":\\\"Second "
                     + "executor applying for probate\\\",\\\"type\\\":\\\"govuk-heading-s\\\",\\\"questionAnd"
                     + "Answers\\\":[{\\\"question\\\":\\\"Name on will\\\",\\\"answers\\\":[\\\"Exec4 Name\\\"]},"
                     + "{\\\"question\\\":\\\"Address\\\",\\\"answers\\\":[\\\"Wells Cathedral Wells BA5 2PA "
                     + "United "
                     + "Kingdom\\\"]},{\\\"question\\\":\\\"Mobile number\\\",\\\"answers\\\":[\\\""
                     + "07769351102\\\"]},"
                     + "{\\\"question\\\":\\\"Email address\\\",\\\"answers\\\":[\\\""
                     + "bruno.cypress@outree.org\\\"]}]},"
                     + "{\\\"title\\\":\\\"Third executor applying for probate\\\",\\\"type\\\":\\\""
                     + "govuk-heading-s\\\","
                     + "\\\"questionAndAnswers\\\":[{\\\"question\\\":\\\"Name on will\\\",\\\""
                     + "answers\\\":[\\\"Exec5 "
                     + "Name\\\"]},{\\\"question\\\":\\\"Address\\\",\\\"answers\\\":[\\\"Wells "
                     + "Cathedral Wells BA5 2PA "
                     + "United Kingdom\\\"]},{\\\"question\\\":\\\"Mobile number\\\",\\\""
                     + "answers\\\":[\\\"07769351102\\\"]},"
                     + "{\\\"question\\\":\\\"Email address\\\",\\\"answers\\\":[\\\""
                     + "bruno.cypress@outree.org\\\"]}]},"
                     + "{\\\"title\\\":\\\"Fourth executor applying for probate\\\",\\\""
                     + "type\\\":\\\"govuk-heading-s\\\","
                     + "\\\"questionAndAnswers\\\":[{\\\"question\\\":\\\"Name on will\\\","
                     + "\\\"answers\\\":[\\\"Exec6 "
                     + "Name\\\"]},{\\\"question\\\":\\\"Address\\\",\\\"answers\\\":[\\\""
                     + "Wells Cathedral Wells BA5 2PA "
                     + "United Kingdom\\\"]},{\\\"question\\\":\\\"Mobile number\\\",\\\"answers\\\":[\\\""
                     + "07769351102\\\"]},"
                     + "{\\\"question\\\":\\\"Email address\\\",\\\"answers\\\":[\\\""
                     + "bruno.cypress@outree.org\\\"]}]},"
                     + "{\\\"title\\\":\\\"Executors not applying for probate\\\",\\\"type\\\":\\\""
                     + "govuk-heading-s\\\","
                     + "\\\"questionAndAnswers\\\":[{\\\"question\\\":\\\"Why isn’t Exec7 Name applying for "
                     + "probate?\\\","
                     + "\\\"answers\\\":[\\\"This executor doesn’t want to apply now, but may do in the future "
                     + "(this is also known as power reserved)\\\"]},{\\\"question\\\":\\\"Have you notified Exec7 "
                     + "Name in writing that you are applying?\\\",\\\"answers\\\":[\\\"Yes\\\"]},{\\\""
                     + "question\\\":"
                     + "\\\"Why isn’t Exec8 Name applying for probate?\\\",\\\"answers\\\":[\\\"This executor "
                     + "doesn’t "
                     + "want to apply now, and gives up the right to do so in the future (this is also known as "
                     + "renunciation,"
                     + " and the executor will need to fill in a form)\\\"]}]}],\\\"mainParagraph\\\":\\\""
                     + "Check the "
                     + "information below carefully. This will form a record of your application for probate. It "
                     + "will also be stored as a public record, and will be able to be viewed online.\\\",\\\""
                     + "pageTitle"
                     + "\\\":\\\"Check your answers\\\"}\"")
                .stringType("languagePreferenceWelsh", "No")
                .object("primaryApplicantAddress", primaryApplicantAddress -> primaryApplicantAddress
                        .stringType("Country", "United Kingdom")
                        .stringType("PostCode", "BA5 2PA")
                        .stringType("PostTown", "Wells")
                        .stringType("AddressLine1", "Wells Cathedral"))
                .stringType("primaryApplicantSurname", "Applicant ln")
                .stringMatcher("applicationSubmittedDate", REGEX_DATE, "2020-09-07")
                .stringType("primaryApplicantForenames", "Applicant fn")
                .stringType("primaryApplicantPhoneNumber", "1234512345123")
                .stringType("primaryApplicantEmailAddress", "testusername@test.com")
                .stringType("primaryApplicantSameWillName", "Yes")
                .minArrayLike("probateNotificationsGenerated", 0, 0,
                    probateNotificationsGenerated -> probateNotificationsGenerated.stringType("gen"))
                .stringType("deceasedMarriedAfterWillOrCodicilDate", "No");

        // welsh executors
        if(isWelsh){
            dataMap.object("welshLegalStatement", welshLegalStatement -> welshLegalStatement
                    .stringType("intro", "Mae&rsquo;r datganiad hwn wedi&rsquo;i seilio ar yr "
                            + "wybodaeth y mae Applicant fn Applicant ln wedi&rsquo;i rhoi yn eu cais. Bydd "
                            + "yn cael ei storio fel cofnod cyhoeddus.")
                    .stringType("deceased", "Deceased fn Deceased ln ar undefined a bu farw ar "
                            + "undefined, ac roedd fel arfer yn preswylio yng Nghymru a Lloegr.")
                    .stringType("applicant", "Rydym ni, Applicant fn Applicant ln o Wells "
                            + "Cathedral Wells BA5 2PA United Kingdom, Exec4 Name o Wells Cathedral Wells BA5 2PA "
                            + "United Kingdom, Exec5 Name o Wells Cathedral Wells BA5 2PA United Kingdom a/ac Exec6 Name "
                            + "o Wells Cathedral Wells BA5 2PA United Kingdom, yn gwneud y datganiad canlynol:")
                    .minArrayLike("executorsApplying", 1, 1,
                            executorsApplying -> executorsApplying
                                    .stringType("id", "b4c0e385-193e-4195-9657-a1091df50b28")
                                    .object("value", value -> value
                                            .stringType("name", "Mae Applicant fn Applicant ln, ysgutor a "
                                                    + "enwir yn yr ewyllys, yn gwneud cais am brofiant.")
                                            .stringType("sign", "Bydd Applicant fn Applicant ln yn anfon "
                                                    + "i&rsquo;r Gofrestrfa Brofiant yr hyn yr ydym wedi&rsquo;i weld "
                                                    + "a&rsquo;i gredu i fod yn ewyllys a thestament gwir a gwreiddiol "
                                                    + "olaf Deceased fn Deceased ln."))
                    )
                    .stringType("deceasedEstateLand", "Hyd eithaf ein gwybodaeth a&rsquo;n cred, nid "
                            + "oedd unrhyw dir wedi&rsquo;i freinio yn Deceased fn Deceased ln a setlwyd cyn marwolaeth "
                            + "(ac nid drwy ewyllys) Deceased fn Deceased ln ac a oedd dal yn dir a setlwyd er "
                            + "gwaethaf y farwolaeth honno.")
                    .stringType("deceasedOtherNames", "names")
                    .stringType("deceasedEstateValue", "Mae gwerth gros yr ystad yn "
                            + "&pound;2000000.00 ac mae gwerth net yr ystad yn &pound;1000000.00.")
                    .minArrayLike("executorsNotApplying", 1, 1, executorsNotApplying -> executorsNotApplying
                            .stringType("id", "cb62d566-b252-4b8d-abd8-da6eedc26c26")
                            .object("value", value -> value
                                    .stringType("executor", "executor:Nid yw Exec2 Name, ysgutor a enwir yn "
                                            + "yr ewyllys, yn gwneud y cais hwn oherwydd bu iddynt farw cyn i Deceased "
                                            + "fn Deceased ln farw."))));

        }

        // welsh declartion
        if(isWelsh){
                   dataMap.object("welshDeclaration", (declaration) -> declaration
                    .stringType("accept", "Rwy&rsquo;n cadarnhau y byddaf yn gweinyddu "
                            + " ystad yr unigolyn sydd wedi"
                            + " marw yn unol &acirc;&rsquo;r gyfraith, a bod fy  nghais yn gywir.")
                    .stringType("confirm", "Rydym yn cadarnhau y byddwn yn gweinyddu ystad "
                            + "Deceased fn Deceased ln, yn unol &acirc;&rsquo;r gyfraith. Byddwn yn:")
                    .stringType("requests", "Os bydd y gofrestrfa brofiant (y llys) yn "
                            + "gofyn inni byddwn yn:")
                    .stringType("understand", "Rydym yn deall:")
                    .stringType("confirmItem1", "Rydym yn deall:")
                    .stringType("confirmItem2", "cadw manylion llawn (rhestr) yr ystad")
                    .stringType("confirmItem3", "cadw cofnod llawn o&rsquo;r ffordd y mae&rsquo;r "
                            + "ystad wedi cael ei gweinyddu")
                    .stringType("requestsItem1", "darparu manylion llawn yr ystad a&rsquo;r "
                            + "ffordd y cafodd ei gweinyddu")
                    .stringType("requestsItem2", "dychwelyd y grant profiant i&rsquo;r llys")
                    .stringType("submitWarning", "Unwaith y bydd pawb wedi cytuno i&rsquo;r "
                            + "datganiad cyfreithiol ac wedi gwneud eu datganiad, ni ellir newid yr wybodaeth hon.")
                    .stringType("understandItem1", "y bydd ein cais yn cael ei wrthod os na "
                            + "fyddwn yn ateb unrhyw gwestiynau am yr wybodaeth rydym wedi&rsquo;i rhoi")
                    .stringType("understandItem2", "y gellir dwyn achos troseddol "
                            + "yn ein herbyn am dwyll os penderfynir ein bod heb ddweud y gwir "
                            + "neu wedi bod yn anonest yn fwriadol"));

        }

        // withExecutors
        if(withExecutors){
            dataMap.minArrayLike("executorsApplying", 1, 1, executorApplying -> executorApplying
                    .stringType("id", "b4c0e385-193e-4195-9657-a1091df50b28")
                    .object("value", (value) -> value
                            .stringType("applyingExecutorName", "Exec4 Name")
                            .stringType("applyingExecutorEmail", "bruno.cypress@outree.org")
                            .stringType("applyingExecutorAgreed", "Yes")
                            .object("applyingExecutorAddress", (address) -> address
                                    .stringType("Country", "United Kingdom")
                                    .stringType("PostCode", "SW17 0QT")
                                    .stringType("PostTown", "London")
                                    .stringType("AddressLine1", "Westeros"))
                            .stringType("applyingExecutorPhoneNumber", "07769351102")
                            .stringType("applyingExecutorInvitationId", "inviteId")));
        }


                .stringType("primaryApplicantForenames", "Jon")
            .stringType("primaryApplicantSurname", "Snow")
            .stringMatcher("primaryApplicantAddressFound",
                "Yes|No", "Yes")
            .stringMatcher("primaryApplicantPhoneNumber", "[0-9]+", "123455678")
            .stringMatcher("primaryApplicantRelationshipToDeceased",
                "partner|child|sibling|partner|parent|adoptedChild|other", "adoptedChild")
            .stringMatcher("primaryApplicantAdoptionInEnglandOrWales", "(Yes|No)", "Yes")
            .stringValue("primaryApplicantEmailAddress", emailAddress)
            .object("primaryApplicantAddress", (address) ->
                address.stringType("AddressLine1", "Pret a Manger")
                    .stringType("AddressLine2", "St. Georges Hospital")
                    .stringType("PostTown", "London")
                    .stringType("PostCode", "SW17 0QT")
            )
            .stringType("deceasedForenames", "Ned")
            .stringType("deceasedSurname", "Stark")
            .stringMatcher("deceasedDateOfBirth", REGEX_DATE, "1930-01-01")
            .stringMatcher("deceasedDateOfDeath", REGEX_DATE, "2018-01-01")
            .object("deceasedAddress", (address) ->
                address.stringType("AddressLine1", "Winterfell")
                    .stringType("AddressLine2", "Westeros")
                    .stringType("PostTown", "London")
                    .stringType("PostCode", "SW17 0QT")
            )
            .stringMatcher("deceasedAddressFound", "Yes|No", "Yes")
            .stringMatcher("deceasedAnyOtherNames", "Yes|No", "Yes")
            .minArrayLike("deceasedAliasNameList", 0, 2,
                alias -> alias
                    .object("value", (value) ->
                        value.stringType("Forenames", "King")
                            .stringType("LastName", "North")
                    ))
            .stringMatcher("deceasedMartialStatus",
                "marriedCivilPartnership|divorcedCivilPartnership|widowed|judicially|neverMarried")
            .stringMatcher("deceasedMarriedAfterWillOrCodicilDate", "Yes|No", "No")
            .stringMatcher("deceasedDivorcedInEnglandOrWales", "Yes|No", "No")
            .stringMatcher("deceasedSpouseNotApplyingReason", "renunciated|mentallyIncapable|other")
            .stringMatcher("deceasedOtherChildren", "Yes|No", "Yes")
            .stringMatcher("childrenOverEighteenSurvived", "Yes|No", "Yes")
            .stringMatcher("childrenDied", "Yes|No", "No")
            .stringMatcher("grandChildrenSurvivedUnderEighteen", "Yes|No", "No")
            .stringMatcher("deceasedAnyChildren", "Yes|No", "No")
            .stringMatcher("deceasedHasAssetsOutsideUK", "Yes|No", "Yes")
            .stringMatcher("deceasedAnyChildren", "Yes|No", "No")
            .stringMatcher("ihtFormId", "IHT205|IHT207|IHT400421", "IHT205")
            .stringMatcher("ihtFormCompletedOnline", "Yes|No", "No")
            .stringType("assetsOverseasNetValue", "10050")
            .stringType("ihtGrossValue", "100000")
            .stringType("ihtNetValue", "100000")
            .stringType("ihtReferenceNumber", "GOT123456")
            .stringMatcher("declarationCheckbox", "Yes|No", "Yes")
            .numberType("outsideUKGrantCopies", 6)
            .numberType("extraCopiesOfGrant", 3)
            .stringType("uploadDocumentUrl", "http://document-management/document/12345")
            .stringMatcher("registryLocation", "Oxford|Manchester|Birmingham|"
                + "Leeds|Liverpool|Brighton|Cardiff|London|Winchester|Newcastle|ctsc", "Oxford")
            .object("declaration", (declaration) ->
                declaration.stringType("accept", "I confirm that I will administer the estate of the "
                    + "person who died according to law, and that my application is truthful.")
                    .stringType("confirm", "We confirm that we will administer the estate of Suki Hammond,"
                    + "according to law. We will:")
                    .stringType("confirmItem1", "collect the whole estate")
                    .stringType("confirmItem2", "keep full details (an inventory) of the estate")
                    .stringType("confirmItem3", "keep a full account of how the estate has been administered")
                    .stringType("requests", "If the probate registry (court) asks us to do so, we will:")
                    .stringType("requestsItem1", "provide the full details of the estate "
                    + "and how it has been administered")
                    .stringType("requestsItem2", "return the grant of probate to the court")
                    .stringType("understand", "We understand that:")
                    .stringType("understandItem1", "our application will be rejected if we "
                    + "do not answer any questions about the information we have given")
                    .stringType("understandItem2", "criminal proceedings for fraud may be brought against us "
                    + "if we are found to have been deliberately untruthful or dishonest"))
            .object("legalStatement", (legalStatement) ->
                legalStatement.stringType("applicant", "We, Jon Snow of place make the following statement:")
                    .stringType("deceased", "Ned Stark was born on 6 October 1902 and died "
                    + "on 22 December 1983, domiciled in England and Wales.")
                    .stringType("deceasedEstateLand", "To the best of our knowledge, information "
                    + "and belief, there was no land vested in Suki Hammond which was settled previously "
                    + "to the death (and not by the will) of Suki Hammond and which remained settled land "
                    + "notwithstanding such death.")
                    .stringType("deceasedEstateValue", "The gross value for the estate amounts "
                    + "to &pound;2222 and the net value for the estate amounts to &pound;222.")
                    .stringType("deceasedOtherNames", "They were also known as King North.")
                    .minArrayLike("executorsApplying", 0, 2,
                        executorApplying -> executorApplying
                            .object("value", (value) ->
                                value.stringType("name", "Jon Snow, an executor named in the "
                                    + "will or codicils, is applying for probate.")
                                    .stringType("sign", "Jon Snow will sign and send to the "
                                    + "probate registry what they believe to be the true and original last "
                                    + "will and testament and any codicils of Ned Stark.")
                            )
                    )
                    .minArrayLike("executorsNotApplying", 0, 1,
                        executorNotApplying -> executorNotApplying
                            .object("value", (value) ->
                                value.stringType("executor", "Burton Leonard, "
                                    + "an executor named in the will or codicils, is not making this application "
                                    + "because they died before Suki Hammond died.")
                            )
                    )
                    .stringType("intro", "This statement is based on the information "
                        + "you&rsquo;ve given in your application. It will be stored as a public record.")
            )
            .numberType("numberOfApplicants", 2)
            .numberType("numberOfExecutors", 3)
            .stringMatcher("softStop", "Yes|No", "No")
            .stringType("totalFee", "0")
            .stringMatcher("willHasCodicils", "Yes|No", "Yes")
            .stringMatcher("willLatestCodicilHasDate", "Yes|No", "No")
            .numberType("willNumberOfCodicils", 1);

        if (withExecutors) {
            cd.minArrayLike("executorsApplying", 0, 2,
                executorApplying -> executorApplying
                    .object("value", (value) ->
                        value.stringType("applyingExecutorName", "Jon Snow")
                            .stringMatcher("applyingExecutorPhoneNumber", "[0-9]+", "07981898999")
                            .stringMatcher("applyingExecutorAgreed", "Yes|No", "Yes")
                            .stringType("applyingExecutorEmail", "address@email.com")
                            .stringType("applyingExecutorInvitationId", "54321")
                            .stringType("applyingExecutorLeadName", "Graham Garderner")
                            .object("applyingExecutorAddress", (address) ->
                                address.stringType("AddressLine1", "Winterfell")
                                    .stringType("AddressLine2", "Westeros")
                                    .stringType("PostTown", "London")
                                    .stringType("PostCode", "SW17 0QT"))
                                    .stringType("applyingExecutorOtherNames", "Graham Poll")
                                    .stringType("applyingExecutorOtherNamesReason", "Divorce")
                    ))
                .minArrayLike("executorsNotApplying", 0, 1,
                    executorNotApplying -> executorNotApplying
                        .object("value", (value) ->
                            value.stringType("notApplyingExecutorName", "Burton Leonard")
                                .stringType("notApplyingExecutorReason", "DiedBefore"))
                );

        }
        if (withPayments) {
            cd.minArrayLike("payments", 0, 1,
                payment -> payment
                    // .stringType("id", "950243f2-c713-4f1b-990d-83eb508bab91")
                    .object("value", (value) ->
                        value.stringMatcher("amount", "[0-9]+", "2000")
                            .stringType("method", "card")
                            .stringMatcher("status", "Success|Failed|Initiated|not_required", "Success")
                            .stringType("reference", "RC-1599-4876-0252-6208")
                    ));

    }*/


    public static DslPart buildCaseDetailsDsl(Long caseId, String emailAddress, boolean withExecutors,
                                              boolean withPayments) {
        return newJsonBody((o) -> {
            o.numberType("id", caseId)
                    .stringType("jurisdiction", "DIVORCE")
                    .stringMatcher("state", "Draft|PaAppCreated|CaseCreated", "CaseCreated")
                    .stringValue("case_type", "AwaitingDecreeNisi")
                    .object("data", (dataMap) -> {
                        getCaseData(emailAddress, withExecutors, withPayments, dataMap);
                    });
        }).build();
    }
    /*.object("data", (cd) -> {
                    cd.numberType("outsideUKGrantCopies", 6)
                        .stringValue("applicationType", "Personal")
                        .stringMatcher("applicationSubmittedDate", REGEX_DATE, "2018-05-17")
                        .stringType("primaryApplicantForenames", "Jon")
                        .stringType("primaryApplicantSurname", "Snow")
                        .stringMatcher("primaryApplicantAddressFound",
                            "Yes|No", "Yes")
                        .stringMatcher("primaryApplicantPhoneNumber", "[0-9]+", "123455678")
                        .stringMatcher("primaryApplicantRelationshipToDeceased",
                            "partner|child|sibling|partner|parent|adoptedChild|other", "adoptedChild")
                        .stringMatcher("primaryApplicantAdoptionInEnglandOrWales", "(Yes|No)", "Yes")
                        .stringValue("primaryApplicantEmailAddress", emailAddress)
                        .object("primaryApplicantAddress", (address) ->
                            address.stringType("AddressLine1", "Pret a Manger")
                                .stringType("AddressLine2", "St. Georges Hospital")
                                .stringType("PostTown", "London")
                                .stringType("PostCode", "SW17 0QT")
                        )
                        .stringType("deceasedForenames", "Ned")
                        .stringType("deceasedSurname", "Stark")
                        .stringMatcher("deceasedDateOfBirth", REGEX_DATE, "1930-01-01")
                        .stringMatcher("deceasedDateOfDeath", REGEX_DATE, "2018-01-01")
                        .object("deceasedAddress", (address) ->
                            address.stringType("AddressLine1", "Winterfell")
                                .stringType("AddressLine2", "Westeros")
                                .stringType("PostTown", "London")
                                .stringType("PostCode", "SW17 0QT")
                        )
                        .stringMatcher("deceasedAddressFound", "Yes|No", "Yes")
                        .stringMatcher("deceasedAnyOtherNames", "Yes|No", "Yes")
                        .minArrayLike("deceasedAliasNameList", 0, 2,
                            alias -> alias
                                .object("value", (value) ->
                                    value.stringType("Forenames", "King")
                                        .stringType("LastName", "North")
                                ))
                        .stringMatcher("deceasedMartialStatus",
                            "marriedCivilPartnership|divorcedCivilPartnership|widowed|judicially|neverMarried")
                        .stringMatcher("deceasedMarriedAfterWillOrCodicilDate", "Yes|No", "No")
                        .stringMatcher("deceasedDivorcedInEnglandOrWales", "Yes|No", "No")
                        .stringMatcher("deceasedSpouseNotApplyingReason", "renunciated|mentallyIncapable|other")
                        .stringMatcher("deceasedOtherChildren", "Yes|No", "Yes")
                        .stringMatcher("childrenOverEighteenSurvived", "Yes|No", "Yes")
                        .stringMatcher("childrenDied", "Yes|No", "No")
                        .stringMatcher("grandChildrenSurvivedUnderEighteen", "Yes|No", "No")
                        .stringMatcher("deceasedAnyChildren", "Yes|No", "No")
                        .stringMatcher("deceasedHasAssetsOutsideUK", "Yes|No", "Yes")
                        .stringMatcher("deceasedAnyChildren", "Yes|No", "No")
                        .stringMatcher("ihtFormId", "IHT205|IHT207|IHT400421", "IHT205")
                        .stringMatcher("ihtFormCompletedOnline", "Yes|No", "No")
                        .stringType("assetsOverseasNetValue", "10050")
                        .stringType("ihtGrossValue", "100000")
                        .stringType("ihtNetValue", "100000")
                        .stringType("ihtReferenceNumber", "GOT123456")
                        .stringMatcher("declarationCheckbox", "Yes|No", "Yes")
                        .numberType("outsideUKGrantCopies", 6)
                        .numberType("extraCopiesOfGrant", 3)
                        .stringType("uploadDocumentUrl", "http://document-management/document/12345")
                        .stringMatcher("registryLocation",
                            "Oxford|Manchester|Birmingham|Leeds|Liverpool|Brighton|Cardiff"
                                + "|London|Winchester|Newcastle|ctsc", "Oxford")
                        .object("declaration", (declaration) ->
                            declaration.stringType("accept", "I confirm that I will administer "
                                + "the estate of the person who died according to law, and "
                                + "that my application is truthful.")
                                .stringType("confirm", "We confirm that we will administer "
                                + "the estate of Suki Hammond, according to law. We will:")
                                .stringType("confirmItem1", "collect the whole estate")
                                .stringType("confirmItem2", "keep full details (an inventory) of the estate")
                                .stringType("confirmItem3", "keep a full account of how the estate "
                                + "has been administered")
                                .stringType("requests", "If the probate registry (court) asks us to "
                                + "do so, we will:")
                                .stringType("requestsItem1", "provide the full details of the "
                                + "estate and how it has been administered")
                                .stringType("requestsItem2", "return the grant of probate to the court")
                                .stringType("understand", "We understand that:")
                                .stringType("understandItem1", "our application will be rejected if "
                                + "we do not answer any questions about the information we have given")
                                .stringType("understandItem2", "criminal proceedings for fraud may be "
                                + "brought against us if we are found to have been deliberately "
                                + "untruthful or dishonest"))
                        .object("legalStatement", (legalStatement) ->
                            legalStatement.stringType("applicant", "We, Jon Snow of place make the "
                                + "following statement:")
                                .stringType("deceased", "Ned Stark was born on 6 October 1902 and died "
                                + "on 22 December 1983, domiciled in England and Wales.")
                                .stringType("deceasedEstateLand", "To the best of our knowledge,"
                                + " information and belief, there was no land vested in Suki Hammond which "
                                + "was settled previously"
                                + "to the death (and not by the will) of Suki Hammond and which remained settled "
                                + "land notwithstanding such death.")
                                 .stringType("deceasedEstateValue", "The gross value for the estate amounts"
                                + "to &pound;2222 and the net value for the estate amounts to &pound;222.")
                                .stringType("deceasedOtherNames", "They were also known as King North.")
                                .minArrayLike("executorsApplying", 0, 2,
                                    executorApplying -> executorApplying
                                        .object("value", (value) ->
                                            value.stringType("name", "Jon Snow, an executor named in the "
                                                + "will or codicils, is applying for probate.")
                                                .stringType("sign", "Jon Snow will sign and send to the "
                                                + "probate registry what they believe to be the true and original last "
                                                + "will and testament "
                                                + "and any codicils of Ned Stark.")
                                        )
                                )
                                .minArrayLike("executorsNotApplying", 0, 1,
                                    executorNotApplying -> executorNotApplying
                                        .object("value", (value) ->
                                            value.stringType("executor", "Burton Leonard, "
                                               + "an executor "
                                                + "named in the will or codicils, is not making "
                                                + "this application because "
                                                + "they died before Suki Hammond died.")
                                        )
                                )
                                .stringType("intro", "This statement is based on the information "
                                    + "you&rsquo;ve given in your application. It will be stored as a public record.")
                        )
                        .numberType("numberOfApplicants", 2)
                        .numberType("numberOfExecutors", 3)
                        .stringMatcher("softStop", "Yes|No", "No")
                        .stringType("totalFee", "0")
                        .stringMatcher("willHasCodicils", "Yes|No", "Yes")
                        .stringMatcher("willLatestCodicilHasDate", "Yes|No", "No")
                        .numberType("willNumberOfCodicils", 1);

                    if (withExecutors) {
                        cd.minArrayLike("executorsApplying", 0, 2,
                            executorApplying -> executorApplying
                                .object("value", (value) ->
                                    value.stringType("applyingExecutorName", "Jon Snow")
                                        .stringMatcher("applyingExecutorPhoneNumber", "[0-9]+", "07981898999")
                                        .stringMatcher("applyingExecutorAgreed", "Yes|No", "Yes")
                                        .stringType("applyingExecutorEmail", "address@email.com")
                                        .stringType("applyingExecutorInvitationId", "54321")
                                        .stringType("applyingExecutorLeadName", "Graham Garderner")
                                        .object("applyingExecutorAddress", (address) ->
                                            address.stringType("AddressLine1", "Winterfell")
                                                .stringType("AddressLine2", "Westeros")
                                                .stringType("PostTown", "London")
                                                .stringType("PostCode", "SW17 0QT")
                                        ).stringType("applyingExecutorOtherNames", "Graham Poll")
                                        .stringType("applyingExecutorOtherNamesReason", "Divorce")
                                ))
                            .minArrayLike("executorsNotApplying", 0, 1,
                                executorNotApplying -> executorNotApplying
                                    .object("value", (value) ->
                                        value.stringType("notApplyingExecutorName", "Burton Leonard")
                                            .stringType("notApplyingExecutorReason", "DiedBefore"))
                            );

                    }
                    if (withPayments) {
                        cd.minArrayLike("payments", 0, 1,
                            payment -> payment
                                // .stringType("id", "950243f2-c713-4f1b-990d-83eb508bab91")
                                .object("value", (value) ->
                                    value.stringMatcher("amount", "[0-9]+", "2000")
                                        .stringType("method", "card")
                                        .stringMatcher("status",
                                                "Success|Failed|Initiated|not_required", "Success")
                                        .stringType("reference", "RC-1599-4876-0252-6208")));
                    }

                })
            ;
        }).build();*/

    public static DslPart buildListOfCaseDetailsDsl(Long caseId, String emailAddress,
                                         boolean withExecutors, boolean withPayments) {
        return PactDslJsonArray
            .arrayEachLike(2)
                .stringValue("case_type", "AwaitingDecreeNisi")
                .stringValue("jurisdiction","divorce")
                .object("case_data")
                    .numberType("outsideUKGrantCopies", 6)
                    .stringValue("applicationType", "Personal")
                    .stringMatcher("applicationSubmittedDate", REGEX_DATE, "2018-05-17")
                    .stringType("primaryApplicantForenames", "Jon")
                    .stringType("primaryApplicantSurname", "Snow")
                    .stringMatcher("primaryApplicantAddressFound",
                        "Yes|No", "Yes")
                    .stringMatcher("primaryApplicantPhoneNumber", "[0-9]+", "123455678")
                    .stringMatcher("primaryApplicantRelationshipToDeceased",
                        "partner|child|sibling|partner|parent|adoptedChild|other", "adoptedChild")
                    .stringMatcher("primaryApplicantAdoptionInEnglandOrWales", "(Yes|No)", "Yes")
                    .stringValue("primaryApplicantEmailAddress", "someEmail....")
                    .object("primaryApplicantAddress")
                        .stringType("AddressLine1", "Pret a Manger")
                        .stringType("AddressLine2", "St. Georges Hospital")
                        .stringType("PostTown", "London")
                        .stringType("PostCode", "SW17 0QT")
                    .closeObject()
                    .object("")
                        .stringType("deceasedForenames", "Ned")
                        .stringType("deceasedSurname", "Stark")
                        .stringMatcher("deceasedDateOfBirth", REGEX_DATE, "1930-01-01")
                        .stringMatcher("deceasedDateOfDeath", REGEX_DATE, "2018-01-01")
                    .closeObject()
                    .object("deceasedAddress")
                        .stringType("AddressLine1", "Winterfell")
                        .stringType("AddressLine2", "Westeros")
                        .stringType("PostTown", "London")
                        .stringType("PostCode", "SW17 0QT")
                    .closeObject()
                    //TODO Not able to add String types after this , only .object ... closeObject() types.
                    .closeObject();
    }

    public static DslPart buildSearchResultDsl(Long caseId, String emailAddress,
                                               boolean withExecutors, boolean withPayments) {
        return newJsonBody((o) -> {
            o.numberType("total",123)
                           .minArrayLike("cases", 2, (cd) -> {
                               cd.numberType("id", 200);
                               cd.stringType("jurisdiction", "divorce");
                               cd.stringType("callback_response_status",  "DONE");
                               cd.stringType("case_type",  "DIVORCE");
                               cd.object("data", (dataMap) -> {
                                   getCaseData(emailAddress, withExecutors, withPayments, dataMap);
                               });
                           });
        }).build();
    }
}
