package org.comp3046.it9.UI.Login;

import javax.swing.*;

public class loginFrame extends JFrame {

    public loginFrame() {
        setTitle("Movie Ticket System - UI.Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(new loginPanel());

    }
}
