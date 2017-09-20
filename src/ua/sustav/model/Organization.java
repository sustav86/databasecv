package ua.sustav.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public class Organization implements Serializable {

    private String url;
    private String name;
    private List<Period> periods;
    private Link link;

    public Organization(String name, Link link, Period... periods) {
        this.name = name;
        this.periods = Arrays.asList(periods);
        this.link = link;
    }

    public Organization(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public Organization() {
    }

    public static class Period implements Serializable {
        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
        private LocalDate startDate;
        private LocalDate endDate;
        private String position;
        private String content;

        public Period() {
        }

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.content = content;
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content) {
            this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), position, content);
        }
    }


}
