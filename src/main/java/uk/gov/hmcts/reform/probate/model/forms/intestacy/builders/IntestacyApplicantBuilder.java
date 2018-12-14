package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.Relationship;
import uk.gov.hmcts.reform.probate.model.forms.intestacy.IntestacyApplicant;

public final class IntestacyApplicantBuilder {
    private String email;
    private String firstName;
    private String lastName;
    private String postCode;
    private String phoneNumber;
    private String address;
    private Relationship relationshipToDeceased;
    private Boolean addressFound;
    private String freeTextAddress;
    private Boolean adoptionInEnglandOrWales;

    private IntestacyApplicantBuilder() {
    }

    public static IntestacyApplicantBuilder anIntestacyApplicant() {
        return new IntestacyApplicantBuilder();
    }

    public IntestacyApplicantBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public IntestacyApplicantBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public IntestacyApplicantBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public IntestacyApplicantBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public IntestacyApplicantBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public IntestacyApplicantBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public IntestacyApplicantBuilder withRelationshipToDeceased(Relationship relationshipToDeceased) {
        this.relationshipToDeceased = relationshipToDeceased;
        return this;
    }

    public IntestacyApplicantBuilder withAddressFound(Boolean addressFound) {
        this.addressFound = addressFound;
        return this;
    }

    public IntestacyApplicantBuilder withFreeTextAddress(String freeTextAddress) {
        this.freeTextAddress = freeTextAddress;
        return this;
    }

    public IntestacyApplicantBuilder withAdoptionInEnglandOrWales(Boolean adoptionInEnglandOrWales) {
        this.adoptionInEnglandOrWales = adoptionInEnglandOrWales;
        return this;
    }

    public IntestacyApplicant build() {
        IntestacyApplicant intestacyApplicant = new IntestacyApplicant();
        intestacyApplicant.setEmail(email);
        intestacyApplicant.setFirstName(firstName);
        intestacyApplicant.setLastName(lastName);
        intestacyApplicant.setPostCode(postCode);
        intestacyApplicant.setPhoneNumber(phoneNumber);
        intestacyApplicant.setAddress(address);
        intestacyApplicant.setRelationshipToDeceased(relationshipToDeceased);
        intestacyApplicant.setAddressFound(addressFound);
        intestacyApplicant.setFreeTextAddress(freeTextAddress);
        intestacyApplicant.setAdoptionInEnglandOrWales(adoptionInEnglandOrWales);
        return intestacyApplicant;
    }
}
