package pl.hsbc.application.wall;

import pl.hsbc.application.wall.dto.*;
import pl.hsbc.domain.wall.MessageEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class WallMessageMapper {
    static WallMessagesDTO map(List<MessageEntity> wallMessages) {
        return new WallMessagesDTO(findWallUUID(wallMessages),
                wallMessages.stream()
                        .map(wallMessage -> createWallMessageDTO(wallMessage))
                        .collect(Collectors.toList())
        );
    }

    private static UUID findWallUUID(List<MessageEntity> wallMessages) {
        return wallMessages.stream().findFirst().orElseThrow().getWall().getUuid();
    }

    private static WallMessageDTO createWallMessageDTO(MessageEntity wallMessage) {
        return new WallMessageDTO(
                findWallOwnerUUID(wallMessage),
                wallMessage.getPublishedDateTime(),
                new AuthorDTO(wallMessage.getAuthor().getUuid(), wallMessage.getAuthor().getLogin()),
                new MessageSimpleDTO(wallMessage.getMessage().getText())
        );
    }

    private static UUID findWallOwnerUUID(MessageEntity messageEntity) {
        return messageEntity.getUuid();
    }

}
