package com.st.app.rewardprovider.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ErrorDetails
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    private Map<String, String> errors = new HashMap<>();

    /**
     * Instantiates new error response
     *
     * @param timestamp
     * @param message
     * @param details
     * @param errors
     */
    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
