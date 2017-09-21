package ua.sustav.databasecv.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by SUSTAVOV on 22.09.2017.
 */
public interface SqlExecutor<T> {
    T execute(PreparedStatement preparedStatement) throws SQLException;
}
