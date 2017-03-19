package org.comp3046.it9.Database;

import org.comp3046.it9.Entity.Staff;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.util.Map;
import java.util.stream.Collectors;

import static org.comp3046.it9.Database.JooqGenerated.Tables.STAFF;

public class StaffDb {
    private final Sqlite sqlite;

    public StaffDb(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    private boolean createStaff(String name, String username, String password) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.insertInto(STAFF,
                    STAFF.NAME,
                    STAFF.USERNAME,
                    STAFF.PASSWORD)
                    .values(
                            name.trim(),
                            username.trim(),
                            password
                    )
                    .execute() == 1;
            // IF need to know new ID, call SQL "SELECT last_insert_rowid()"
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean createStaff(Staff staff, String password) {
        return this.createStaff(staff.getName(), staff.getUsername(), password);
    }

    public boolean updateStaff(int staffId, String name, String username, String password) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(STAFF)
                    .set(STAFF.NAME, name.trim())
                    .set(STAFF.USERNAME, username.trim())
                    .set(STAFF.PASSWORD, password)
                    .where(STAFF.SID.equal(staffId))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeStaff(int staffId) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.delete(STAFF)
                    .where(STAFF.SID.equal(staffId))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Map<Integer, Staff> getStaffList() {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record3<Integer, String, String>> fetch = dsl
                .select(STAFF.SID,
                        STAFF.NAME,
                        STAFF.USERNAME)
                .from(STAFF)
                .fetch();

        return fetch.stream().map(r -> new Staff(
                r.get(STAFF.SID),
                r.get(STAFF.NAME).trim(),
                r.get(STAFF.USERNAME).trim()
        )).collect(Collectors.toMap(Staff::getId, c -> c));
    }

    public Staff getStaffById(int sid) {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record3<Integer, String, String>> fetch = dsl
                .select(STAFF.SID,
                        STAFF.NAME,
                        STAFF.USERNAME)
                .from(STAFF)
                .where(STAFF.SID.equal(sid))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record3<Integer, String, String> fetch1 = fetch.get(0);

        return new Staff(
                fetch1.get(STAFF.SID),
                fetch1.get(STAFF.NAME).trim(),
                fetch1.get(STAFF.USERNAME).trim()
        );
    }

    public Staff getIdFromUserCredentials(String username, String password) throws DataAccessException {
        DSLContext dsl = this.sqlite.getDsl();
        Result<Record3<Integer, String, String>> fetch = dsl
                .select(STAFF.SID,
                        STAFF.NAME,
                        STAFF.USERNAME)
                .from(STAFF)
                .where(STAFF.USERNAME.equal(username))
                .and(STAFF.PASSWORD.equal(password))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record3<Integer, String, String> fetch1 = fetch.get(0);

        return new Staff(
                fetch1.get(STAFF.SID),
                fetch1.get(STAFF.NAME).trim(),
                fetch1.get(STAFF.USERNAME).trim());
    }
}
