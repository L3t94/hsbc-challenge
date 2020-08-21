package pl.hsbc.infrastructure.wall;

import pl.hsbc.domain.wall.MessageEntity;
import pl.hsbc.domain.wall.WallEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class WallFactory {
    static WallEntity createWall(WallEntity wallEntity){
        List<MessageEntity> messagesEntity;
        if(wallEntity.getReversedDateTimeOrderedMessages() == null || wallEntity.getReversedDateTimeOrderedMessages().isEmpty()){
            messagesEntity = new ArrayList<>();
        }else{
            messagesEntity = wallEntity.getReversedDateTimeOrderedMessages();
        }
        return new WallEntity(UUID.randomUUID(), wallEntity.getUser(), messagesEntity);
    }


}
