package ua.sustav.databasecv.model;

import java.io.Serializable;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public enum SectionType implements Serializable {
    OBJECTIVE("ПОЗИЦИЯ"),
    ACHIEVEMENTS("ДОСТИЖЕНИЯ"),
    QUALIFICATION("КВАЛИФИКАЦИЯ"),
    EXPIRIENCE("ОПЫТ РАБОТЫ"),
    EDUCATION("ОБРАЗОВАНИЕ");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "SectionType{" +
                "title='" + title + '\'' +
                '}';
    }
}
