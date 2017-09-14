package ua.sustav.model;

/**
 * Created by SUSTAVOV on 14.09.2017.
 */
public class Organization extends Link {
    private final String description;

    public Organization(String name, String url, String description) {
        super(name, url);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
