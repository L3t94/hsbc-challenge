package pl.hsbc.application.user.dto;

import java.util.UUID;

public class UserDTO {
    private String login;
    private UUID uuid;

    public UserDTO() {
    }

    public UserDTO(String login, UUID uuid) {
        this.login = login;
        this.uuid = uuid;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }




}
