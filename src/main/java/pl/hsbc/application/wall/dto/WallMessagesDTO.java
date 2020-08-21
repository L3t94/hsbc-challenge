package pl.hsbc.application.wall.dto;

import java.util.List;
import java.util.UUID;

public class WallMessagesDTO {
    private UUID wallUUID;
    private List<WallMessageDTO> wallMessages;

    public WallMessagesDTO(UUID wallUUID, List<WallMessageDTO> wallMessages) {
        this.wallUUID = wallUUID;
        this.wallMessages = wallMessages;
    }

    public UUID getWallUUID() {
        return wallUUID;
    }

    public void setWallUUID(UUID wallUUID) {
        this.wallUUID = wallUUID;
    }

    public List<WallMessageDTO> getWallMessages() {
        return wallMessages;
    }

    public void setWallMessages(List<WallMessageDTO> wallMessages) {
        this.wallMessages = wallMessages;
    }
}
