package pl.hsbc.domain.wall;

import pl.hsbc.domain.user.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageEntity implements Comparable<MessageEntity> {
    private UUID uuid;
    private LocalDateTime publishedDateTime;
    private UserEntity author; //redundant for future to publish someone message on our wall
    private Message message;
    private WallEntity wall;

    public MessageEntity(Message message) {
        this.message = message;
    }

    public MessageEntity(Message message, WallEntity wall, UserEntity author) {
        this(message);
        this.author = author;
        this.wall = wall;
    }

    public MessageEntity(UUID uuid, LocalDateTime publishedDateTime, UserEntity author, Message message, WallEntity wallId) {
        this(message, wallId, author);
        if (uuid == null) {
            throw new IllegalArgumentException("messageEntity uuid is null");
        }
        this.uuid = uuid;
        this.publishedDateTime = publishedDateTime;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getPublishedDateTime() {
        return publishedDateTime;
    }


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }


    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public WallEntity getWall() {
        return wall;
    }

    public void setWall(WallEntity wall) {
        this.wall = wall;
    }


    @Override
    public int compareTo(MessageEntity o) {
        return this.getPublishedDateTime().compareTo(o.getPublishedDateTime());
    }
}
