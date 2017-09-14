package ua.sustav.model;

/**
 * Created by SUSTAVOV on 14.09.2017.
 */
public class Section {
    private String type;
    private String description;

    public Section(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
