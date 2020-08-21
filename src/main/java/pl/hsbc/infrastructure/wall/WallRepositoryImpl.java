package pl.hsbc.infrastructure.wall;

import org.springframework.stereotype.Repository;
import pl.hsbc.domain.wall.MessageEntity;
import pl.hsbc.domain.user.UserEntity;
import pl.hsbc.domain.wall.WallEntity;
import pl.hsbc.domain.wall.WallRepository;

import java.util.*;

@Repository
public class WallRepositoryImpl implements WallRepository {
    private List<WallEntity> allWalls = new ArrayList<>();

    @Override
    public Optional<WallEntity> findByUserEntity(UserEntity userEntity) {
        return allWalls.stream()
                .filter(wall-> wall.getUser().getUuid().equals(userEntity.getUuid()))
                .findFirst();
    }

    @Override
    public WallEntity save(WallEntity wallEntity) {
        WallEntity createdWall = WallFactory.createWall(wallEntity);
        allWalls.add(createdWall);
        return createdWall;
    }

    public void  addMessageToWall(UUID wallId, MessageEntity messageEntity){
        WallEntity wallEntity = allWalls.stream()
                .filter(wall -> wall.getUuid().equals(wallId))
                .findFirst().orElseThrow(() -> new IllegalStateException("wall not found to add message wallId = "+wallId));
        wallEntity.addMessage(messageEntity);
    }
}
