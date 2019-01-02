package uk.gov.hmcts.reform.probate.model.cases;

public enum RegistryLocation {

    OXFORD("Oxford"),
    BIRMINGHAM("Birmingham"),
    MACHESTER("Manchester"),
    LEEDS("Leeds"),
    LIVERPOOL("Liverpool"),
    BRIGHTON("Brighton"),
    LONDON("London"),
    CARDIFF("Cardiff"),
    NEWCASTLE("Newcastle"),
    WINCHESTER("Winchester");

    private final String name;

    private RegistryLocation(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
