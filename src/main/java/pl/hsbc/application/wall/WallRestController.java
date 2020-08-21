package pl.hsbc.application.wall;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hsbc.application.ResponseDTO;
import pl.hsbc.application.wall.dto.MessageDTO;
import pl.hsbc.application.wall.dto.MessagesDTO;
import pl.hsbc.application.wall.dto.WallMessagesDTO;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("/rest/wall")
class WallRestController {
    private final WallFacade wallFacade;

    public WallRestController(WallFacade wallFacade) {
        this.wallFacade = wallFacade;
    }

    @GetMapping("/messages")
    ResponseEntity<WallMessagesDTO> getWallMessages(HttpSession httpSession) {
        WallMessagesDTO wallMessagesDTO = wallFacade.findWallMessages(httpSession.getId());
        return new ResponseEntity<>(wallMessagesDTO, HttpStatus.OK);
    }

    @PostMapping("/messages")
    ResponseEntity<ResponseDTO> saveTextMessage(@RequestBody MessageDTO messageDTO, HttpSession httpSession) {
        if (messageDTO.getText() == null || messageDTO.getText().isBlank()) {
            return new ResponseEntity<>(new ResponseDTO("text message is empty"), HttpStatus.BAD_REQUEST);
        }
        if (messageDTO.getText().length() > 140) {
            return new ResponseEntity<>(new ResponseDTO("text message is too long " + messageDTO.getText().length()), HttpStatus.BAD_REQUEST);
        }
        UUID uuid = wallFacade.publishMessage(messageDTO, httpSession.getId());
        ResponseDTO responseDTO = new ResponseDTO(uuid);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/messages/followed")
    ResponseEntity<MessagesDTO> getFollowedUsersWallMessages(HttpSession httpSession) {
        return new ResponseEntity<>(wallFacade.findAllFollowedUsersMessages(httpSession.getId()), HttpStatus.OK);
    }

}
