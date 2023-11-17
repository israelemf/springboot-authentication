package com.auth.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

public class ErrorStructure {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private Integer code;
    private String status;
    private List<String> errors;

    public ErrorStructure() {
    }

    public ErrorStructure(LocalDateTime timestamp, Integer code, String status) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
    }

    public ErrorStructure(LocalDateTime timestamp, Integer code, String status, List<String> errors) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }
}
