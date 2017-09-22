package ua.sustav.databasecv.model;

import ua.sustav.databasecv.web.SectionHtmlType;

import java.io.Serializable;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public enum SectionType implements Serializable {
    OBJECTIVE("ПОЗИЦИЯ", SectionHtmlType.TEXT),
    ACHIEVEMENTS("ДОСТИЖЕНИЯ", SectionHtmlType.MULTI_TEXT),
    QUALIFICATION("КВАЛИФИКАЦИЯ", SectionHtmlType.MULTI_TEXT),
    EXPIRIENCE("ОПЫТ РАБОТЫ", SectionHtmlType.ORGANIZATION),
    EDUCATION("ОБРАЗОВАНИЕ", SectionHtmlType.ORGANIZATION);

    private String title;
    private SectionHtmlType htmlType;

    SectionType(String title, SectionHtmlType htmlType) {
        this.title = title;
        this.htmlType = htmlType;
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

    public SectionHtmlType getHtmlType() {
        return htmlType;
    }
}
