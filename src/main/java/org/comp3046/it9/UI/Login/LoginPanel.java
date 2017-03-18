package org.comp3046.it9.UI.Login;

import javax.swing.*;
import java.awt.*;

class LoginPanel extends JPanel {
    private JPanel controlPanel;

    public LoginPanel() {

        setLayout(new BorderLayout());
        //set for login (top bar)
        JPanel loginPanel = new JPanel();
        add(loginPanel, BorderLayout.LINE_END);
    }
}
