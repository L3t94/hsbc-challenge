package pl.hsbc.application.wall.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public class WallMessageDTO {
    private UUID messageUUID;
    private LocalDateTime publishedDateTime;
    private AuthorDTO author;
    private MessageSimpleDTO message;

    public WallMessageDTO(UUID ownerUUID, LocalDateTime publishedDateTime, AuthorDTO author, MessageSimpleDTO message) {
        this.messageUUID = ownerUUID;
        this.publishedDateTime = publishedDateTime;
        this.author = author;
        this.message = message;
    }

    public UUID getMessageUUID() {
        return messageUUID;
    }

    public LocalDateTime getPublishedDateTime() {
        return publishedDateTime;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public MessageSimpleDTO getMessage() {
        return message;
    }


}
