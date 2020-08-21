package pl.hsbc.domain.user;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findUserByLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("User login is null");
        }
        return userRepository.findByLogin(login);
    }

    public UserEntity saveUser(UserEntity userEntity) {
        if (userEntity.getLogin() == null) {
            throw new IllegalArgumentException("User login is null");
        }
        return userRepository.save(userEntity);
    }

    public void followUser(String followerLogin, UUID followedUserUUID) {
        Optional<UserEntity> followerOpt = userRepository.findByLogin(followerLogin);
        followerOpt.orElseThrow(() -> new IllegalArgumentException("user " + followerLogin + " not exist"));
        userRepository.followUser(followerOpt.get().getUuid(),followedUserUUID);
    }

    public List<UserEntity> findFollowedUsers(String userLogin) {
        Optional<UserEntity> userEntityOpt = userRepository.findByLogin(userLogin);
        UserEntity userEntity = userEntityOpt.orElseThrow(() ->  new IllegalStateException( "user " + userLogin + " not exist"));
        return userEntity.getFollowedUsers();
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAllUsers();
    }
}
