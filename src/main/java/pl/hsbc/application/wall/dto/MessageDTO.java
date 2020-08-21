package pl.hsbc.application.wall.dto;

import java.util.UUID;

public class MessageDTO {
    private String text;
    private UUID author; //redundant for future to publish someone message on our wall

    public MessageDTO(){}
    public MessageDTO(String text, UUID author){
        this(text);
        this.author = author;
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
