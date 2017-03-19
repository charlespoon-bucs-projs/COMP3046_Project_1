package org.comp3046.it9.UI.Menu;

import org.comp3046.it9.Entity.Customer;
import org.comp3046.it9.UI.Login.LoginFrame;
import org.comp3046.it9.UI.Member.MemberSetting;
import org.comp3046.it9.UI.Search.SearchMovie;
import org.comp3046.it9.UI.TransactionRecord.TransactionRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberMenu {

    private final LoginFrame loginFrame;
    private final Customer customer;

    private final TopBar tp;
    private final JFrame frame;
    private JLabel lblLoginer;
    private JSeparator separator;
    private JButton btnAccountSetting, btnSearchMovie, btnTransactionRecord, btnLogout;
    private JPanel topbar;

    /**
     * Create the application.
     */
    public MemberMenu(LoginFrame LoginFrame, Customer customer) {
        this.loginFrame = LoginFrame;
        this.customer = customer;

        tp = new TopBar();
        frame = new JFrame();
        frame.setBounds(100, 100, 524, 340);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("XXX Cinema - Menu");
        frame.setVisible(true);
        initialize();
        tp.clock();
    }



    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame.getContentPane().setLayout(null);
        topbar = new JPanel();
        topbar.setLayout(null);
        topbar.setBounds(0, 0, 504, 40);

        lblLoginer = new JLabel("Login as ");
        lblLoginer.setText(lblLoginer.getText() + this.customer.getName() + "-Member");
        lblLoginer.setBounds(304, 10, 200, 15);

        lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        topbar.add(lblLoginer);

        separator = new JSeparator();
        separator.setBounds(10, 35, 534, 2);
        topbar.add(separator);

        frame.getContentPane().add(tp.topbarLayout(topbar, this.customer.getUid() + "", this.customer.getName()));

        btnAccountSetting = new JButton("Account Setting");
        btnAccountSetting.setBounds(20, 47, 215, 93);
        btnAccountSetting.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
        btnAccountSetting.addActionListener(new AccountSettingAction());
        frame.getContentPane().add(btnAccountSetting);

        btnSearchMovie = new JButton("Search Movie");
        btnSearchMovie.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
        btnSearchMovie.setBounds(272, 47, 215, 93);
        btnSearchMovie.addActionListener(new SearchMovieAction());
        frame.getContentPane().add(btnSearchMovie);

        btnTransactionRecord = new JButton("Transaction Record");
        btnTransactionRecord.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
        btnTransactionRecord.addActionListener(new TransactionRecordAction());
        btnTransactionRecord.setBounds(129, 163, 266, 93);
        frame.getContentPane().add(btnTransactionRecord);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(210, 268, 88, 23);
        btnLogout.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnLogout.addActionListener(new LogoutAction());
        frame.getContentPane().add(btnLogout);

    }

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    public Customer getCustomer() {
        return customer;
    }

    private MemberMenu getSelf() {
        return this;
    }

    private class AccountSettingAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new MemberSetting(getSelf());
            setVisible(false);
        }
    }

    private class SearchMovieAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new SearchMovie(getSelf());
            setVisible(false);
        }
    }

    private class TransactionRecordAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new TransactionRecord(getSelf());
            setVisible(false);
        }
    }

    private class LogoutAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            loginFrame.setVisible(true);
            frame.dispose();
        }
    }

}
