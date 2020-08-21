package pl.hsbc.application.user.dto;

import java.util.UUID;

public class FollowerDTO {
    private UUID followedUser;

    public UUID getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(UUID followedUser) {
        this.followedUser = followedUser;
    }
    public FollowerDTO(){}

    public FollowerDTO(UUID followedUser) {
        this.followedUser = followedUser;
    }
}
