package esp.adivinanzas.trabalenguas;

public class Trabalenguas {
    private String section;
    private String text;

    public Trabalenguas(String section, String text) {
        this.section = section;
        this.text = text;
    }

    public Trabalenguas() {

    }

    public String getSection() {
        return section;
    }

    public String getText() {
        return text;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setText(String text) {
        this.text = text;
    }
}
