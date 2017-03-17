package org.comp3046.it9.UI.Menu;

import org.comp3046.it9.Entity.Staff;
import org.comp3046.it9.UI.Index.index;

import java.awt.*;

public class StaffMenuTest {
    private static final int SID = 1;
    private static final String NAME = "Watson";
    private static final String USERNAME = "waston123";
    private static final String PASSWORD = "qazplm";

    private static final Staff STAFF = new Staff(SID, NAME, USERNAME);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                staff_menu staff_menu = new staff_menu(STAFF);
//                staff_menu.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
