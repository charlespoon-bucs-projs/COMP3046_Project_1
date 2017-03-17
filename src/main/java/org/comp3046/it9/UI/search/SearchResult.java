package org.comp3046.it9.UI.search;

import org.comp3046.it9.Entity.Movie;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.topbar;
import org.comp3046.it9.UI.buyTicket.PayMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchResult {
    // parent
    private final MemberMenu memberMenu;
    // previous
    private final SearchMovie searchMovie;
    // target
    private final Movie movie;
    private final String[] selectedSeat; // new from this step

    @Deprecated
    String movie_id, movie_name;

    topbar tb;
    private JFrame frame;
    private JPanel topbar;
    private JLabel lblLoginer, lblMovieName, lblHouse, lblTime;
    private JButton btnBack, btnNext, btnReset;
    private JSeparator separator;
    private JComboBox comboBox_MovieName, comboBox_House, comboBox_Time;
    private JButton btnA1, A2, A3, A4, B1, B2, B3, B4;
    private JLabel lblNewLabel;

    public SearchResult(MemberMenu memberMenu, SearchMovie searchMovie, Movie movie) {
        this.memberMenu = memberMenu;
        this.searchMovie = searchMovie;
        this.movie = movie;

        tb = new topbar();
        frame = new JFrame();
        frame.setBounds(100, 100, 524, 353);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("XXX Cinema - Search Movie");
        selectedSeat = new String[8];
        for (int i = 0; i < 8; i++) {
            selectedSeat[i] = "";

        }
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
        btnBack.setBounds(10, 50, 87, 23);
        btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new BackAction());

        lblMovieName = new JLabel();
        lblMovieName.setText(movie_name);
        lblMovieName.setHorizontalAlignment(JLabel.CENTER);
        lblMovieName.setBounds(124, 85, 276, 50);
        lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 26));
        frame.getContentPane().add(lblMovieName);

        lblNewLabel = new JLabel("screen");
        lblNewLabel.setBackground(Color.CYAN);
        lblNewLabel.setBounds(134, 130, 238, 15);
        lblNewLabel.setOpaque(true);
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(lblNewLabel);

        JButton btnA1 = new JButton("A1");
        btnA1.setBounds(124, 155, 52, 40);
        btnA1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA1);
        btnA1.addActionListener(new SeatAction());

        JButton btnA2 = new JButton("A2");
        btnA2.setBounds(196, 155, 52, 40);
        btnA2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA2);
        btnA2.addActionListener(new SeatAction());

        JButton btnA3 = new JButton("A3");
        btnA3.setBounds(248, 155, 52, 40);
        btnA3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA3);
        btnA3.addActionListener(new SeatAction());

        JButton btnA4 = new JButton("A4");
        btnA4.setBounds(320, 155, 52, 40);
        btnA4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA4);
        btnA4.addActionListener(new SeatAction());

        JButton btnB1 = new JButton("B1");
        btnB1.setBounds(124, 195, 52, 40);
        btnB1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB1);
        btnB1.addActionListener(new SeatAction());

        JButton btnB2 = new JButton("B2");
        btnB2.setBounds(196, 195, 52, 40);
        btnB2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB2);
        btnB2.addActionListener(new SeatAction());

        JButton btnB3 = new JButton("B3");
        btnB3.setBounds(248, 195, 52, 40);
        btnB3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB3);
        btnB3.addActionListener(new SeatAction());

        JButton btnB4 = new JButton("B4");
        btnB4.setBounds(320, 195, 52, 40);
        btnB4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB4);
        btnB4.addActionListener(new SeatAction());

        btnNext = new JButton("Next");
        btnNext.setBounds(276, 281, 87, 23);
        btnNext.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnNext); //
        btnNext.addActionListener(new NextAction());

        btnReset = new JButton("Reset");
        btnReset.setBounds(397, 281, 87, 23);
        btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnReset);
        btnNext.addActionListener(new ResetAction());

    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public SearchResult getSelf() {
        return this;
    }

    public SearchMovie getSearchMovie() {
        return searchMovie;
    }

    public void dispose() {
        frame.dispose();
    }

    private class SeatAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object btn = event.getSource();

            if (btn instanceof JButton) {
                if (((JButton) btn).getBackground().equals(Color.RED)) {
                    ((JButton) btn).setBackground(UIManager.getColor("panelButtons.Background"));
                    for (int i = 0; i < selectedSeat.length; i++) {
                        if (selectedSeat[i].equals(((JButton) btn).getText())) {
                            selectedSeat[i] = "";
                            break;
                        }
                    }

                } else {
                    ((JButton) btn).setBackground(Color.RED);
                    for (int i = 0; i < selectedSeat.length; i++) {
                        if (selectedSeat[i].equals("")) {
                            selectedSeat[i] = ((JButton) btn).getText();
                            break;
                        }
                    }
                }

            }

        }
    }

    private class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

        }
    }

    private class NextAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            new PayMethod(memberMenu, getSelf(), movie, selectedSeat);
            frame.setVisible(false);
        }
    }

    private class BackAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            searchMovie.setVisible(true);
            frame.setVisible(false);
            frame.dispose();

        }
    }
}
