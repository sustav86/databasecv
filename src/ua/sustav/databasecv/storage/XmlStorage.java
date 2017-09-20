package ua.sustav.databasecv.storage;

import ua.sustav.databasecv.model.*;
import ua.sustav.databasecv.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by SUSTAVOV on 20.09.2017.
 */
public class XmlStorage extends FileStorage {
    private XmlParser xmlParser;

    public XmlStorage(String path) {
        super(path);
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class, OrganizationSection.class, TextSection.class, Organization.Period.class);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try(Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try(Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);
        }
    }
}
