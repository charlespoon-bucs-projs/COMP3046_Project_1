package org.comp3046.it9.Database;

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

import org.comp3046.it9.Entity.Customer;

import static org.comp3046.it9.Database.JooqGenerated.tables.Customer.CUSTOMER;

public class UserDb {
    private Sqlite sqlite;

    public UserDb(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean isUserCredentialsValid(String username, String password) {
        DSLContext dsl = this.sqlite.getDsl();
        Result<Record1<Integer>> fetch = dsl.selectOne()
                .from(CUSTOMER)
                .where(CUSTOMER.USERNAME.equal(username))
                .and(CUSTOMER.PASSWORD.equal(password))
                .fetch();
        return fetch.size() == 1;
    }

    public boolean createCustomer(String username, String password,
                                  String salutation, String name, Date birthday, int mobile, String email) {
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
                    .values(username,
                            password,
                            salutation,
                            name,
                            Utils.Convert.dateToString(birthday),
                            mobile,
                            email)
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(int uid, String username, String password,
                                  String salutation, String name, Date birthday, int mobile, String email) {
        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(CUSTOMER)
                    .set(CUSTOMER.USERNAME, username)
                    .set(CUSTOMER.PASSWORD, password)
                    .set(CUSTOMER.SALUTATION, salutation)
                    .set(CUSTOMER.NAME, name)
                    .set(CUSTOMER.BIRTHDAY, Utils.Convert.dateToString(birthday))
                    .set(CUSTOMER.MOBILE, mobile)
                    .set(CUSTOMER.EMAIL, email)
                    .where(CUSTOMER.UID.equal(uid))
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String forgetPasswordCheckMobileBirthday(String username, int mobile, Date birthday) throws UnsupportedOperationException {
        DSLContext dsl = this.sqlite.getDsl();

        Result<Record1<Integer>> fetchUid = dsl.select(CUSTOMER.UID)
                .from(CUSTOMER)
                .where(CUSTOMER.USERNAME.equal(username))
                .and(CUSTOMER.MOBILE.equal(mobile))
                .and(CUSTOMER.BIRTHDAY.equal(Utils.Convert.dateToString(birthday)))
                .fetch();

        boolean canChangePassword = fetchUid.size() == 1;

        if (!canChangePassword)
            throw new UnsupportedOperationException();

        int chaingingUid = fetchUid.get(0).value1();

        String newPassword = new BigInteger(130, new SecureRandom()).toString(32);

        int affect = dsl.update(CUSTOMER)
                .set(CUSTOMER.PASSWORD, newPassword)
                .where(CUSTOMER.UID.equal(chaingingUid))
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
                r.get(CUSTOMER.NAME),
                r.get(CUSTOMER.SALUTATION),
                r.get(CUSTOMER.USERNAME),
                r.get(CUSTOMER.MOBILE),
                r.get(CUSTOMER.EMAIL),
                Utils.Convert.stringToDate(r.get(CUSTOMER.BIRTHDAY), null)
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
                fetch1.get(CUSTOMER.NAME),
                fetch1.get(CUSTOMER.SALUTATION),
                fetch1.get(CUSTOMER.USERNAME),
                fetch1.get(CUSTOMER.MOBILE),
                fetch1.get(CUSTOMER.EMAIL),
                Utils.Convert.stringToDate(fetch1.get(CUSTOMER.BIRTHDAY), null)
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
