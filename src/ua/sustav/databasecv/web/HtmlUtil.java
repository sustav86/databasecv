package ua.sustav.databasecv.web;

import ua.sustav.databasecv.model.ContactType;
import ua.sustav.databasecv.model.Organization;
import ua.sustav.databasecv.model.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by SUSTAVOV on 21.09.2017.
 */
public class HtmlUtil {
//    public static final String EMPTY_TD = "<img src='img/s.gif'>";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static String mask(String str) {
        return str == null || str.isEmpty() ? "&nbsp;" : str;
    }


    public static String format(LocalDate date) {
        return date.equals(Organization.Period.NOW) ? "Сейчас" : date.format(DATE_FORMATTER);
    }

    public static String textArea(String name, List<String> list) {
        return String.format("<textarea name=%s cols=75 rows=5>%s</textarea>", name, String.join("\n", list));
    }

    public static String input(String name, String value) {
        return String.format("<input type='text' name=%s size=75 value=%s>", name, value);
    }

    public static String  getContact(Resume resume, ContactType type) {
        String contact = resume.getContact(type);
        return contact == null ? "&nbsp;" : type.toHtml(contact);
    }


}
