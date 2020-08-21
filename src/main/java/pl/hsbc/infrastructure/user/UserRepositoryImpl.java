package pl.hsbc.infrastructure.user;

import org.springframework.stereotype.Repository;
import pl.hsbc.domain.user.UserEntity;
import pl.hsbc.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private List<UserEntity> allUsers = new ArrayList<>();
    @Override
    public UserEntity save(UserEntity userEntity) {
        UserEntity user = UserFactory.createUser(userEntity);
        allUsers.add(user);
        return user;
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return allUsers.stream().filter(user-> user.getLogin().equals(login)).findFirst();
    }

    @Override
    public Optional<UserEntity> findByUUID(UUID uuid) {
        return allUsers.stream().filter(user-> user.getUuid().equals(uuid)).findFirst();
    }

    @Override
    public void followUser(UUID followerUser, UUID followedUser) {
        Optional<UserEntity> followerOpt = findByUUID(followerUser);
        UserEntity followerEntity = followerOpt.orElseThrow(() -> new IllegalStateException("Follower user not exist"));
        Optional<UserEntity> followedUserOpt = findByUUID(followedUser);
        UserEntity followedUserEntity = followedUserOpt.orElseThrow(() -> new IllegalStateException("Followed user not exist"));
        if(!followerEntity.getFollowedUsers().contains(followedUserEntity)) {
            followerEntity.getFollowedUsers().add(followedUserEntity);
        }
    }

    @Override
    public List<UserEntity>  findAllUsers() {
        return allUsers;
    }


}
