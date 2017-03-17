package org.comp3046.it9.Database;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerDbTest {
    private Sqlite sqlite;
    private CustomerDb customerDb;

    @Before
    public void setup() throws SQLException {
        this.sqlite = new Sqlite();
        this.customerDb = new CustomerDb(this.sqlite);
    }

    @Test
    public void testCreateCustomer() throws ParseException {
        boolean ret = this.customerDb.createCustomer(
                "testUsername",
                "testPassword",
                "Mr",
                "Charles Poon",
                (new SimpleDateFormat("dd/MM/yyyy")).parse("11/12/1994"),
                91777779,
                "email@example.com"
        );

        Assert.assertEquals(ret, true);
    }

    @Test
    public void testCreateCustomer2() {

    }

    @Test
    public void testForgetPasswordCheckMobileBirthday() {

    }

    @Test
    public void testGetCustomerById() {

    }

    @Test
    public void testGetCustomerList() {

    }

    @Test
    public void testIsUserCredentialsValid() {

    }

    @Test
    public void testRemoveCustomer() {

    }

    @Test
    public void testUpdateCustomer() {

    }

    @After
    public void cleanup() {

    }
}
