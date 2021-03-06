package ua.sustav.databasecv.storage;

import ua.sustav.databasecv.model.*;
import ua.sustav.databasecv.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by SUSTAVOV on 20.09.2017.
 */
public class JsonStorage extends FileStorage {

    public JsonStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try(Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(resume, writer);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try(Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(reader, Resume.class);
        }
    }
}
