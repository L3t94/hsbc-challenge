package pl.hsbc.infrastructure.wall;

import pl.hsbc.domain.wall.MessageEntity;

import java.time.LocalDateTime;
import java.util.UUID;

class MessageFactory {
    static MessageEntity createMessage(MessageEntity messageEntity) {
        return new MessageEntity(
                UUID.randomUUID(),
                LocalDateTime.now(),
                messageEntity.getAuthor(),
                messageEntity.getMessage(),
                messageEntity.getWall()
        );
    }
}
