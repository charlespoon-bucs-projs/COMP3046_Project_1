package org.comp3046.it9.UI.Login;

import org.comp3046.it9.Database.CustomerDb;
import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Database.StaffDb;
import org.comp3046.it9.Entity.Customer;
import org.comp3046.it9.Entity.Staff;
import org.jooq.exception.DataAccessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class loginPanel extends JPanel {

    private JTextField username;
    private JLabel label_username, label_password;
    private JPasswordField password;
    private JPanel inputPanel, usernamePanel, passwordPanel, buttonPanel;
    private JButton btn_cancel, btn_login;

    public loginPanel() {
        setLayout(new BorderLayout());
        inputPanel = new JPanel(new GridLayout(2, 1));

        // for user name;
        usernamePanel = new JPanel(new FlowLayout());

        label_username = new JLabel("User Name ");
        label_username.setPreferredSize(new Dimension(90, 40));
        label_username.setLabelFor(username);

        username = new JTextField();
        username.setColumns(18);
        //add to panel
        usernamePanel.add(label_username);
        usernamePanel.add(username);


        // for password;
        passwordPanel = new JPanel(new FlowLayout());

        label_password = new JLabel("Password  ");
        label_password.setPreferredSize(new Dimension(90, 40));
        label_password.setLabelFor(password);

        password = new JPasswordField();
        password.setColumns(18);

        passwordPanel.add(label_password);
        passwordPanel.add(password);

        inputPanel.add(usernamePanel);
        inputPanel.add(passwordPanel);

        //set cancel button and login button
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));

        btn_cancel = new JButton("Cancel");
        btn_login = new JButton("UI/Login");
        addButton("Cancel", new CancelAction(), buttonPanel);
        addButton("UI/Login", new LoginAction(), buttonPanel);


        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);


    }


    private void addButton(String label, ActionListener listener, JPanel panel) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }


    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String strUsername = username.getText();
            String strPassword = new String(password.getPassword());

            try (Sqlite sqlite = new Sqlite()) {
                // check if staff
                StaffDb staffDb = new StaffDb(sqlite);
                Staff staffId = staffDb.getIdFromUserCredentials(strUsername, strPassword);
                if (staffId != null) {
                    // TODO: what to do if it's staff? This window should also be closed

                }

                // check if customer
                CustomerDb customerDb = new CustomerDb(sqlite);
                Customer customerId = customerDb.getIdFromUserCredentials(strUsername, strPassword);
                if (customerId != null) {
                    // TODO: what to do if it's customer? This window should also be closed
                }

                JOptionPane.showMessageDialog(null, "The username and password is incorrect.", "Cannot login", JOptionPane.ERROR_MESSAGE);
            } catch (DataAccessException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class CancelAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username.setText("");
            password.setText("");
        }
    }


}
