package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.cases.MaritalStatus;
import uk.gov.hmcts.reform.probate.model.cases.grantofrepresentation.SpouseNotApplyingReason;
import uk.gov.hmcts.reform.probate.model.forms.AliasOtherNames;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyDeceased;

import java.time.LocalDate;
import java.util.Map;

public final class IntestacyDeceasedBuilder {
    private String firstName;
    private MaritalStatus maritalStatus;
    private String lastName;
    private Boolean divorcedInEnglandOrWales;
    private Boolean domiciledInEnglandOrWales;
    private LocalDate dateOfBirth;
    private SpouseNotApplyingReason spouseNotApplyingReason;
    private LocalDate dateOfDeath;
    private Boolean otherChildren;
    private Boolean addressFound;
    private String address;
    private String freeTextAddress;
    private Boolean allDeceasedChildrenOverEighteen;
    private Boolean alias;
    private Map<String, AliasOtherNames> otherNames;
    private Boolean anyDeceasedChildrenDieBeforeDeceased;
    private Boolean anyDeceasedGrandchildrenUnderEighteen;
    private Boolean anyChildren;

    private IntestacyDeceasedBuilder() {
    }

    public static IntestacyDeceasedBuilder anIntestacyDeceased() {
        return new IntestacyDeceasedBuilder();
    }

    public IntestacyDeceasedBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public IntestacyDeceasedBuilder withMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public IntestacyDeceasedBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public IntestacyDeceasedBuilder withDivorcedInEnglandOrWales(Boolean divorcedInEnglandOrWales) {
        this.divorcedInEnglandOrWales = divorcedInEnglandOrWales;
        return this;
    }

    public IntestacyDeceasedBuilder withDomiciledInEnglandOrWales(Boolean domiciledInEnglandOrWales) {
        this.domiciledInEnglandOrWales = domiciledInEnglandOrWales;
        return this;
    }

    public IntestacyDeceasedBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public IntestacyDeceasedBuilder withSpouseNotApplyingReason(SpouseNotApplyingReason spouseNotApplyingReason) {
        this.spouseNotApplyingReason = spouseNotApplyingReason;
        return this;
    }

    public IntestacyDeceasedBuilder withDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
        return this;
    }

    public IntestacyDeceasedBuilder withOtherChildren(Boolean otherChildren) {
        this.otherChildren = otherChildren;
        return this;
    }

    public IntestacyDeceasedBuilder withAddressFound(Boolean addressFound) {
        this.addressFound = addressFound;
        return this;
    }

    public IntestacyDeceasedBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public IntestacyDeceasedBuilder withFreeTextAddress(String freeTextAddress) {
        this.freeTextAddress = freeTextAddress;
        return this;
    }

    public IntestacyDeceasedBuilder withAllDeceasedChildrenOverEighteen(Boolean allDeceasedChildrenOverEighteen) {
        this.allDeceasedChildrenOverEighteen = allDeceasedChildrenOverEighteen;
        return this;
    }

    public IntestacyDeceasedBuilder withAlias(Boolean alias) {
        this.alias = alias;
        return this;
    }

    public IntestacyDeceasedBuilder withOtherNames(Map<String, AliasOtherNames> otherNames) {
        this.otherNames = otherNames;
        return this;
    }

    public IntestacyDeceasedBuilder withAnyDeceasedChildrenDieBeforeDeceased(Boolean anyDeceasedChildrenDieBefore) {
        this.anyDeceasedChildrenDieBeforeDeceased = anyDeceasedChildrenDieBefore;
        return this;
    }

    public IntestacyDeceasedBuilder withAnyDeceasedGrandchildrenUnderEighteen(Boolean anyDeceasedGrandchildrenUnder) {
        this.anyDeceasedGrandchildrenUnderEighteen = anyDeceasedGrandchildrenUnder;
        return this;
    }

    public IntestacyDeceasedBuilder withAnyChildren(Boolean anyChildren) {
        this.anyChildren = anyChildren;
        return this;
    }

    public IntestacyDeceased build() {
        IntestacyDeceased intestacyDeceased = new IntestacyDeceased();
        intestacyDeceased.setFirstName(firstName);
        intestacyDeceased.setMaritalStatus(maritalStatus);
        intestacyDeceased.setLastName(lastName);
        intestacyDeceased.setDivorcedInEnglandOrWales(divorcedInEnglandOrWales);
        intestacyDeceased.setDomiciledInEnglandOrWales(domiciledInEnglandOrWales);
        intestacyDeceased.setDateOfBirth(dateOfBirth);
        intestacyDeceased.setSpouseNotApplyingReason(spouseNotApplyingReason);
        intestacyDeceased.setDateOfDeath(dateOfDeath);
        intestacyDeceased.setOtherChildren(otherChildren);
        intestacyDeceased.setAddressFound(addressFound);
        intestacyDeceased.setAddress(address);
        intestacyDeceased.setFreeTextAddress(freeTextAddress);
        intestacyDeceased.setAllDeceasedChildrenOverEighteen(allDeceasedChildrenOverEighteen);
        intestacyDeceased.setAlias(alias);
        intestacyDeceased.setOtherNames(otherNames);
        intestacyDeceased.setAnyDeceasedChildrenDieBeforeDeceased(anyDeceasedChildrenDieBeforeDeceased);
        intestacyDeceased.setAnyDeceasedGrandchildrenUnderEighteen(anyDeceasedGrandchildrenUnderEighteen);
        intestacyDeceased.setAnyChildren(anyChildren);
        return intestacyDeceased;
    }
}
