package pl.hsbc.application.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hsbc.application.ResponseDTO;
import pl.hsbc.application.user.dto.FollowedUsersDTO;
import pl.hsbc.application.user.dto.FollowerDTO;
import pl.hsbc.application.user.dto.UsersDTO;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/user")
class UserRestController {
    private final UserFacade userFacade;

    public UserRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PutMapping("/followed")
    ResponseEntity<ResponseDTO> addFollower(@RequestBody FollowerDTO followerDTO, HttpSession httpSession) {
        if (followerDTO.getFollowedUser() == null) {
            return new ResponseEntity<>(new ResponseDTO("followed user is empty"), HttpStatus.BAD_REQUEST);
        }
        userFacade.followUser(httpSession.getId(), followerDTO.getFollowedUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/followed")
    ResponseEntity<FollowedUsersDTO> findFollowedUsers(HttpSession httpSession) {
        return new ResponseEntity<>(userFacade.getFollowedUsers(httpSession.getId()), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<UsersDTO> findAllUsers(){
        return new ResponseEntity<>(userFacade.findAllUsers(),HttpStatus.OK);
    }
}
