package org.comp3046.it9.UI.TransactionRecord;

import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Database.TransactionsDb;
import org.comp3046.it9.Entity.Customer;
import org.comp3046.it9.Entity.Staff;
import org.comp3046.it9.UI.Menu.member_menu;
import org.comp3046.it9.UI.Menu.staff_menu;
import org.comp3046.it9.UI.Menu.topbar;
import org.comp3046.it9.UI.Register.JTextFieldLimit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class transactionRecord {
    private Customer customer = null;
    private Staff staff = null;

    topbar tb;
    private JFrame frame;
    private JPanel topbar;
    private JLabel lblLoginer, lblMobile;
    private JButton btnBack, btnSearch;
    private JTextField textField_Mobile;
    private JSeparator separator;
    private JTable MovieTable;
    private JTable table;

    public transactionRecord(Staff staff) {
        this.staff = staff;
        _ctor();
    }

    public transactionRecord(Customer customer) {
        this.customer = customer;
        _ctor();
    }

    private void _ctor() {
        tb = new topbar();
        initialize();
        tb.clock();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("XXX Cinema - Transaction Record");
        frame.setVisible(true);

        frame.getContentPane().setLayout(null);

        frame.setBounds(100, 100, 524, 455);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topbar = new JPanel();
        topbar.setLayout(null);
        topbar.setBounds(0, 0, 504, 40);

        lblLoginer = new JLabel("Login as ");
        if (staff != null)
            lblLoginer.setText(lblLoginer.getText() + tb.FullName + "-Staff");
        else
            lblLoginer.setText(lblLoginer.getText() + tb.FullName + "-Member");
        lblLoginer.setBounds(304, 10, 200, 15);

        lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        topbar.add(lblLoginer);

        separator = new JSeparator();
        separator.setBounds(10, 35, 534, 2);
        topbar.add(separator);

        frame.getContentPane().add(tb.topbarLayout(topbar, tb.id, tb.FullName));

        btnBack = new JButton("Back");
        btnBack.setBounds(10, 52, 87, 23);
        btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new BackAction());

        lblMobile = new JLabel("Mobile");
        lblMobile.setBounds(148, 45, 87, 35);
        lblMobile.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblMobile);

        textField_Mobile = new JTextField();
        textField_Mobile.setBounds(228, 50, 127, 23);
        textField_Mobile.setDocument(new JTextFieldLimit(50));

        frame.getContentPane().add(textField_Mobile);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(365, 52, 87, 23);
        btnSearch.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnSearch.addActionListener(new SearchAction());
        frame.getContentPane().add(btnSearch);

        MovieTable = new JTable();
        MovieTable.setBounds(0, 0, 478, 233);

        DefaultTableModel model = defaultTableModel();
        model.addRow(new Object[]{"123", "11:15 pm", "Chinese", "183 mins"});
        MovieTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(MovieTable);
        scrollPane.setBounds(10, 102, 488, 233);
        frame.getContentPane().add(scrollPane);

    }

    private class BackAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (staff != null)
                new staff_menu(staff);
            else
                new member_menu(customer);
            frame.dispose();

        }
    }

    private class SearchAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // TODO: search TRANSACTIONS
        }
    }

    private DefaultTableModel defaultTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Movie Name");
        model.addColumn("Start Time");
        model.addColumn("Language");
        model.addColumn("Length");

        return model;
    }
}
