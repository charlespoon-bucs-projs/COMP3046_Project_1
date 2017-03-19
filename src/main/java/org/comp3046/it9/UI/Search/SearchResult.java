package org.comp3046.it9.UI.Search;

import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Database.TransactionsDb;
import org.comp3046.it9.Entity.Movie;
import org.comp3046.it9.Entity.Transaction;
import org.comp3046.it9.UI.BuyTicket.PayMethod;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchResult {
    // parent
    private final MemberMenu memberMenu;
    // previous
    private final SearchMovie searchMovie;
    // target
    private final Movie movie;
    private final String[] selectedSeat; // new from this step

    @Deprecated
    String movie_id;
    @Deprecated
    private String movie_name;

    private final TopBar tb;
    private final JFrame frame;
    private JLabel lblHouse;
    private JLabel lblTime;
    private JComboBox comboBox_MovieName;
    private JComboBox comboBox_House;
    private JComboBox comboBox_Time;

    private HashMap<String, JButton> seatButtons;

    public SearchResult(MemberMenu memberMenu, SearchMovie searchMovie, Movie movie) {
        this.memberMenu = memberMenu;
        this.searchMovie = searchMovie;
        this.movie = movie;

        this.seatButtons = new HashMap<>();

        tb = new TopBar();
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

        async();
    }

    private void async() {
        new Thread(() -> {
            try (Sqlite sqlite = new Sqlite()) {
                TransactionsDb tr = new TransactionsDb(sqlite);

                Map<Integer, Transaction> mt = tr.getTransactionsByMovie(movie);

                if (mt.size() == 0) return;

                mt.values().stream()
                        .flatMap(m -> Arrays.stream(m.getSeat().split(",\\s*")))
                        .collect(Collectors.toList())
                        .forEach(sn -> {
                            JButton seatBtn = seatButtons.get(sn);
                            if (seatBtn == null)
                                throw new NullPointerException(String.format("No seat available: \"%s\"", sn));
                            seatBtn.setEnabled(false);
                        });
            } catch (FileNotFoundException ignored) {
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\nMissing database file.", "Search Movie", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Cannot fetch movie data from database: " + e.getMessage(),
                        "Search Movie",
                        JOptionPane.ERROR_MESSAGE);
            }
        }).start();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        JPanel topbar = new JPanel();
        topbar.setLayout(null);
        topbar.setBounds(0, 0, 504, 40);

        JLabel lblLoginer = new JLabel("Login as ");
        lblLoginer.setText(lblLoginer.getText() + memberMenu.getCustomer().getName() + "-Member");
        lblLoginer.setBounds(304, 10, 200, 15);

        lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        topbar.add(lblLoginer);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 35, 534, 2);
        topbar.add(separator);

        frame.getContentPane().add(tb.topbarLayout(
                topbar, memberMenu.getCustomer().getUid() + "", memberMenu.getCustomer().getName()
        ));

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 50, 87, 23);
        btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new BackAction());

        JLabel lblMovieName = new JLabel();
        lblMovieName.setText(movie_name);
        lblMovieName.setHorizontalAlignment(JLabel.CENTER);
        lblMovieName.setBounds(124, 85, 276, 50);
        lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 26));
        frame.getContentPane().add(lblMovieName);

        JLabel lblNewLabel = new JLabel("screen");
        lblNewLabel.setBackground(Color.CYAN);
        lblNewLabel.setBounds(134, 130, 238, 15);
        lblNewLabel.setOpaque(true);
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(lblNewLabel);

        JButton btnA1 = new JButton("A1");
        btnA1.setBounds(124, 155, 52, 40);
        btnA1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA1);
        seatButtons.put("A1", btnA1);

        JButton btnA2 = new JButton("A2");
        btnA2.setBounds(196, 155, 52, 40);
        btnA2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA2);
        seatButtons.put("A2", btnA2);

        JButton btnA3 = new JButton("A3");
        btnA3.setBounds(248, 155, 52, 40);
        btnA3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA3);
        seatButtons.put("A3", btnA3);

        JButton btnA4 = new JButton("A4");
        btnA4.setBounds(320, 155, 52, 40);
        btnA4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnA4);
        seatButtons.put("A4", btnA4);

        JButton btnB1 = new JButton("B1");
        btnB1.setBounds(124, 195, 52, 40);
        btnB1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB1);
        seatButtons.put("B1", btnB1);

        JButton btnB2 = new JButton("B2");
        btnB2.setBounds(196, 195, 52, 40);
        btnB2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB2);
        seatButtons.put("B2", btnB2);

        JButton btnB3 = new JButton("B3");
        btnB3.setBounds(248, 195, 52, 40);
        btnB3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB3);
        seatButtons.put("B3", btnB3);

        JButton btnB4 = new JButton("B4");
        btnB4.setBounds(320, 195, 52, 40);
        btnB4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnB4);
        seatButtons.put("B4", btnB4);

        seatButtons.values().forEach(b -> b.addActionListener(new SeatAction()));

        JButton btnNext = new JButton("Next");
        btnNext.setBounds(276, 281, 87, 23);
        btnNext.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnNext); //
        btnNext.addActionListener(new NextAction());

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(397, 281, 87, 23);
        btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnReset);
        btnNext.addActionListener(new ResetAction());

    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    private SearchResult getSelf() {
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
