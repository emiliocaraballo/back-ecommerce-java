package co.com.ecommerce.infrastructure.configuration.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private String code;
    private String description;
    private String title;
    private int statusCode;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ErrorResponse(String code, String description, String title, int statusCode, String path) {
        this.code = code;
        this.description = description;
        this.title = title;
        this.statusCode = statusCode;
        this.path = path;
    }

    // Constructor, getters y setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
