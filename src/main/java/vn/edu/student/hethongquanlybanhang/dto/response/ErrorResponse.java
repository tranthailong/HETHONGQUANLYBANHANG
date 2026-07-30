package vn.edu.student.hethongquanlybanhang.dto.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}