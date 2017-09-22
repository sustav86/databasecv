package ua.sustav.databasecv.web;

import ua.sustav.databasecv.model.MultiTextSection;
import ua.sustav.databasecv.model.Section;
import ua.sustav.databasecv.model.SectionType;
import ua.sustav.databasecv.model.TextSection;

import java.util.Collections;

import static ua.sustav.databasecv.web.HtmlUtil.input;
import static ua.sustav.databasecv.web.HtmlUtil.textArea;

/**
 * Created by SUSTAVOV on 22.09.2017.
 */
public enum SectionHtmlType {
    TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return input(type.name(), section == null ? "" : ((TextSection) section).getTitle());
        }

        @Override
        public TextSection createSection(String value) {
            return new TextSection(value);
        }
    },
    MULTI_TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return textArea(type.name(), section == null ? Collections.singletonList("") : ((MultiTextSection) section).getValues());
        }

        @Override
        public MultiTextSection createSection(String value) {
            return new MultiTextSection(value.split("\\n"));
        }
    },
    ORGANIZATION {
        @Override
        public String toHtml(Section section, SectionType type) {
            return section.toString();
        }

        @Override
        public Section createSection(String value) {
            throw new UnsupportedOperationException();
        }
    };

    public abstract String toHtml(Section section, SectionType type);
    public abstract Section createSection(String value);

}
