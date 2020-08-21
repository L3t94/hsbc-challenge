package pl.hsbc.application.wall.dto;

import java.util.List;

public class MessagesDTO {
    public MessagesDTO() {
    }

    private List<MessageDTO> messages;

    public MessagesDTO(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
