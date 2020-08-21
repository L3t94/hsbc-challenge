package pl.hsbc.domain.wall;

import pl.hsbc.domain.user.UserEntity;

import java.util.Optional;

public interface WallRepository {
    Optional<WallEntity> findByUserEntity(UserEntity userEntity);
    WallEntity save(WallEntity wallEntity);
}
