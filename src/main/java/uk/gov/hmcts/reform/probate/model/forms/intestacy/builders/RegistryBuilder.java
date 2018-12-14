package uk.gov.hmcts.reform.probate.model.forms.intestacy.builders;

import uk.gov.hmcts.reform.probate.model.forms.Registry;

public final class RegistryBuilder {
    private String name;
    private String email;
    private String address;
    private Long sequenceNumber;

    private RegistryBuilder() {
    }

    public static RegistryBuilder createRegistry() {
        return new RegistryBuilder();
    }

    public RegistryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RegistryBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public RegistryBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public RegistryBuilder withSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    public Registry build() {
        Registry registry = new Registry();
        registry.setName(name);
        registry.setEmail(email);
        registry.setAddress(address);
        registry.setSequenceNumber(sequenceNumber);
        return registry;
    }
}
