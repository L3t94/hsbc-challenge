package pl.hsbc.application.wall;

import org.springframework.stereotype.Service;
import pl.hsbc.application.wall.dto.MessageDTO;
import pl.hsbc.application.wall.dto.MessagesDTO;
import pl.hsbc.application.wall.dto.WallMessagesDTO;
import pl.hsbc.domain.user.UserService;
import pl.hsbc.domain.wall.Message;
import pl.hsbc.domain.wall.WallService;

import java.util.UUID;

@Service
class WallFacade {
    private final WallService wallService;

    WallFacade(WallService wallService, UserService userService) {
        this.wallService = wallService;
    }

    WallMessagesDTO findWallMessages(String userLogin) {
        return WallMessageMapper.map( wallService.findWallMessages(userLogin));
    }

    UUID publishMessage(MessageDTO messageDTO, String userLogin){
        return wallService.publishMessage(new Message(messageDTO.getText()),userLogin).getUuid();
    }

    MessagesDTO findAllFollowedUsersMessages(String userLogin){
        return MessageMapper.map(wallService.findAllFollowedUsersMessages(userLogin));
    }
}
