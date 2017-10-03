package javase01.t06;

/**
 * Represents a single text note
 */
public class Note {
    private String text;

    public Note(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
