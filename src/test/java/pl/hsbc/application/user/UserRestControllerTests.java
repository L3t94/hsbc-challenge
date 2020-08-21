package pl.hsbc.application.user;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import pl.hsbc.application.RAUtil;
import pl.hsbc.application.user.dto.FollowedUsersDTO;
import pl.hsbc.application.user.dto.FollowerDTO;
import pl.hsbc.application.user.dto.UserDTO;
import pl.hsbc.domain.user.UserService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
/*
    It is integration test but for purpose of this task not needed to be separated. Running as unit test.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRestControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;


    @Test
    void follow_user_then_check_followed_user() {
        //given
        RAUtil raUtil = new RAUtil(port);

        String followedUserLogin = addMessageInFirstSession(raUtil);
        UUID followedUserUUID = userService.findUserByLogin(followedUserLogin).orElseThrow().getUuid();
        String followerUser = addMessageInSecondSession(raUtil);

        RequestSpecification requestFollowUser = raUtil.prepareFollowRequest(followerUser, new FollowerDTO(followedUserUUID));
        //when
        Response response = raUtil.followRequest(requestFollowUser);
        //then
        response.then().statusCode(HttpStatus.OK.value());

        FollowedUsersDTO followedUsersDTO = raUtil.getFollowedUsers(followerUser);
        List<UserDTO> followedUsers = followedUsersDTO.getFollowedUsers();

        assertAll(
                () -> Assertions.assertThat(followedUsers.size()).isEqualTo(1),
                () -> Assertions.assertThat(followedUsers.get(0).getLogin()).isEqualTo(followedUserLogin),
                () -> Assertions.assertThat(followedUsers.get(0).getUuid()).isEqualTo(followedUserUUID)
        );
    }

    private String addMessageInFirstSession(RAUtil raUtil) {
        return raUtil.addMessageAndReturnUserLogin("test1");
    }

    private String addMessageInSecondSession(RAUtil raUtil) {
        return raUtil.addMessageAndReturnUserLogin("test2");
    }


}
