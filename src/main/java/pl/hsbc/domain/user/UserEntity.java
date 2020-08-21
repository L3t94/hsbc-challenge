package pl.hsbc.domain.user;

import java.util.List;
import java.util.UUID;

public class UserEntity {
    private UUID uuid;
    private List<UserEntity> followedUsers;
    private String login;


    public UserEntity(UUID uuid, String login, List<UserEntity> followedUsers) {
        this(login, followedUsers);
        if(uuid == null){
            throw new IllegalArgumentException("userEntity uuid is null");
        }
        this.uuid = uuid;
    }

    public UserEntity(String login,List<UserEntity> followedUsers) {
        this.followedUsers = followedUsers;
        this.login = login;
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

    public List<UserEntity> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(List<UserEntity> followedUsers) {
        this.followedUsers = followedUsers;
    }


}
