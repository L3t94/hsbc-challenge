package pl.hsbc.application;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import pl.hsbc.application.user.dto.FollowedUsersDTO;
import pl.hsbc.application.wall.dto.MessageDTO;
import pl.hsbc.application.user.dto.FollowerDTO;
import pl.hsbc.application.wall.dto.MessagesDTO;
import pl.hsbc.application.wall.dto.WallMessagesDTO;

public class RAUtil {
    private final int port;

    public RAUtil(int port) {
        this.port = port;
    }

    public Response postForMessages(RequestSpecification requestSpecification) {
        return requestSpecification.post("hsbc-challenge/rest/wall/messages");
    }

    public RequestSpecification prepareMessagesRequest(MessageDTO messageDTO) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .port(this.port)
                .body(messageDTO, ObjectMapperType.JACKSON_2);
    }

    public WallMessagesDTO getWallMessages(MessageDTO messageDTO, Response response) {
        return RestAssured
                .given()
                .sessionId(response.getSessionId())
                .contentType(ContentType.JSON)
                .port(this.port)
                .body(messageDTO, ObjectMapperType.JACKSON_2)
                .get("hsbc-challenge/rest/wall/messages")
                .getBody()
                .as(WallMessagesDTO.class);
    }

    public MessagesDTO getFollowedUsersMessages(String userLogin) {
        return RestAssured
                .given()
                .sessionId(userLogin)
                .contentType(ContentType.JSON)
                .port(this.port)
                .get("hsbc-challenge/rest/wall/messages/followed")
                .getBody()
                .as(MessagesDTO.class);
    }

    public Response followRequest(RequestSpecification requestFollowing) {
        return requestFollowing.put("hsbc-challenge/rest/user/followed");
    }

    public RequestSpecification prepareFollowRequest(String followerLogin, FollowerDTO followerDTO) {
        RequestSpecification requestFollowing = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .port(this.port)
                .sessionId(followerLogin)
                .body(followerDTO, ObjectMapperType.JACKSON_2);
        return requestFollowing;
    }
    public FollowedUsersDTO getFollowedUsers(String userLogin) {
        return RestAssured
                .given()
                .sessionId(userLogin)
                .contentType(ContentType.JSON)
                .port(this.port)
                .get("hsbc-challenge/rest/user/followed")
                .getBody()
                .as(FollowedUsersDTO.class);
    }

    public String addMessageAndReturnUserLogin(String message) {
        MessageDTO messageDTO = new MessageDTO(message);
        RequestSpecification requestSpecification = prepareMessagesRequest(messageDTO);
        Response responseMessages = postForMessages(requestSpecification);
        return responseMessages.getSessionId();
    }

    public void addMessageAsUser(String message, String userLogin) {
        MessageDTO messageDTO = new MessageDTO(message);
        RequestSpecification requestSpecification = prepareMessagesRequest(messageDTO);
        requestSpecification.sessionId(userLogin);
        postForMessages(requestSpecification);
    }

    public static void simulateDelay() throws InterruptedException {
        Thread.sleep(1);
    }

}
