package ua.sustav.databasecv.storage;

import ua.sustav.databasecv.DataBaseCVException;
import ua.sustav.databasecv.model.Resume;
import ua.sustav.databasecv.sql.ConnectionFactory;
import ua.sustav.databasecv.sql.Sql;
import ua.sustav.databasecv.sql.SqlExecutor;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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
        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, resume.getFullName());
                preparedStatement.setString(3, resume.getLocation());
                preparedStatement.setString(4, resume.getHomePage());
                preparedStatement.execute();

                return null;
            }
        });
    }

    @Override
    public void update(final Resume resume) {
        sql.execute("UPDATE resume SET full_name=?, location=?, home_page=? WHERE uuid=?", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(4, resume.getUuid());
                preparedStatement.setString(1, resume.getFullName());
                preparedStatement.setString(2, resume.getLocation());
                preparedStatement.setString(3, resume.getHomePage());
                if (preparedStatement.executeUpdate() == 0) {
                    throw new DataBaseCVException("Resume not found", resume);
                }

                return null;
            }
        });
    }

    @Override
    public Resume load(String uuid) {
        return sql.execute("SELECT * FROM resume r WHERE r.uuid=?", new SqlExecutor<Resume>() {
            @Override
            public Resume execute(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, uuid);
                ResultSet result = preparedStatement.executeQuery();
                if (!result.next()) {
                    throw new DataBaseCVException("Resume with uuid = " + uuid + " is't exist", uuid);
                }
                Resume resume = new Resume(uuid, result.getString("full_name"),
                        result.getString("location"),
                        result.getString("home_page"));

                return resume;
            }
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
        return sql.execute("SELECT * FROM resume ORDER BY full_name, uuid", new SqlExecutor<Collection<Resume>>() {
            @Override
            public Collection<Resume> execute(PreparedStatement preparedStatement) throws SQLException {
                List<Resume> result = new LinkedList<>();
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    result.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"),
                            resultSet.getString("location"),
                            resultSet.getString("home_page")));
                }
                return result;
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
}
