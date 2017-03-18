package org.comp3046.it9.UI.Menu;

import org.comp3046.it9.Entity.Staff;
import org.comp3046.it9.UI.Login.LoginFrame;

import java.awt.*;

class StaffMenuTest {
    private static final int SID = 1;
    private static final String NAME = "Watson";
    private static final String USERNAME = "waston123";
    // private static final String PASSWORD = "qazplm";

    private static final Staff STAFF = new Staff(SID, NAME, USERNAME);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(false);
            new StaffMenu(loginFrame, STAFF);
        });
    }
}
