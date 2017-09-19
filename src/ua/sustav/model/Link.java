package ua.sustav.model;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public class Link {
    static final long serialVersionUID = 1L;

    public static Link EMPTY = new Link();
    private final String name;
    private final String url;

    public Link() {
        this("", null);
    }

    public Link(String name, String url) {
        this.name = name;
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
}
