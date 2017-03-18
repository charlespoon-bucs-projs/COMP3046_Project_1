package org.comp3046.it9.UI.Register;

import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Database.CustomerDb;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Register {

    private MaskFormatter mf1;
    private UtilDateModel model;
    private JFrame frame;
    private JTextField textField_FullName, textField_Username, textField_Mobile_Number, textField_Email,
            textField_Confirm_Email, textField_Password, textField_Confirm_Password;
    private JComboBox<String> comboBox_Salutation;
    private JDatePickerImpl datePicker;

    /**
     * Create the application.
     */
    public Register() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 447);
        frame.setTitle("XXX Cinema - Register");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        try {
            mf1 = new MaskFormatter("00000000");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        initialize();
    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        JLabel lblFullName = new JLabel("*Full Name");
        lblFullName.setBounds(46, 10, 138, 35);
        lblFullName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblFullName);

        textField_FullName = new JTextField();
        textField_FullName.setBounds(182, 19, 138, 21);
        textField_FullName.setDocument(new JTextFieldLimit(15));
        frame.getContentPane().add(textField_FullName);
        textField_FullName.setColumns(10);

        JLabel lblSalutation = new JLabel("*Salutation");
        lblSalutation.setBounds(46, 45, 114, 35);
        lblSalutation.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblSalutation);

        comboBox_Salutation = new JComboBox<>();
        comboBox_Salutation.setBounds(250, 54, 70, 21);
        comboBox_Salutation.addItem("Mr.");
        comboBox_Salutation.addItem("Ms.");
        comboBox_Salutation.addItem("Mrs.");
        frame.getContentPane().add(comboBox_Salutation);

        JLabel lblUsername = new JLabel("*Username");
        lblUsername.setBounds(46, 80, 114, 35);
        lblUsername.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblUsername);

        textField_Username = new JTextField();
        textField_Username.setBounds(182, 89, 138, 21);
        textField_Username.setDocument(new JTextFieldLimit(15));
        frame.getContentPane().add(textField_Username);
        textField_Username.setColumns(15);

        JLabel lblBirthday = new JLabel("*Birthday");
        lblBirthday.setBounds(46, 115, 114, 35);
        lblBirthday.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblBirthday);

        model = new UtilDateModel();

        // model.setDate(20,04,2014);
        // Need this...
        model.setSelected(true);
        model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.setBounds(182, 119, 138, 28);
        datePicker.addActionListener(new CheckDateAction());
        frame.getContentPane().add(datePicker);

        JLabel lblMobileNumber = new JLabel("*Mobile Number");
        lblMobileNumber.setBounds(46, 150, 114, 35);
        lblMobileNumber.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblMobileNumber);

        textField_Mobile_Number = new JTextField();
        textField_Mobile_Number.setBounds(182, 159, 138, 21);
        frame.getContentPane().add(textField_Mobile_Number);
        textField_Mobile_Number.setDocument(new JTextFieldLimit(8));
        textField_Mobile_Number.setColumns(8);

        JLabel lblEmail = new JLabel("*Email");
        lblEmail.setBounds(46, 185, 114, 35);
        lblEmail.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblEmail);

        textField_Email = new JTextField();
        textField_Email.setBounds(182, 194, 138, 21);
        frame.getContentPane().add(textField_Email);
        textField_Email.setDocument(new JTextFieldLimit(20));
        textField_Email.setColumns(20);

        JLabel lblConfirm_Email = new JLabel("*Confirm");
        lblConfirm_Email.setBounds(46, 220, 114, 35);
        lblConfirm_Email.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblConfirm_Email);

        textField_Confirm_Email = new JTextField();
        textField_Confirm_Email.setBounds(182, 229, 138, 21);
        frame.getContentPane().add(textField_Confirm_Email);
        textField_Confirm_Email.setDocument(new JTextFieldLimit(20));
        textField_Confirm_Email.setColumns(20);

        JLabel lblPassword = new JLabel("*Password");
        lblPassword.setBounds(46, 255, 114, 35);
        lblPassword.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblPassword);

        textField_Password = new JTextField();
        textField_Password.setBounds(182, 264, 138, 21);
        frame.getContentPane().add(textField_Password);
        textField_Password.setDocument(new JTextFieldLimit(20));
        textField_Password.setColumns(20);

        JLabel lblConfirm_Password = new JLabel("*Confirm");
        lblConfirm_Password.setBounds(46, 290, 114, 35);
        lblConfirm_Password.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblConfirm_Password);

        textField_Confirm_Password = new JTextField();
        textField_Confirm_Password.setBounds(182, 299, 138, 21);
        frame.getContentPane().add(textField_Confirm_Password);
        textField_Confirm_Password.setDocument(new JTextFieldLimit(20));
        textField_Confirm_Password.setColumns(20);

        JLabel lblrequiredField = new JLabel("*Required Field");
        lblrequiredField.setBounds(46, 365, 138, 21);
        lblrequiredField.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblrequiredField);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(186, 366, 87, 23);
        btnSubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnSubmit);
        btnSubmit.addActionListener(new SubmitAction());

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(283, 366, 87, 23);
        btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnReset);
        btnReset.addActionListener(new ResetAction());

    }

    private class CheckDateAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (model.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
                if (model.getMonth() == Calendar.getInstance().get(Calendar.MONTH)) {
                    if (model.getDay() >= Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1) {
                        model.setDate(Calendar.getInstance().get(Calendar.YEAR),
                                Calendar.getInstance().get(Calendar.MONTH),
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);

                    }
                } else {
                    if (model.getMonth() > Calendar.getInstance().get(Calendar.MONTH)) {
                        model.setDate(Calendar.getInstance().get(Calendar.YEAR),
                                Calendar.getInstance().get(Calendar.MONTH),
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
                    }

                }

            } else {
                if (model.getYear() >= Calendar.getInstance().get(Calendar.YEAR)) {
                    model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
                }

            }
        }
    }

    private class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // register submit button

            String uname = textField_Username.getText();
            String pwd = textField_Password.getText();
            String salutn = (String) comboBox_Salutation.getSelectedItem();
            String name = textField_FullName.getText();
            Date bday = (Date) datePicker.getModel().getValue();
            int mob = Integer.parseInt(textField_Mobile_Number.getText(), 10);
            String email = textField_Email.getText();

            // check
            if (!pwd.equals(textField_Confirm_Password.getText())) {
                JOptionPane.showMessageDialog(null, "The passwords do not match.", "Passwords not match", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!email.equals(textField_Confirm_Email.getText())) {
                JOptionPane.showMessageDialog(null, "The e-mails do not match.", "E-mails not match", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // work
            try (Sqlite sqlite = new Sqlite()) {
                CustomerDb customerDb = new CustomerDb(sqlite);

                boolean created = customerDb.createCustomer(
                        uname,
                        pwd,
                        salutn,
                        name,
                        bday,
                        mob,
                        email);

                if (created) {
                    JOptionPane.showMessageDialog(null, "New account successfully created.", "Create account", JOptionPane.INFORMATION_MESSAGE);

                    // close window
                    frame.dispatchEvent(
                            new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)
                    );
                } else {
                    throw new SQLException("returns false, see log");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();

                String message = "Error creating account: \r\n";
                message += e.getMessage();
                JOptionPane.showMessageDialog(null, message, "Error creating account", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            textField_FullName.setText("");
            comboBox_Salutation.setSelectedIndex(0);

            textField_Username.setText("");
            model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
            textField_Mobile_Number.setText("");
            textField_Email.setText("");
            textField_Confirm_Email.setText("");
            textField_Password.setText("");
            textField_Confirm_Password.setText("");
        }
    }
}
