package pl.hsbc.application.wall;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import pl.hsbc.application.RAUtil;
import pl.hsbc.application.user.dto.FollowerDTO;
import pl.hsbc.application.wall.dto.MessageDTO;
import pl.hsbc.application.wall.dto.MessagesDTO;
import pl.hsbc.application.wall.dto.WallMessageDTO;
import pl.hsbc.application.wall.dto.WallMessagesDTO;
import pl.hsbc.domain.user.UserService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
/*
    It is integration test but for purpose of this task not needed to be separated. Running as unit test.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WallRestControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    UserService userService;

    @Test
    void save_message_then_read_wall_one_session() {
        RAUtil raUtil = new RAUtil(port);
        //given
        MessageDTO messageDTO = new MessageDTO("test");
        RequestSpecification requestSpecification = raUtil.prepareMessagesRequest(messageDTO);
        //when
        Response response = raUtil.postForMessages(requestSpecification);
        //then
        response.then().statusCode(HttpStatus.CREATED.value());
        String messageUUID = response.getBody().jsonPath().getString("uuid");
        WallMessagesDTO wallMessagesDTO = raUtil.getWallMessages(messageDTO, response);
        List<WallMessageDTO> wallMessages = wallMessagesDTO.getWallMessages();
        assertAll(
                () -> Assertions.assertThat(wallMessages.get(0).getMessageUUID().toString()).isEqualTo(messageUUID),
                () -> Assertions.assertThat(wallMessages.get(0).getMessage().getText()).isEqualTo(messageDTO.getText())
        );
    }

    @Test
    void save_message_when_empty() {
        RAUtil raUtil = new RAUtil(port);
        //given
        MessageDTO messageDTO = new MessageDTO("");
        RequestSpecification requestSpecification = raUtil.prepareMessagesRequest(messageDTO);
        //when
        Response response = raUtil.postForMessages(requestSpecification);
        //then
        String message = response.getBody().jsonPath().getString("message");
        response.then().statusCode(HttpStatus.BAD_REQUEST.value());
        Assertions.assertThat(message).isEqualTo("text message is empty");
    }

    @Test
    void save_too_long_message_when_empty() {
        RAUtil raUtil = new RAUtil(port);
        //given
        MessageDTO messageDTO = new MessageDTO(StringUtils.repeat("a", 141));
        RequestSpecification requestSpecification = raUtil.prepareMessagesRequest(messageDTO);
        //when
        Response response = raUtil.postForMessages(requestSpecification);
        //then
        String message = response.getBody().jsonPath().getString("message");
        response.then().statusCode(HttpStatus.BAD_REQUEST.value());
        Assertions.assertThat(message).contains("text message is too long");
    }

    @Test
    void follow_user_then_check_followed_messages_order() throws InterruptedException {
        //given
        RAUtil raUtil = new RAUtil(port);
        String followedUserLogin = addMessagesInFirstSession(raUtil);
        UUID followedUserUUID = userService.findUserByLogin(followedUserLogin).orElseThrow().getUuid();
        String followerUser = addMessageInSecondSession(raUtil);
        RequestSpecification requestFollowUser = raUtil.prepareFollowRequest(followerUser, new FollowerDTO(followedUserUUID));
        raUtil.followRequest(requestFollowUser);
        //when
        MessagesDTO followedUsersMessages = raUtil.getFollowedUsersMessages(followerUser);
        //then
        assertAll(
                () -> Assertions.assertThat( followedUsersMessages.getMessages().get(0).getText()).isEqualTo("test3"),
                () -> Assertions.assertThat( followedUsersMessages.getMessages().get(1).getText()).isEqualTo("test2"),
                () -> Assertions.assertThat( followedUsersMessages.getMessages().get(2).getText()).isEqualTo("test1")
        );
    }

    private String addMessagesInFirstSession(RAUtil raUtil) throws InterruptedException {
        String userLogin = raUtil.addMessageAndReturnUserLogin("test1");
        RAUtil.simulateDelay();
        raUtil.addMessageAsUser("test2",userLogin);
        RAUtil.simulateDelay();
        raUtil.addMessageAsUser("test3",userLogin);
        RAUtil.simulateDelay();
        return userLogin;
    }

    private String addMessageInSecondSession(RAUtil raUtil) {
        return raUtil.addMessageAndReturnUserLogin("test2");
    }


}
