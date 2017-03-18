package org.comp3046.it9.Database;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Sqlite implements Closeable, AutoCloseable {
    private static final String SQLITE3_FILE_PATH = "cinema.sqlite3";

    private Connection connection;

    public Sqlite() throws SQLException, FileNotFoundException {
        if (!(new java.io.File(SQLITE3_FILE_PATH).exists()))
            throw new FileNotFoundException(String.format("missing SQLite3 file %s", SQLITE3_FILE_PATH));

        this.connection = createConnection();
    }

    private static Connection createConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        // config.setReadOnly(true);
        config.setSharedCache(true);
        config.enableRecursiveTriggers(true);


        SQLiteDataSource ds = new SQLiteDataSource(config);
        ds.setUrl(String.format("jdbc:sqlite:%s", SQLITE3_FILE_PATH));
        return ds.getConnection();
        //ds.setServerName("cinema.sqlite3");
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
