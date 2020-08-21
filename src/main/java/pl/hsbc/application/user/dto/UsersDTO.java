package pl.hsbc.application.user.dto;

import java.util.List;

public class UsersDTO {
    private List<UserDTO> allUsers;

    public UsersDTO() {
    }

    public UsersDTO(List<UserDTO> allUsers) {
        this.allUsers = allUsers;
    }

    public List<UserDTO> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<UserDTO> allUsers) {
        this.allUsers = allUsers;
    }
}
