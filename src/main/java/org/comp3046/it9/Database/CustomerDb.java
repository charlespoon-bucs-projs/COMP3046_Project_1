package org.comp3046.it9.Database;

import org.comp3046.it9.Entity.Customer;
import org.comp3046.it9.Utils.Convert;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import static org.comp3046.it9.Database.JooqGenerated.tables.Customer.CUSTOMER;

public class CustomerDb {
    private final Sqlite sqlite;

    public CustomerDb(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public Customer getIdFromUserCredentials(String username, String password) throws DataAccessException {
        DSLContext dsl = this.sqlite.getDsl();
        Result<Record7<Integer, String, String, String, Integer, String, String>> fetch = dsl
                .select(CUSTOMER.UID,
                        CUSTOMER.NAME,
                        CUSTOMER.SALUTATION,
                        CUSTOMER.USERNAME,
                        CUSTOMER.MOBILE,
                        CUSTOMER.EMAIL,
                        CUSTOMER.BIRTHDAY)
                .from(CUSTOMER)
                .where(CUSTOMER.USERNAME.equal(username))
                .and(CUSTOMER.PASSWORD.equal(password))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record7<Integer, String, String, String, Integer, String, String> fetch1 = fetch.get(0);
        return new Customer(
                fetch1.get(CUSTOMER.UID),
                fetch1.get(CUSTOMER.NAME).trim(),
                fetch1.get(CUSTOMER.SALUTATION).trim(),
                fetch1.get(CUSTOMER.USERNAME).trim(),
                fetch1.get(CUSTOMER.MOBILE),
                fetch1.get(CUSTOMER.EMAIL).trim(),
                Convert.stringToDate(fetch1.get(CUSTOMER.BIRTHDAY).trim(), null));
    }

    public boolean createCustomer(String username,
                                  String password,
                                  String salutation,
                                  String name,
                                  Date birthday,
                                  int mobile,
                                  String email) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.insertInto(CUSTOMER,
                    CUSTOMER.USERNAME,
                    CUSTOMER.PASSWORD,
                    CUSTOMER.SALUTATION,
                    CUSTOMER.NAME,
                    CUSTOMER.BIRTHDAY,
                    CUSTOMER.MOBILE,
                    CUSTOMER.EMAIL)
                    .values(username.trim(),
                            password,
                            salutation.trim(),
                            name.trim(),
                            Convert.dateToString(birthday).trim(),
                            mobile,
                            email.trim())
                    .returning(CUSTOMER.UID)
                    .execute() == 1;
            // IF need to know new ID, call SQL "SELECT last_insert_rowid()"
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean createCustomer(Customer c) {
        return this.createCustomer(c.getUsername(), c.getEmail(), c.getSalutation(), c.getName(), c.getBirthday(), c.getMobile(), c.getEmail());
    }

    public boolean updateCustomer(int uid, String username, String password,
                                  String salutation, String name, Date birthday, int mobile, String email) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(CUSTOMER)
                    .set(CUSTOMER.USERNAME, username.trim())
                    .set(CUSTOMER.PASSWORD, password)
                    .set(CUSTOMER.SALUTATION, salutation.trim())
                    .set(CUSTOMER.NAME, name.trim())
                    .set(CUSTOMER.BIRTHDAY, Convert.dateToString(birthday).trim())
                    .set(CUSTOMER.MOBILE, mobile)
                    .set(CUSTOMER.EMAIL, email.trim())
                    .where(CUSTOMER.UID.equal(uid))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCustomer2(int uid, String username,
                                  String salutation, String name, Date birthday, int mobile, String email) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(CUSTOMER)
                    .set(CUSTOMER.USERNAME, username.trim())
                    .set(CUSTOMER.SALUTATION, salutation.trim())
                    .set(CUSTOMER.NAME, name.trim())
                    .set(CUSTOMER.BIRTHDAY, Convert.dateToString(birthday).trim())
                    .set(CUSTOMER.MOBILE, mobile)
                    .set(CUSTOMER.EMAIL, email.trim())
                    .where(CUSTOMER.UID.equal(uid))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    // @Deprecated
    public String forgetPasswordCheckEmail(String email) throws UnsupportedOperationException {
        // DON'T DO THIS ON PRODUCTION!!!

        DSLContext dsl = this.sqlite.getDsl();

        Result<Record1<Integer>> fetchUid = dsl.select(CUSTOMER.UID)
                .from(CUSTOMER)
                .where(CUSTOMER.EMAIL.equal(email.trim()))
                .fetch();

        boolean canChangePassword = fetchUid.size() == 1;

        if (!canChangePassword)
            throw new UnsupportedOperationException();

        int changingUid = fetchUid.get(0).value1();

        String newPassword = new BigInteger(40, new SecureRandom()).toString(32).trim().substring(0, 9);

        int affect = dsl.update(CUSTOMER)
                .set(CUSTOMER.PASSWORD, newPassword)
                .where(CUSTOMER.UID.equal(changingUid))
//                .and(CUSTOMER.EMAIL.equal(email))
                .execute();

        if (affect != 1)
            return null;
        return newPassword;
    }

    public String forgetPasswordCheckMobileBirthday(String username, int mobile, Date birthday) throws UnsupportedOperationException {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record1<Integer>> fetchUid = dsl.select(CUSTOMER.UID)
                .from(CUSTOMER)
                .where(CUSTOMER.USERNAME.equal(username))
                .and(CUSTOMER.MOBILE.equal(mobile))
                .and(CUSTOMER.BIRTHDAY.equal(Convert.dateToString(birthday)))
                .fetch();

        boolean canChangePassword = fetchUid.size() == 1;

        if (!canChangePassword)
            throw new UnsupportedOperationException();

        int changingUid = fetchUid.get(0).value1();

        String newPassword = new BigInteger(40, new SecureRandom()).toString(32).trim().substring(0, 9);

        int affect = dsl.update(CUSTOMER)
                .set(CUSTOMER.PASSWORD, newPassword)
                .where(CUSTOMER.UID.equal(changingUid))
//                .and(CUSTOMER.USERNAME.equal(username))
//                .and(CUSTOMER.MOBILE.equal(mobile))
//                .and(CUSTOMER.BIRTHDAY.equal(strBirthday))
                .execute();

        if (affect != 1)
            return null;
        return newPassword;
    }

    // == iteration ==

    public Map<Integer, Customer> getCustomersList() {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record7<Integer, String, String, String, Integer, String, String>> fetch = dsl
                .select(CUSTOMER.UID,
                        CUSTOMER.NAME,
                        CUSTOMER.SALUTATION,
                        CUSTOMER.USERNAME,
                        CUSTOMER.MOBILE,
                        CUSTOMER.EMAIL,
                        CUSTOMER.BIRTHDAY)
                .from(CUSTOMER)
                .fetch();

        return fetch.stream().map(r -> new Customer(
                r.get(CUSTOMER.UID),
                r.get(CUSTOMER.NAME).trim(),
                r.get(CUSTOMER.SALUTATION).trim(),
                r.get(CUSTOMER.USERNAME).trim(),
                r.get(CUSTOMER.MOBILE),
                r.get(CUSTOMER.EMAIL).trim(),
                Convert.stringToDate(r.get(CUSTOMER.BIRTHDAY).trim(), null)
        )).collect(Collectors.toMap(Customer::getUid, c -> c));
    }

    public Customer getCustomerByUid(int uid) throws DataAccessException {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record7<Integer, String, String, String, Integer, String, String>> fetch = dsl
                .select(CUSTOMER.UID,
                        CUSTOMER.NAME,
                        CUSTOMER.SALUTATION,
                        CUSTOMER.USERNAME,
                        CUSTOMER.MOBILE,
                        CUSTOMER.EMAIL,
                        CUSTOMER.BIRTHDAY)
                .from(CUSTOMER)
                .where(CUSTOMER.UID.equal(uid))
                .limit(2)
                .fetch();

        if (fetch.size() < 1)
            return null;
        else if (fetch.size() > 1)
            throw new DataAccessException("size > 1");

        Record7<Integer, String, String, String, Integer, String, String> fetch1 = fetch.get(0);
        return new Customer(
                fetch1.get(CUSTOMER.UID),
                fetch1.get(CUSTOMER.NAME).trim(),
                fetch1.get(CUSTOMER.SALUTATION).trim(),
                fetch1.get(CUSTOMER.USERNAME).trim(),
                fetch1.get(CUSTOMER.MOBILE),
                fetch1.get(CUSTOMER.EMAIL).trim(),
                Convert.stringToDate(fetch1.get(CUSTOMER.BIRTHDAY).trim(), null)
        );
    }

    public boolean removeCustomer(int uid) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.delete(CUSTOMER)
                    .where(CUSTOMER.UID.equal(uid))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
