package org.comp3046.it9.UI.ForgotPassword;

import org.comp3046.it9.Database.CustomerDb;
import org.comp3046.it9.Database.Sqlite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class ForgetPassword {

    private JFrame frame;
    private JTextField textField_email;

    /*
    /**
     * Launch the application.
     *
     * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { forgot_password window = new
	 * forgot_password(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

    /**
     * Create the application.
     */
    public ForgetPassword() {
        frame = new JFrame();
        frame.setBounds(100, 100, 402, 202);
        frame.setTitle("XXX Cinema - Forget Password");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(51, 30, 46, 15);
        frame.getContentPane().add(lblEmail);
        lblEmail.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));

        textField_email = new JTextField();
        textField_email.setBounds(124, 27, 200, 18);
        textField_email.setFont(new Font("�s�ө���", Font.PLAIN, 18));
        frame.getContentPane().add(textField_email);
        textField_email.setColumns(30);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(51, 96, 87, 23);
        btnSubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnSubmit);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnReset.setBounds(202, 96, 87, 23);
        btnReset.addActionListener(new ResetAction());
        frame.getContentPane().add(btnReset);
    }

    private class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // TODO: forget password submit

            String email = textField_email.getText();

            try (Sqlite sqlite = new Sqlite()) {
                CustomerDb customerDb = new CustomerDb(sqlite);

                String newPassword;
                try {
                    newPassword = customerDb.forgetPasswordCheckEmail(email);
                } catch (UnsupportedOperationException e) {
                    JOptionPane.showMessageDialog(null, "User not found.", "Forget password", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String msg = "";
                msg += "Please remember the following message.\r\n";
                msg += "Your new password for your account is:\r\n";
                msg += "\r\n";
                msg += newPassword + "\r\n";
                msg += "\r\n";
                msg += "This message will not be re-displayed again.";

                JOptionPane.showMessageDialog(null, msg, "Forget password", JOptionPane.WARNING_MESSAGE);

                // close window
                frame.dispatchEvent(
                        new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)
                );
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            textField_email.setText("");
        }
    }

}
