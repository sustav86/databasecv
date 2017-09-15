package ua.sustav.model;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public enum ContactType {
    TELEPHON("Телефон"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKEDIN("LinkedIn");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
