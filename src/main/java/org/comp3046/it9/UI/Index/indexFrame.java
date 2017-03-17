package org.comp3046.it9.UI.Index;

import javax.swing.*;

public class indexFrame extends JFrame {
    public indexFrame() {
        setTitle("Movie Ticket System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(new indexPanel());

    }

}
