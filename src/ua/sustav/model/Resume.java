package ua.sustav.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public static final Resume EMPTY;

    static {
        EMPTY = new Resume();
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public static Resume getEMPTY() {
        return EMPTY;
    }

    public Resume() {
    }

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
//        Objects.requireNonNull(uuid, "null uuid");
//        Objects.requireNonNull(fullName, "null fullName");
//        Objects.requireNonNull(location, "null location");
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Resume resume = (Resume) o;
//
//        if (uuid != null ? !uuid.equals(resume.uuid) : resume.uuid != null) return false;
//        if (fullName != null ? !fullName.equals(resume.fullName) : resume.fullName != null) return false;
//        if (location != null ? !location.equals(resume.location) : resume.location != null) return false;
//        if (homePage != null ? !homePage.equals(resume.homePage) : resume.homePage != null) return false;
//        if (contacts != null ? !contacts.equals(resume.contacts) : resume.contacts != null) return false;
//        return sections != null ? sections.equals(resume.sections) : resume.sections == null;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (uuid != null ? !uuid.equals(resume.uuid) : resume.uuid != null) return false;
        if (fullName != null ? !fullName.equals(resume.fullName) : resume.fullName != null) return false;
        if (location != null ? !location.equals(resume.location) : resume.location != null) return false;
        return homePage != null ? homePage.equals(resume.homePage) : resume.homePage == null;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    public void addContact(ContactType type, String contact) {
        contacts.put(type, contact);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", homePage='" + homePage + '\'' +
                '}';
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addObjective(String value) {
        addSection(SectionType.OBJECTIVE, new TextSection(value));
    }

    public void addMultiTextSection(SectionType type, String... values) {
        addSection(type, new MultiTextSection(values));
    }

    public void addOrganizationSection(SectionType type, Organization... organizations) {
        addSection(type, new OrganizationSection(organizations));
    }
}
