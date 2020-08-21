package pl.hsbc.infrastructure.wall;

import org.springframework.stereotype.Repository;
import pl.hsbc.domain.wall.MessageEntity;
import pl.hsbc.domain.wall.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final WallRepositoryImpl wallRepository;
    private List<MessageEntity> allMessages = new ArrayList<>();

    public MessageRepositoryImpl(WallRepositoryImpl wallRepository) {
        this.wallRepository = wallRepository;
    }

    @Override
    public MessageEntity save(MessageEntity messageEntity) {
        MessageEntity createdMessageEntity = MessageFactory.createMessage(messageEntity);
        allMessages.add(createdMessageEntity);
        wallRepository.addMessageToWall(messageEntity.getWall().getUuid(), createdMessageEntity);
        return createdMessageEntity;
    }
}
