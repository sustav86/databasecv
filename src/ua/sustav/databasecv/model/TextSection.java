package ua.sustav.databasecv.model;

import java.io.Serializable;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public class TextSection extends Section implements Serializable {
    static final long serialVersionUID = 1L;
    private String title;

    public TextSection(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "title='" + title + '\'' +
                '}';
    }

    public TextSection() {
    }
}
