package org.springframework.samples.petclinic.rest.advice;

/**
 * Thrown when a date range is inconsistent, e.g. its lower bound is after its upper bound.
 */
public class InvalidDateRangeException extends RuntimeException {

    private final String field;
    private final String rejectedValue;

    public InvalidDateRangeException(String field, String rejectedValue, String message) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }
}
