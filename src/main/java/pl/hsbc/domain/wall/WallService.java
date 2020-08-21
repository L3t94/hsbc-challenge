package pl.hsbc.domain.wall;

import org.springframework.stereotype.Service;
import pl.hsbc.domain.user.UserEntity;
import pl.hsbc.domain.user.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WallService {
    private final WallRepository wallRepository;
    private final UserService userService;
    private final MessageRepository messageRepository;

    public WallService(WallRepository wallRepository, UserService userService, MessageRepository messageRepository) {
        this.wallRepository = wallRepository;
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    public List<MessageEntity> findWallMessages(String userLogin){
        UserEntity userEntity = findUserEntity(userLogin);
        WallEntity wallEntity = findWall(userEntity);
        return wallEntity.getReversedDateTimeOrderedMessages();
    }

    private WallEntity findWall(UserEntity userEntity) {
        Optional<WallEntity> wallOpt = findWallByUser(userEntity);
        WallEntity wallEntity = wallOpt.orElseThrow(() -> new IllegalStateException("wall for user " + userEntity.getLogin() + " not found"));
        return wallEntity;
    }

    private UserEntity findUserEntity(String userLogin) {
        Optional<UserEntity> userOpt = userService.findUserByLogin(userLogin);
        return userOpt.orElseThrow(() -> new IllegalStateException("user for login " + userLogin + " not found"));
    }

    public Optional<WallEntity> findWallByUser(UserEntity userEntity){
        return wallRepository.findByUserEntity(userEntity);
    }

    public MessageEntity publishMessage(Message message, String userLogin) {
        UserEntity userEntity = getUserOrAddIfNotExist(userLogin);
        WallEntity wallEntity = getWallOrAddIfNotExist(userEntity);
        MessageEntity messageEntity = new MessageEntity(message, wallEntity, userEntity);
        return this.messageRepository.save(messageEntity);
    }

    public List<MessageEntity> findAllFollowedUsersMessages(String userLogin){
        UserEntity userEntity = findUserEntity(userLogin);
        List<UserEntity> followedUsers = userEntity.getFollowedUsers();
        return followedUsers.stream()
                .map(this::findWall)
                .flatMap(wall->wall.getMessages().stream())
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    private WallEntity getWallOrAddIfNotExist(UserEntity userEntity) {
        Optional<WallEntity> wallOpt = findWallByUser(userEntity);
        WallEntity wallEntity;
        wallEntity = wallOpt.orElseGet(() -> saveWall(new WallEntity(userEntity, Collections.emptyList())));
        return wallEntity;
    }

    private UserEntity getUserOrAddIfNotExist(String userLogin) {
        Optional<UserEntity> userOpt = userService.findUserByLogin(userLogin);
        UserEntity userEntity;
        userEntity = userOpt.orElseGet(() -> userService.saveUser(new UserEntity(userLogin, Collections.emptyList())));
        return userEntity;
    }

    private WallEntity saveWall(WallEntity wallEntity){
        return wallRepository.save(wallEntity);
    }
}
