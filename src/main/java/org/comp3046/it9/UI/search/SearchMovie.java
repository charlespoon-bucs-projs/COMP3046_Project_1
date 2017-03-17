package org.comp3046.it9.UI.search;

import org.comp3046.it9.Entity.Movie;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.topbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchMovie {
    private final MemberMenu memberMenu;

    topbar tb;
    private JFrame frame;
    private JPanel topbar;
    private JLabel lblLoginer, lblMovieName, lblHouse, lblTime;
    private JButton btnBack, btnSubmit, btnReset;
    private JSeparator separator;
    private JComboBox comboBox_MovieName, comboBox_House, comboBox_Time;

    /**
     * Create the application.
     */
    public SearchMovie(MemberMenu memberMenu) {
        this.memberMenu = memberMenu;

        tb = new topbar();
        frame = new JFrame();
        frame.setBounds(100, 100, 524, 457);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("XXX Cinema - Search Movie");
        initialize();
        tb.clock();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        topbar = new JPanel();
        topbar.setLayout(null);
        topbar.setBounds(0, 0, 504, 40);

        lblLoginer = new JLabel("Login as ");
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

        lblMovieName = new JLabel("Movie Name");
        lblMovieName.setBounds(66, 72, 138, 35);
        lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        frame.getContentPane().add(lblMovieName);

        comboBox_MovieName = new JComboBox();
        comboBox_MovieName.setBounds(109, 117, 307, 40);
        // TODO: no more dummy movie titles
        comboBox_MovieName.addItem("Movie 1");
        comboBox_MovieName.addItem("Movie 2");
        comboBox_MovieName.addItem("Movie 3");
        frame.getContentPane().add(comboBox_MovieName);

        lblHouse = new JLabel("House");
        lblHouse.setBounds(66, 167, 138, 35);
        lblHouse.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        frame.getContentPane().add(lblHouse);

        comboBox_House = new JComboBox();
        comboBox_House.setBounds(109, 212, 307, 40);
        comboBox_House.addItem("A");
        comboBox_House.addItem("B");
        comboBox_House.addItem("C");
        frame.getContentPane().add(comboBox_House);

        lblTime = new JLabel("Show Time");
        lblTime.setBounds(66, 262, 138, 35);
        lblTime.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        frame.getContentPane().add(lblTime);

        comboBox_Time = new JComboBox();
        comboBox_Time.setBounds(109, 307, 307, 40);
        comboBox_Time.addItem("time 1");
        comboBox_Time.addItem("time 2");
        comboBox_Time.addItem("time 3");
        frame.getContentPane().add(comboBox_Time);

        btnSubmit = new JButton("Buy Ticket");
        btnSubmit.setBounds(276, 366, 87, 23);
        btnSubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnSubmit); //
        btnSubmit.addActionListener(new SubmitAction());

        btnReset = new JButton("Reset");
        btnReset.setBounds(397, 366, 87, 23);
        btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnReset); //
        btnReset.addActionListener(new ResetAction());

    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public SearchMovie getSelf() {
        return this;
    }

    public void dispose() {
        frame.dispose();
    }

    private class BackAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            memberMenu.setVisible(true);
            frame.setVisible(false);
            frame.dispose();

        }
    }

    private class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            //noinspection ConstantIfStatement,ConstantConditions
            if (true) throw new UnsupportedOperationException("not yet implemented");

            // TODO: gimme movie entity
            Movie movie = null;

            new SearchResult(memberMenu, getSelf(), movie);
            frame.setVisible(false);
        }
    }

    private class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            comboBox_MovieName.setSelectedIndex(0);
            comboBox_House.setSelectedIndex(0);
            comboBox_Time.setSelectedIndex(0);

        }
    }

}
