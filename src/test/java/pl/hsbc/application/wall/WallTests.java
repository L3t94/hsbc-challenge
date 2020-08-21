package pl.hsbc.application.wall;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.hsbc.application.RAUtil;
import pl.hsbc.application.wall.dto.MessageDTO;
import pl.hsbc.application.wall.dto.WallMessagesDTO;
import pl.hsbc.domain.user.UserService;
import pl.hsbc.domain.wall.MessageRepository;
import pl.hsbc.domain.wall.WallService;
import pl.hsbc.infrastructure.user.UserRepositoryImpl;
import pl.hsbc.infrastructure.wall.MessageRepositoryImpl;
import pl.hsbc.infrastructure.wall.WallRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertAll;

class WallTests {
    private final WallRepositoryImpl wallRepository = new WallRepositoryImpl();
    private final UserService userService = new UserService(new UserRepositoryImpl());
    private final MessageRepository messageRepository = new MessageRepositoryImpl(wallRepository);
    private final WallFacade sut = new WallFacade(
            new WallService(wallRepository, userService, messageRepository),
            userService
    );

    @Test
    void publish_messages_then_check_order() throws InterruptedException {
        //given
        MessageDTO message1DTO = new MessageDTO("test1");
        MessageDTO message2DTO = new MessageDTO("test2");
        MessageDTO message3DTO = new MessageDTO("test3");
        //when
        sut.publishMessage(message1DTO, "user1");
        RAUtil.simulateDelay();
        sut.publishMessage(message2DTO, "user1");
        RAUtil.simulateDelay();
        sut.publishMessage(message3DTO, "user1");
        //then
        WallMessagesDTO wallMessagesDTO = sut.findWallMessages("user1");
        assertAll(
                () -> Assertions.assertThat(wallMessagesDTO.getWallMessages().size()).isEqualTo(3),
                () -> Assertions.assertThat(wallMessagesDTO.getWallMessages().get(0).getMessage().getText()).isEqualTo(message3DTO.getText()),
                () -> Assertions.assertThat(wallMessagesDTO.getWallMessages().get(1).getMessage().getText()).isEqualTo(message2DTO.getText()),
                () -> Assertions.assertThat(wallMessagesDTO.getWallMessages().get(2).getMessage().getText()).isEqualTo(message1DTO.getText())
        );
    }


}
