package pl.hsbc.domain.wall;

public class Message {
    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    private String text;
}
