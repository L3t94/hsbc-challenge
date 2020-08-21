package pl.hsbc.domain.wall;

import pl.hsbc.domain.user.UserEntity;

import java.util.*;

public class WallEntity {
    private UUID uuid;
    private UserEntity user;
    private List<MessageEntity> messages;

    public WallEntity(UserEntity user, List<MessageEntity> messages) {
        this.user = user;
        this.messages = messages;
    }

    public WallEntity(UUID uuid, UserEntity user, List<MessageEntity> messages) {
        this(user,messages);
        if(uuid == null){
            throw new IllegalArgumentException("wallEntity uuid is null");
        }
        this.uuid = uuid;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<MessageEntity> getReversedDateTimeOrderedMessages() {
        Collections.sort(messages, Collections.reverseOrder());
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }


    public void  addMessage(MessageEntity messageEntity){
        this.messages.add(messageEntity);
    }

}
