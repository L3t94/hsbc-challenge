package pl.hsbc.application.user.dto;

import java.util.List;

public class FollowedUsersDTO {
    private List<UserDTO> followedUsers;


    public FollowedUsersDTO(List<UserDTO> followedUsers) {
        this.followedUsers = followedUsers;
    }

    public FollowedUsersDTO() {
    }

    public List<UserDTO> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(List<UserDTO> followedUsers) {
        this.followedUsers = followedUsers;
    }
}
