package pl.hsbc.application;

import java.util.UUID;

public class ResponseDTO<T> {
    private T body;
    private String message;
    private UUID uuid;

    public ResponseDTO(String message) {
        this.message = message;
    }
    public ResponseDTO(UUID uuid) {
        this.uuid = uuid;
    }
    public ResponseDTO(T body) {
        this.body = body;
    }

    public ResponseDTO(String message, UUID uuid) {
        this(message);
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
