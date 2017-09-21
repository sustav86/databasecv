package ua.sustav.databasecv.sql;

import ua.sustav.databasecv.DataBaseCVException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by SUSTAVOV on 22.09.2017.
 */
public class Sql {
    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.execute();
                return null;
            }
        });
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw new DataBaseCVException("Connection fails to DB " + sql, e);
        }
    }
}
