package org.comp3046.it9.UI.Menu;

import org.comp3046.it9.UI.Index.index;
import org.comp3046.it9.UI.TransactionRecord.transactionRecord;
import org.comp3046.it9.UI.member.MemberSetting;
import org.comp3046.it9.UI.search.searchMovie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class member_menu {

    topbar tp;
    private JFrame frame;
    private JLabel lblLoginer;
    private JSeparator separator;
    private JButton btnAccountSetting, btnSearchMovie, btnTransactionRecord, btnLogout;
    private String id, FullName;
    private JPanel topbar;

    /**
     * Create the application.
     */
    public member_menu(String id, String FullName) {
        this.id = id;
        this.FullName = FullName;
        tp = new topbar();
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
        lblLoginer.setText(lblLoginer.getText() + FullName + "-Member");
        lblLoginer.setBounds(304, 10, 200, 15);

        lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        topbar.add(lblLoginer);

        separator = new JSeparator();
        separator.setBounds(10, 35, 534, 2);
        topbar.add(separator);

        frame.getContentPane().add(tp.topbarLayout(topbar, id, FullName));

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

    private class AccountSettingAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new MemberSetting(false, false);
            frame.dispose();
        }
    }

    private class SearchMovieAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new searchMovie();
            frame.dispose();
        }
    }

    private class TransactionRecordAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new transactionRecord(false);
            frame.dispose();
        }
    }

    private class LogoutAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new index();
            frame.setVisible(false);

        }
    }

}
