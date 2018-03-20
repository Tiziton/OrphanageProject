package pl.polsl.orphanage.security;

/**
 * Enum for contain Authorities
 */
public enum AuthoritiesConstants {

    SYSTEM("SYSTEM"),
    ADMIN("ADMIN"),
    CARETAKER("CARETAKER"),
    CURATOR("CURATOR");

    private final String name;

    AuthoritiesConstants(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
