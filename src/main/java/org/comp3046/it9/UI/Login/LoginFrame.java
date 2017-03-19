package org.comp3046.it9.UI.Login;

import org.comp3046.it9.Database.CustomerDb;
import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Database.StaffDb;
import org.comp3046.it9.Entity.Customer;
import org.comp3046.it9.Entity.Staff;
import org.comp3046.it9.UI.ForgotPassword.ForgetPassword;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.StaffMenu;
import org.comp3046.it9.UI.Register.Register;
import org.jooq.exception.DataAccessException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFrame {

    private final JFrame frame;
    private JTextField textField_username;
    private JPasswordField passwordField;

    public LoginFrame() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setTitle("XXX Cinema - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginFrame window = new LoginFrame();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        JLabel lblXxxCinema = new JLabel("XXX Cinema");
        lblXxxCinema.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
        lblXxxCinema.setBounds(147, 10, 200, 49);
        frame.getContentPane().add(lblXxxCinema);

        JLabel lblId = new JLabel("Username");
        lblId.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        lblId.setBounds(85, 69, 89, 35);
        frame.getContentPane().add(lblId);

        textField_username = new JTextField();
        textField_username.setFont(new Font("�s�ө���", Font.PLAIN, 18));
        textField_username.setBounds(165, 78, 135, 21);
        frame.getContentPane().add(textField_username);
        textField_username.setColumns(15);

        JLabel lblNewLabel = new JLabel("Password");
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        lblNewLabel.setBounds(85, 114, 89, 35);
        frame.getContentPane().add(lblNewLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(165, 123, 135, 21);
        frame.getContentPane().add(passwordField);

        JButton btnForgotPassword = new JButton("Forget Password");
        btnForgotPassword.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnForgotPassword.setBounds(10, 209, 135, 23);
        btnForgotPassword.addActionListener(new ForgotPasswordAction());
        frame.getContentPane().add(btnForgotPassword);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(198, 209, 87, 23);
        btnRegister.addActionListener(new RegisterAction());
        btnRegister.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnRegister);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnLogin.setBounds(306, 210, 87, 23);
        btnLogin.addActionListener(new LoginAction());
        frame.getContentPane().add(btnLogin);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    private LoginFrame getSelf() {
        return this;
    }

    private class ForgotPasswordAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new ForgetPassword();
        }
    }

    private class RegisterAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new Register();
        }
    }

    @Deprecated
    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String strUsername = textField_username.getText();
            String strPassword = new String(passwordField.getPassword());

            LoginFrame loginFrame = getSelf();

            try (Sqlite sqlite = new Sqlite()) {
                // check if staff
                StaffDb staffDb = new StaffDb(sqlite);
                Staff staff = staffDb.getIdFromUserCredentials(strUsername, strPassword);
                if (staff != null) {
                    loginFrame.setVisible(false);
                    new StaffMenu(loginFrame, staff);
                    return;
                }

                // check if customer
                CustomerDb customerDb = new CustomerDb(sqlite);
                Customer customer = customerDb.getIdFromUserCredentials(strUsername, strPassword);
                if (customer != null) {
                    loginFrame.setVisible(false);
                    new MemberMenu(loginFrame, customer);
                    return;
                }

                JOptionPane.showMessageDialog(null, "Username/Password incorrect, please try again.", "Login", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ignored) {
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\nMissing database file.", "Login", JOptionPane.ERROR_MESSAGE);
            } catch (DataAccessException | SQLException | IOException e) {
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\n" + e.getMessage(), "Login", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
