package ua.sustav.databasecv.web;

import ua.sustav.databasecv.model.ContactType;
import ua.sustav.databasecv.model.Resume;

/**
 * Created by SUSTAVOV on 21.09.2017.
 */
public class HtmlUtil {
//    public static final String EMPTY_TD = "<img src='img/s.gif'>";

    public static String  getContact(Resume resume, ContactType type) {
        String contact = resume.getContact(type);
        return contact == null ? "&nbsp;" : type.toHtml(contact);
    }
}
