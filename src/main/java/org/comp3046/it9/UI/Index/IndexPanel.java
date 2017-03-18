package org.comp3046.it9.UI.Index;

import org.comp3046.it9.UI.Login.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexPanel extends JPanel {
    private JPanel controlPanel;

    public IndexPanel() {

        setLayout(new BorderLayout());
        //set for login (top bar)
        JPanel loginPanel = new JPanel();
        addButton("UI/Login", new ChangeFrameAction(), loginPanel);
        add(loginPanel, BorderLayout.LINE_END);


    }

    private void addButton(String label, ActionListener listener, JPanel panel) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    private class ChangeFrameAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new LoginFrame();
        }
    }

}
