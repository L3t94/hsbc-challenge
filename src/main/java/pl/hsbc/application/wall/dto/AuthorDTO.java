package pl.hsbc.application.wall.dto;

import java.util.UUID;

public class AuthorDTO {
    private UUID id;
    private String login;

    public AuthorDTO(UUID id, String login) {
        this.id = id;
        this.login = login;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
