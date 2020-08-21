package pl.hsbc.application.wall;

import pl.hsbc.application.wall.dto.MessageDTO;
import pl.hsbc.application.wall.dto.MessagesDTO;
import pl.hsbc.domain.wall.MessageEntity;

import java.util.List;
import java.util.stream.Collectors;

class MessageMapper {
    static MessagesDTO map(List<MessageEntity> messages){
        return new MessagesDTO(
                messages.stream()
                        .map(messageEntity -> createMessageDTO(messageEntity))
                        .collect(Collectors.toList())
        );
    }

    private static MessageDTO createMessageDTO(MessageEntity messageEntity) {
        return new MessageDTO(messageEntity.getMessage().getText(), messageEntity.getAuthor().getUuid());
    }
}
