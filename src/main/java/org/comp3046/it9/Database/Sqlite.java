package org.comp3046.it9.Database;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Sqlite implements Closeable, AutoCloseable {
    private Connection connection;

    public Sqlite() throws SQLException {
        this.connection = createConnection();
    }

    private static Connection createConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        // config.setReadOnly(true);
        config.setSharedCache(true);
        config.enableRecursiveTriggers(true);


        SQLiteDataSource ds = new SQLiteDataSource(config);
        ds.setUrl("jdbc:sqlite:cinema.db");
        return ds.getConnection();
        //ds.setServerName("cinema.db");
    }

    private Connection getConnection() {
        return connection;
    }

    private void closeConnection() throws SQLException {
        this.connection.close();
    }

    public void close() throws IOException {
        try {
            this.closeConnection();
        } catch (SQLException ignored) {
        }
    }

    DSLContext getDsl() {
        return DSL.using(this.getConnection(), SQLDialect.SQLITE);
    }

    int getLastInsertRowId() {
        return getDsl().fetch("SELECT last_insert_rowid()").get(0).get(0, int.class);
    }
}
