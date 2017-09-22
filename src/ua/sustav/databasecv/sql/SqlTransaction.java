package ua.sustav.databasecv.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by SUSTAVOV on 22.09.2017.
 */
public interface SqlTransaction<T> {
    T execute(Connection connection) throws SQLException;
}
