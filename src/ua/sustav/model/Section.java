package ua.sustav.model;

/**
 * Created by SUSTAVOV on 14.09.2017.
 */
public class Section {
    private SectionType type;
    private String description;

    public Section(SectionType type, String description) {
        this.type = type;
        this.description = description;
    }

    public SectionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
