package pl.hsbc.application.user;

import org.springframework.stereotype.Service;
import pl.hsbc.application.user.dto.FollowedUsersDTO;
import pl.hsbc.application.user.dto.UsersDTO;
import pl.hsbc.domain.user.UserService;

import java.util.UUID;

@Service
class UserFacade {
    private final UserService userService;

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    void followUser(String userLogin, UUID followedUserUUID){
        userService.followUser(userLogin,followedUserUUID);
    }

    public FollowedUsersDTO getFollowedUsers(String userLogin) {
        return UserMapper.mapToFollowedUsersDTO(userService.findFollowedUsers(userLogin));
    }

    public UsersDTO findAllUsers() {
        return UserMapper.mapToUsersDTO(userService.findAllUsers());
    }
}
