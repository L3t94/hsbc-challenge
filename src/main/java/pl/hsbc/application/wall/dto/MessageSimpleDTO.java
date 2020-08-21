package pl.hsbc.application.wall.dto;

public class MessageSimpleDTO {
    private String text;

    public MessageSimpleDTO() {
    }

    public MessageSimpleDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
