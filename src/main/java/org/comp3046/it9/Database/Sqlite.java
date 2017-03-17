package org.comp3046.it9.Database;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

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

    public void close() throws IOException {
        try {
            this.closeConnection();
        } catch (SQLException ignored) {
        }
    }

    public DSLContext getDsl() {
        return DSL.using(this.getConnection(), SQLDialect.SQLITE);
    }
}
