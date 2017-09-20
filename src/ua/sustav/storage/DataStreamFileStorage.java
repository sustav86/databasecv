package ua.sustav.storage;

import ua.sustav.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * Created by SUSTAVOV on 19.09.2017.
 */
public class DataStreamFileStorage extends FileStorage {
    private final String NULL = "NULL";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(os)) {
            writeString(dos, resume.getUuid());
            writeString(dos, resume.getFullName());
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();

            writeCollections(dos, contacts.entrySet(), entry -> {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());
            });

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry: sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());
                switch (type) {
                    case OBJECTIVE:
                        writeString(dos, ((TextSection)section).getTitle());
                        break;
                    case ACHIEVEMENTS:
                    case QUALIFICATION:
                        writeCollections(dos, ((MultiTextSection) section).getValues(), value -> writeString(dos, value));
                        break;
                    case EDUCATION:
                    case EXPIRIENCE:
                        writeCollections(dos, ((OrganizationSection) section).getValues(), (org) -> {
                            dos.writeUTF(org.getLink().getName());
                            dos.writeUTF(org.getLink().getUrl());
                            writeCollections(dos, org.getPeriods(), period -> {
                                writeLocalDate(dos, period.getStartDate());
                                writeLocalDate(dos, period.getEndDate());
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getContent());
                            });
                        });
                    default:
                        break;
                }
            }
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        Resume result = new Resume();
        try(DataInputStream dis = new DataInputStream(is)) {
            result.setUuid(readString(dis));
            result.setFullName(readString(dis));
            result.setLocation(readString(dis));
            result.setHomePage(readString(dis));
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                result.addContact(ContactType.VALUES[dis.readInt()], readString(dis));
            }
            final int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(readString(dis));
                switch (sectionType) {
                    case OBJECTIVE:
                        result.addObjective(readString(dis));
                        break;
                    case ACHIEVEMENTS:
                    case QUALIFICATION:
                        result.addSection(sectionType, new MultiTextSection(readList(dis, () -> readString(dis))));
                        break;
                    case EDUCATION:
                    case EXPIRIENCE:
                        result.addSection(sectionType, new OrganizationSection(readList(dis, new ElementReader<Organization>() {
                            @Override
                            public Organization read() throws IOException {
                                return new Organization(new Link(dis.readUTF(), dis.readUTF()), readList(dis, new ElementReader<Organization.Period>() {
                                    @Override
                                    public Organization.Period read() throws IOException {
                                        return new Organization.Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF());
                                    }
                                }));
                            }
                        })));
                        break;
                    default:
                        break;
                }
            }
        }

        return result;
    }


    private void writeString(DataOutputStream dos, String value) throws IOException {
        dos.writeUTF(value == null ? NULL: value);
    }

    private String readString(DataInputStream dis) throws IOException {
        String value = dis.readUTF();
        return value.equals(NULL) ? null : value;
    }

    private interface ElementWrite<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> void writeCollections(DataOutputStream dos, Collection<T> collection, ElementWrite<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
           list.add(reader.read());
        }
        return list;
    }

    public void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
//        Objects.requireNonNull(localDate, "null localDate");
        dos.writeInt(localDate.getYear());
        dos.writeUTF(localDate.getMonth().name());
    }

    public LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
    }
}
