package uk.gov.hmcts.reform.probate.model;

import com.google.common.collect.Lists;
import uk.gov.hmcts.reform.probate.model.cases.Address;
import uk.gov.hmcts.reform.probate.model.cases.CollectionMember;
import uk.gov.hmcts.reform.probate.model.cases.RegistryLocation;
import uk.gov.hmcts.reform.probate.model.cases.caveat.Caveat;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;

import java.time.LocalDate;
import java.util.Arrays;

public class CaveatCreator {

    public static Caveat createCaveatCase() {

        Caveat caveat = new Caveat();

        caveat.setApplicationType(ProbateType.PERSONAL);
        caveat.setCaveatorAddress(getAddress("caveator"));
        caveat.setCaveatorEmailAddress("caveator@email.com");
        caveat.setCaveatorForenames("caveator forename");
        caveat.setCaveatorSurname("caveator surname");

        caveat.setDeceasedAddress(getAddress("deceased"));
        caveat.setDeceasedAnyOtherNames(true);
        CollectionMember<AliasOtherNames> aliasNameCollectionMember = new CollectionMember<>();
        AliasOtherNames aon = new AliasOtherNames();
        aon.setFirstName("deceased other firstname");
        aon.setLastName("deceased other lastname");
        aliasNameCollectionMember.setValue(aon);
        caveat.setDeceasedFullAliasNameList(Lists.newArrayList(aliasNameCollectionMember));
        caveat.setDeceasedDateOfBirth(LocalDate.of(1966, 3, 4));
        caveat.setDeceasedDateOfDeath(LocalDate.of(2018, 11, 20));
        caveat.setDeceasedForenames("deceased forename");
        caveat.setDeceasedSurname("deceased surname");
        caveat.setRegistryLocation(RegistryLocation.OXFORD);
        caveat.setExpiryDate(LocalDate.of(2019, 2, 14));

        return caveat;

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
}
