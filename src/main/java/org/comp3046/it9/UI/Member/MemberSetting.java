package org.comp3046.it9.UI.Member;

import org.comp3046.it9.Database.CustomerDb;
import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Entity.Customer;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.StaffMenu;
import org.comp3046.it9.UI.Menu.TopBar;
import org.comp3046.it9.UI.Register.JTextFieldLimit;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MemberSetting {
    private final MemberMenu memberMenu;
    private final StaffMenu staffMenu;
    private final Customer editingCustomer;

    private JRadioButton rdbtnStaff;
    private TopBar tp;
    MaskFormatter mf1;
    private UtilDateModel model;
    private JDatePickerImpl datePicker;
    private JFrame frame;
    private JLabel lblLoginer, lblFullName, lblSalutation, lblUsername, lblBirthday, lblMobileNumber, lblEmail,
            lblPassword;
    private JTextField textField_FullName, textField_Username, textField_Mobile_Number, textField_Email,
            textField_Password;
    private JComboBox<String> comboBox_Salutation;
    private JButton btnSubmit, btnReset, btnBack;
    private JPanel topbar;
    private JSeparator separator;

    /**
     * Launch the application.
     */

    // must not be adding customer by customer itself
    public MemberSetting(MemberMenu memberMenu) {
        this.memberMenu = memberMenu;
        this.staffMenu = null;
        this.editingCustomer = memberMenu.getCustomer();

        initialize();
        tp.clock();
    }

    public MemberSetting(StaffMenu staffMenu) { // for add new member
        this.memberMenu = null;
        this.staffMenu = staffMenu;
        this.editingCustomer = null;

        initialize();
        tp.clock();
    }

    public MemberSetting(StaffMenu staffMenu, Customer editingCustomer) { // for
        // add
        // new
        // member
        this.memberMenu = null;
        this.staffMenu = staffMenu;
        this.editingCustomer = editingCustomer;

        initialize();
        tp.clock();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        if (this.editingCustomer == null)
            frame.setTitle("XXX Cinema - Add New Member");
        else
            frame.setTitle("XXX Cinema - Modify Member");
        frame.setBounds(100, 100, 524, 447);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp = new TopBar();

        frame.setVisible(true);
        frame.getContentPane().setLayout(null);

        topbar = new JPanel();
        topbar.setLayout(null);
        topbar.setBounds(0, 0, 504, 40);

        lblLoginer = new JLabel("Login as ");
        int id = 0;
        String name = "";
        if (staffMenu != null){
            id = staffMenu.getStaff().getId();
            name = staffMenu.getStaff().getName();
            lblLoginer.setText(lblLoginer.getText() + name + "-Staff");
        }
        else {
            id = memberMenu.getCustomer().getUid();
            name = memberMenu.getCustomer().getName();
            lblLoginer.setText(lblLoginer.getText() + name + "-Member");
        }

        lblLoginer.setBounds(304, 10, 200, 15);

        lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        topbar.add(lblLoginer);

        separator = new JSeparator();
        separator.setBounds(10, 35, 534, 2);
        topbar.add(separator);


        frame.getContentPane().add(tp.topbarLayout(topbar, id + "", name));

        btnBack = new JButton("Back");
        btnBack.setBounds(10, 52, 87, 23);
        btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new BackAction());

        lblFullName = new JLabel("*Full Name");
        lblFullName.setBounds(66, 72, 138, 35);
        lblFullName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblFullName);

        textField_FullName = new JTextField();
        textField_FullName.setBounds(242, 81, 138, 21);
        textField_FullName.setDocument(new JTextFieldLimit(15));
        frame.getContentPane().add(textField_FullName);
        textField_FullName.setColumns(10);

        lblSalutation = new JLabel("*Salutation");
        lblSalutation.setBounds(66, 107, 138, 35);
        lblSalutation.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblSalutation);

        comboBox_Salutation = new JComboBox<>();
        comboBox_Salutation.setBounds(310, 112, 70, 21);
        comboBox_Salutation.addItem("Mr.");
        comboBox_Salutation.addItem("Ms.");
        comboBox_Salutation.addItem("Mrs.");
        frame.getContentPane().add(comboBox_Salutation);

        lblUsername = new JLabel("*Username");
        lblUsername.setBounds(66, 142, 114, 35);
        lblUsername.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblUsername);

        textField_Username = new JTextField();
        textField_Username.setBounds(242, 147, 138, 21);
        textField_Username.setDocument(new JTextFieldLimit(15));
        frame.getContentPane().add(textField_Username);
        textField_Username.setColumns(15);

        lblPassword = new JLabel("*Password");
        lblPassword.setBounds(66, 177, 114, 35);
        lblPassword.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblPassword);

        textField_Password = new JTextField();
        textField_Password.setBounds(242, 182, 138, 21);
        frame.getContentPane().add(textField_Password);
        textField_Password.setDocument(new JTextFieldLimit(20));
        textField_Password.setColumns(20);

        lblMobileNumber = new JLabel("*Mobile Number");
        lblMobileNumber.setBounds(66, 212, 114, 35);
        lblMobileNumber.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblMobileNumber);

        textField_Mobile_Number = new JTextField();
        textField_Mobile_Number.setBounds(242, 217, 138, 21);
        frame.getContentPane().add(textField_Mobile_Number);
        textField_Mobile_Number.setDocument(new JTextFieldLimit(8));
        textField_Mobile_Number.setColumns(8);

        lblEmail = new JLabel("*Email");
        lblEmail.setBounds(66, 247, 114, 35);
        lblEmail.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblEmail);

        textField_Email = new JTextField();
        textField_Email.setBounds(242, 252, 138, 21);
        frame.getContentPane().add(textField_Email);
        textField_Email.setDocument(new JTextFieldLimit(20));
        textField_Email.setColumns(20);

        lblBirthday = new

                JLabel("*Birthday");
        lblBirthday.setBounds(66, 282, 114, 35);
        lblBirthday.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblBirthday);

        model = new UtilDateModel();

        model.setSelected(true);
        model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.setBounds(242, 287, 138, 28);
        datePicker.addActionListener(new CheckDateAction());
        frame.getContentPane().add(datePicker);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(276, 366, 87, 23);
        btnSubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnSubmit); //
        btnSubmit.addActionListener(new SubmitAction());

        btnReset = new JButton("Reset");
        btnReset.setBounds(397, 366, 87, 23);
        btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnReset); //

        JButton button = new JButton("Delete Member");
        button.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        button.setBounds(27, 367, 122, 23);
        frame.getContentPane().add(button);

        if (this.editingCustomer != null && staffMenu != null) { // staff modify mem

            textField_FullName.setText(editingCustomer.getName());
            textField_FullName.setEditable(true);
            lblFullName.setEnabled(true);

            comboBox_Salutation.setSelectedIndex(0);
            lblSalutation.setEnabled(false);

            textField_Username.setText(editingCustomer.getUsername());
            textField_Username.setEditable(true);
            lblUsername.setEnabled(true);

            textField_Password.setText("");

            textField_Mobile_Number.setText(Integer.toString(editingCustomer.getMobile()));
            textField_Mobile_Number.setEditable(false);
            lblMobileNumber.setEnabled(false);

            textField_Email.setText(editingCustomer.getEmail());
            textField_Email.setEditable(false);
            lblEmail.setEnabled(false);

            lblBirthday.setEnabled(false);

            datePicker.getComponent(1).setEnabled(true);

        } else if (staffMenu != null) {

        } else {
            rdbtnStaff.setVisible(false);
            textField_FullName.setText(memberMenu.getCustomer().getName());
            textField_FullName.setEditable(true);
            lblFullName.setEnabled(true);

            comboBox_Salutation.setSelectedIndex(0);
            lblSalutation.setEnabled(false);

            textField_Username.setText(memberMenu.getCustomer().getUsername());
            textField_Username.setEditable(false);
            lblUsername.setEnabled(true);

            textField_Password.setText("");

            textField_Mobile_Number.setText(Integer.toString(memberMenu.getCustomer().getMobile()));
            textField_Mobile_Number.setEditable(false);
            lblMobileNumber.setEnabled(false);

            textField_Email.setText(memberMenu.getCustomer().getEmail());
            textField_Email.setEditable(false);
            lblEmail.setEnabled(false);
            lblBirthday.setText("*Birthday: ");
            lblBirthday.setEnabled(false);

            datePicker.getComponent(1).setEnabled(false);
        }

    }

    private class BackAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            frame.setVisible(false);
            if (staffMenu != null) {
                staffMenu.setVisible(true);
            } else { // memberMenu != null
                memberMenu.setVisible(true);
            }
            frame.dispose();
        }
    }

    private class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // reset all fields
            textField_FullName.setText("");
            comboBox_Salutation.setSelectedIndex(0);
            textField_Username.setText("");
            textField_Password.setText("");
            textField_Mobile_Number.setText("");
            textField_Email.setText("");
            ((UtilDateModel)datePicker.getModel()).setValue(new Date()); // reset date of birth to today
        }
    }

    private class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String fullName = textField_FullName.getText();
            String salutation = (String) comboBox_Salutation.getSelectedItem();
            String username = textField_Username.getText();
            String password = textField_Password.getText();
            int mobile = Integer.parseInt(textField_Mobile_Number.getText(), 10);
            Date dateOfBorn = (Date) datePicker.getModel().getValue();
            String email = textField_Email.getText();

            boolean isAdd = editingCustomer == null;

            try (Sqlite sqlite = new Sqlite()) {
                CustomerDb customerDb = new CustomerDb(sqlite);

                boolean dbOk = false;

                if (isAdd) {
                    dbOk = customerDb.createCustomer(
                            username,
                            password,
                            salutation,
                            fullName,
                            dateOfBorn,
                            mobile,
                            email
                    );
                } else {
                    if(password.equals("")){
                        System.out.println("password null");
                        dbOk = customerDb.updateCustomer2(
                                editingCustomer.getUid(),
                                username,
                                salutation,
                                fullName,
                                dateOfBorn,
                                mobile,
                                email
                    );
                    }else{
                        System.out.println("Have PW");
                        dbOk = customerDb.updateCustomer(
                                editingCustomer.getUid(),
                                username,
                                password,
                                salutation,
                                fullName,
                                dateOfBorn,
                                mobile,
                                email
                        );
                    }
                }
                if (dbOk){
                    JOptionPane.showMessageDialog(
                            null, "Member information updated.",
                            "Member Settings", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (!dbOk) {
                    JOptionPane.showMessageDialog(
                            null, "Failed to modify data on database, unknown reason.",
                            "Member Settings", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(
                        null,
                        "Successful to " + (isAdd ? "add" : ("modify user " + editingCustomer.getUid())) + " data.",
                        "Member Settings", JOptionPane.INFORMATION_MESSAGE);

                // back
                btnBack.doClick();
            } catch (FileNotFoundException ignored) {
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\nMissing database file.", "Member Settings", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Error: \r\n\r\n" + e.getMessage(),
                        "Member Settings",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
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
}
