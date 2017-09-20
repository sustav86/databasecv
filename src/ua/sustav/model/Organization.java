package ua.sustav.model;

import ua.sustav.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    static final long serialVersionUID = 1L;

    private String url = "";
    private String name = "";
    private List<Period> periods = new LinkedList<>();
    private Link link = Link.EMPTY;

    public Organization(String name, Link link, Period... periods) {
        this.name = name;
        this.periods = Arrays.asList(periods);
        this.link = link;
    }

    public Organization(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public Organization(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public Link getLink() {
        return link;
    }

    public Organization() {
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        static final long serialVersionUID = 1L;

        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String position;
        private String content;

        public Period() {
        }

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            Objects.requireNonNull(startDate, "null startDate");
            Objects.requireNonNull(position, "null position");
            this.startDate = startDate;
            this.endDate = endDate == null ? NOW: endDate;
            this.position = position;
            this.content = content == null ? "": content;
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content) {
            this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), position, content);
        }

        public static LocalDate getNOW() {
            return NOW;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return position;
        }

        public String getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;

            if (!startDate.equals(period.startDate)) return false;
            if (!endDate.equals(period.endDate)) return false;
            if (!position.equals(period.position)) return false;
            return content.equals(period.content);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + position.hashCode();
            result = 31 * result + content.hashCode();
            return result;
        }
    }


}
