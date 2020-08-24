package pl.hsbc.application.wall.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageDTO {
    private String text;
    private UUID author; //redundant for future to publish someone message on our wall
    private LocalDateTime publishedDateTime;

    public MessageDTO(){}

    public MessageDTO(String text, UUID author, LocalDateTime publishedDateTime){
        this(text);
        this.author = author;
        this.publishedDateTime = publishedDateTime;
    }

    public LocalDateTime getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(LocalDateTime publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }
    public MessageDTO(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }
}
