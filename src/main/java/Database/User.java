package Database;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Database.JooqGenerated.tables.Customer.CUSTOMER;

public class User {
    private Sqlite sqlite;

    public User(Sqlite sqlite) {
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
        String strBirthday = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")).format(birthday);

        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.insertInto(CUSTOMER,
                    CUSTOMER.USERNAME, CUSTOMER.PASSWORD,
                    CUSTOMER.SALUTATION, CUSTOMER.NAME, CUSTOMER.BIRTHDAY, CUSTOMER.MOBILE, CUSTOMER.EMAIL)
                    .values(username, password,
                            salutation, name, strBirthday, mobile, email)
                    .execute() == 1;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(int uid, String username, String password,
                                  String salutation, String name, Date birthday, int mobile, String email) {
        String strBirthday = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")).format(birthday);

        DSLContext dsl = this.sqlite.getDsl();

        try {
            return dsl.update(CUSTOMER)
                    .set(CUSTOMER.USERNAME, username)
                    .set(CUSTOMER.PASSWORD, password)
                    .set(CUSTOMER.SALUTATION, salutation)
                    .set(CUSTOMER.NAME, name)
                    .set(CUSTOMER.BIRTHDAY, strBirthday)
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
        String strBirthday = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")).format(birthday);

        DSLContext dsl = this.sqlite.getDsl();

        Result<Record1<Integer>> fetchUid = dsl.select(CUSTOMER.UID)
                .from(CUSTOMER)
                .where(CUSTOMER.USERNAME.equal(username))
                .and(CUSTOMER.MOBILE.equal(mobile))
                .and(CUSTOMER.BIRTHDAY.equal(strBirthday))
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
}
