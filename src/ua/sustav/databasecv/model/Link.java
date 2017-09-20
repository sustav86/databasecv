package ua.sustav.databasecv.model;

import java.io.Serializable;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public class Link implements Serializable {
    static final long serialVersionUID = 1L;

    public static Link EMPTY = new Link("empty", "empty");
    private String name = "";
    private String url = "";

    public Link() {

    }

    public Link(String name, String url) {
        this.name =name;
        this.url = url;
    }

    public Link(Link link) {
        this(link.name, link.url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!name.equals(link.name)) return false;
        return url != null ? url.equals(link.url) : link.url == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public static Link getEMPTY() {
        return EMPTY;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
