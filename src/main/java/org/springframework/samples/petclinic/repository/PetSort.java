package org.springframework.samples.petclinic.repository;

import java.util.Arrays;
import java.util.Optional;

/**
 * Orders in which pets can be listed. A leading '-' in the external value means descending.
 */
public enum PetSort {

    NAME_ASC("name"),
    NAME_DESC("-name"),
    BIRTH_DATE_ASC("birthDate"),
    BIRTH_DATE_DESC("-birthDate");

    private final String value;

    PetSort(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Exact, case-sensitive lookup by external value.
     */
    public static Optional<PetSort> fromValue(String value) {
        return Arrays.stream(values()).filter(s -> s.value.equals(value)).findFirst();
    }
}
