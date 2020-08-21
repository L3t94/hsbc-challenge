package pl.hsbc.application.user;

import pl.hsbc.application.user.dto.FollowedUsersDTO;
import pl.hsbc.application.user.dto.UserDTO;
import pl.hsbc.application.user.dto.UsersDTO;
import pl.hsbc.domain.user.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

class UserMapper {
    static UserDTO map(UserEntity userEntity) {
        return new UserDTO(userEntity.getLogin(), userEntity.getUuid());
    }

    static FollowedUsersDTO mapToFollowedUsersDTO(List<UserEntity> userEntities) {
        return new FollowedUsersDTO(
                userEntities.stream()
                        .map(userEntity -> map(userEntity))
                        .collect(Collectors.toList())
        );
    }

    static UsersDTO mapToUsersDTO(List<UserEntity> userEntities) {
        return new UsersDTO(
                userEntities.stream()
                        .map(userEntity -> map(userEntity))
                        .collect(Collectors.toList())
        );

    }
}
