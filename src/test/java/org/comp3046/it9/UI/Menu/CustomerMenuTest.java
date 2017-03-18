package org.comp3046.it9.UI.Menu;

import org.comp3046.it9.Entity.Customer;
import org.comp3046.it9.UI.Login.LoginFrame;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class CustomerMenuTest {
    private static final int UID = 1;
    private static final String NAME = "Sherlock";
    private static final String SALUTATION = "Mr";
    private static final String USERNAME = "sherlock123";
    private static final int MOBILE = 23800000;
    private static final String EMAIL = "pizza@hut.com";
    // private static final String PASSWORD = "qwerty";
    private static Date BIRTHDAY;

    static {
        try {
            BIRTHDAY = (new SimpleDateFormat("dd/MM/yyyy")).parse("01/01/1990");
        } catch (ParseException ignored) {
        }
    }

    private static final Customer CUSTOMER = new Customer(UID, NAME, SALUTATION, USERNAME,MOBILE, EMAIL, BIRTHDAY);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(false);
            new MemberMenu(loginFrame, CUSTOMER);
        });
    }
}
