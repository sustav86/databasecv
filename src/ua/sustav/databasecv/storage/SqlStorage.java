package ua.sustav.databasecv.storage;

import ua.sustav.databasecv.DataBaseCVException;
import ua.sustav.databasecv.model.ContactType;
import ua.sustav.databasecv.model.Resume;
import ua.sustav.databasecv.sql.Sql;
import ua.sustav.databasecv.sql.SqlExecutor;
import ua.sustav.databasecv.util.Util;

import java.sql.*;
import java.util.*;

/**
 * Created by SUSTAVOV on 21.09.2017.
 */
public class SqlStorage implements IStorage {

    private Sql sql;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sql.execute("DELETE FROM resume");
    }

    @Override
    public void save(final Resume resume) {
        sql.execute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)")) {

                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.setString(3, resume.getLocation());
                ps.setString(4, resume.getHomePage());
                if (ps.executeUpdate() == 0) {
                    throw new DataBaseCVException("Resume not found", resume);
                }
            }
            insertContact(connection, resume);
            return null;
        });
    }


    @Override
    public void update(final Resume resume) {
        sql.execute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name=?, location=?, home_page=? WHERE uuid=?")) {
                ps.setString(4, resume.getUuid());
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getLocation());
                ps.setString(3, resume.getHomePage());
                ps.execute();
            }
            deleteContacts(connection, resume);
            insertContact(connection, resume);
            return null;

        });
    }

    @Override
    public Resume load(String uuid) {
        return sql.execute("SELECT * FROM resume r LEFT JOIN contact c ON c.resume_uuid = r.uuid WHERE r.uuid=?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) {
                throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
            }
            Resume resume = new Resume(uuid, result.getString("full_name"),
                    result.getString("location"),
                    result.getString("home_page"));
            addContact(result, resume);
            while (result.next()) {
                addContact(result, resume);
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sql.execute("DELETE FROM resume WHERE uuid=?", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, uuid);
                if (preparedStatement.executeUpdate() == 0) {
                    throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
                }
                return null;
            }
        });
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return sql.execute("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid ORDER BY full_name, uuid", new SqlExecutor<Collection<Resume>>() {
            @Override
            public Collection<Resume> execute(PreparedStatement preparedStatement) throws SQLException {
                List<Resume> result = new LinkedList<>();
                Map<String, Resume> map = new LinkedHashMap<>();
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    Resume resume = map.get(uuid);
                    if (resume == null) {
                        resume = new Resume(uuid, resultSet.getString("full_name"),
                                resultSet.getString("location"),
                                resultSet.getString("home_page"));
                        map.put(uuid, resume);
                    }
                    addContact(resultSet, resume);
                    result.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"),
                            resultSet.getString("location"),
                            resultSet.getString("home_page")));
                }
                return map.values();
            }
        });
    }

    @Override
    public int size() {
        return sql.execute("SELECT count(*) FROM resume", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement preparedStatement) throws SQLException {
                ResultSet result = preparedStatement.executeQuery();
                result.next();
                return result.getInt(1);
            }
        });
    }

    private void insertContact(Connection connection, Resume resume) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection connection, Resume resume) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addContact(ResultSet resultSet, Resume resume) throws SQLException {
        String value = resultSet.getString("value");
        if (!Util.isEmpty(value)) {

            ContactType contactType = ContactType.valueOf(resultSet.getString("type"));
            resume.addContact(contactType, value);
        }
    }
}
