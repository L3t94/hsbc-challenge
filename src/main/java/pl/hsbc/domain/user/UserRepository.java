package pl.hsbc.domain.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findByUUID(UUID uuid);
    void followUser(UUID followerUser, UUID followedUser);

    List<UserEntity> findAllUsers();
}
