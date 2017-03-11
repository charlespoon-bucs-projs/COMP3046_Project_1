package Database;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class Sqlite implements Closeable, AutoCloseable {
    private Connection connection;

    Sqlite() throws SQLException {
        this.connection = createConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    static Connection createConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        // config.setReadOnly(true);
        config.setSharedCache(true);
        config.enableRecursiveTriggers(true);


        SQLiteDataSource ds = new SQLiteDataSource(config);
        ds.setUrl("jdbc:sqlite:cinema.db");
        return ds.getConnection();
        //ds.setServerName("cinema.db");
    }

    void closeConnection() throws SQLException {
        this.connection.close();
    }

    @Override
    public void close() throws IOException {
        try {
            this.closeConnection();
        } catch (SQLException ignored) {
        }
    }
}
