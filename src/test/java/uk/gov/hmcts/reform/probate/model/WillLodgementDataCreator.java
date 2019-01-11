package uk.gov.hmcts.reform.probate.model;

import com.google.common.collect.Lists;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.ApplicationType;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.FullAliasName;
import uk.gov.hmcts.reform.probate.model.cases.Gender;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.willlodgement.LodgementType;
import uk.gov.hmcts.reform.probate.model.cases.willlodgement.WillExecutor;
import uk.gov.hmcts.reform.probate.model.cases.willlodgement.WillLodgementData;

import java.time.LocalDate;
import java.util.List;

public class WillLodgementDataCreator {

    public static WillLodgementData createWillLodgement() {
        return WillLodgementData.builder()
                .applicationType(ApplicationType.PERSONAL)
                .codicilDate(LocalDate.of(2018, 2, 1))
                .deceasedAddress(getAddress("wlDeceasedAddress"))
                .deceasedAnyOtherNames(true)
                .deceasedDateOfBirth(LocalDate.of(1975, 1, 1))
                .deceasedDateOfDeath(LocalDate.of(2019, 1, 1))
                .deceasedEmailAddress("wlEmailAddress")
                .deceasedForenames("wlDeceasedForenames")
                .deceasedFullAliasNameList(getFullAliasNameList())
                .deceasedGender(Gender.MALE)
                .deceasedSurname("wlDeceasedSurname")
                .deceasedTypeOfDeath("Illness")
                .jointWill(false)
                .lodgedDate(LocalDate.of(2018, 2, 2))
                .lodgementType(LodgementType.SAFE_CUSTODY)
                .numberOfCodicils(13L)
                .registryLocation(RegistryLocation.CARDIFF)
                .willDate(LocalDate.of(2008, 11, 5))
                .willExecutorList(getWillExecutor())
                .build();
    }

    private static List<CollectionMember<WillExecutor>> getWillExecutor() {
        CollectionMember<WillExecutor> willExecutorCollectionMember = new CollectionMember<>();
        willExecutorCollectionMember.setValue(
                WillExecutor.builder()
                        .willExecutorAddress(getAddress("willExecutorAddress"))
                        .willExecutorEmail("willExecutorEmail")
                        .willExecutorForenames("willExecutorForename")
                        .willExecutorSurname("willExecutorSurname")
                        .willExecutorTitle("willExecutorTitle")
                        .build()
        );
        return Lists.newArrayList(willExecutorCollectionMember);
    }

    private static List<CollectionMember<FullAliasName>> getFullAliasNameList() {
        CollectionMember<FullAliasName> fullAliasNameCollectionMember = new CollectionMember<>();
        fullAliasNameCollectionMember.setValue(FullAliasName.builder()
                .fullAliasName("wlFullAliasName").build());
        return Lists.newArrayList(fullAliasNameCollectionMember);
    }

    private static Address getAddress(String name) {
        Address address = new Address();
        address.setAddressLine1(name + " address line 1");
        address.setAddressLine2(name + " address line 2");
        address.setAddressLine3(name + " address line 3");
        address.setCounty(name + " county");
        address.setPostTown(name + " post town");
        address.setPostCode(name + " post code");
        address.setCountry(name + " country");
        return address;
    }

    private WillLodgementDataCreator() {
    }
}
