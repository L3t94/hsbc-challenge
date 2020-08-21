package pl.hsbc.infrastructure.user;

import pl.hsbc.domain.user.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class UserFactory {
    static UserEntity createUser(UserEntity userEntity){
        List<UserEntity> followed ;
        if(userEntity.getFollowedUsers() == null || userEntity.getFollowedUsers().isEmpty()){
            followed = new ArrayList<>();
        }else{
            followed = userEntity.getFollowedUsers();
        }
        return new UserEntity(UUID.randomUUID(),userEntity.getLogin(), followed);
    }
}
