package org.comp3046.it9.Database;

import org.comp3046.it9.Entity.Staff;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.util.Map;
import java.util.stream.Collectors;

import static org.comp3046.it9.Database.JooqGenerated.Tables.STAFF;

public class StaffDb {
    private Sqlite sqlite;

    public StaffDb(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean createStaff(String name, String username, String password) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.insertInto(STAFF,
                    STAFF.NAME,
                    STAFF.USERNAME,
                    STAFF.PASSWORD)
                    .values(
                            name,
                            username,
                            password
                    )
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStaff(int staffId, String name, String username, String password) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(STAFF)
                    .set(STAFF.NAME, name)
                    .set(STAFF.USERNAME, username)
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

        Result<Record4<Integer, String, String, String>> fetch = dsl
                .select(STAFF.SID,
                        STAFF.NAME,
                        STAFF.USERNAME,
                        STAFF.PASSWORD)
                .from(STAFF)
                .fetch();

        return fetch.stream().map(r -> new Staff(
                r.get(STAFF.SID),
                r.get(STAFF.NAME),
                r.get(STAFF.USERNAME),
                r.get(STAFF.PASSWORD)
        )).collect(Collectors.toMap(Staff::getId, c -> c));
    }

    public Staff getStaffById(int sid) {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record4<Integer, String, String, String>> fetch = dsl
                .select(STAFF.SID,
                        STAFF.NAME,
                        STAFF.USERNAME,
                        STAFF.PASSWORD)
                .from(STAFF)
                .where(STAFF.SID.equal(sid))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record4<Integer, String, String, String> fetch1 = fetch.get(0);

        return new Staff(
                fetch1.get(STAFF.SID),
                fetch1.get(STAFF.NAME),
                fetch1.get(STAFF.USERNAME),
                fetch1.get(STAFF.PASSWORD)
        );
    }
}
