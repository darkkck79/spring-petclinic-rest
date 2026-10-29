package org.springframework.samples.petclinic.rest.advice;

import java.util.List;

/**
 * Thrown when a request parameter has a value that is not one of the allowed ones.
 */
public class InvalidRequestParameterException extends RuntimeException {

    private final String parameter;
    private final String rejectedValue;
    private final List<String> allowedValues;

    public InvalidRequestParameterException(String parameter, String rejectedValue, List<String> allowedValues) {
        super("Invalid value '%s' for parameter '%s', must be one of %s".formatted(rejectedValue, parameter, allowedValues));
        this.parameter = parameter;
        this.rejectedValue = rejectedValue;
        this.allowedValues = allowedValues;
    }

    public String getParameter() {
        return parameter;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }
}
