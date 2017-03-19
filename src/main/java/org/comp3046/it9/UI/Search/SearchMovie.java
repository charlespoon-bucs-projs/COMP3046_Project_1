package org.comp3046.it9.UI.Search;

import org.comp3046.it9.Database.MovieDb;
import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Entity.Movie;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchMovie {
    private final MemberMenu memberMenu;

    private Map<Integer, Movie> moviesList = null;
    private Stream<Movie> _cacheForTimeFilter = null;
    private Map<String, Movie> _cacheForSearchResult = null;

    private final TopBar tb;
    private final JFrame frame;
    private JPanel topbar;
    private JLabel lblLoginer, lblMovieName, lblHouse, lblTime;
    private JButton btnBack, btnSubmit, btnReset;
    private JSeparator separator;
    private JComboBox<String> comboBox_MovieName;
    private JComboBox<String> comboBox_House;
    private JComboBox<String> comboBox_Time;

    /**
     * Create the application.
     */
    public SearchMovie(MemberMenu memberMenu) {
        this.memberMenu = memberMenu;

        tb = new TopBar();
        frame = new JFrame();
        frame.setBounds(100, 100, 524, 457);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("XXX Cinema - Search Movie");
        initialize();
        tb.clock();

        loadMoviesFromDb();
    }

    private void loadMoviesFromDb() {
        comboBox_MovieName.setEnabled(false);
        comboBox_House.setEnabled(false);
        comboBox_Time.setEnabled(false);

        new Thread(() -> {
            try (Sqlite sqlite = new Sqlite()) {
                MovieDb movieDb = new MovieDb(sqlite);

                moviesList = movieDb.getMoviesList();

                comboBox_MovieName.removeAllItems();
                moviesList.values().stream().map(Movie::getName).distinct().forEach(mt -> comboBox_MovieName.addItem(mt));

                comboBox_MovieName.setEnabled(true);
            } catch (FileNotFoundException ignored) {
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\nMissing database file.", "Search movie", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Cannot fetch movie data from database: " + e.getMessage(),
                        "Search movie",
                        JOptionPane.ERROR_MESSAGE);
            }

            comboBox_MovieName.setEnabled(true);
            comboBox_House.setEnabled(true);
            comboBox_Time.setEnabled(true);
        }).start();
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
        lblLoginer.setText(lblLoginer.getText() + memberMenu.getCustomer().getName() + "-Member");
        lblLoginer.setBounds(304, 10, 200, 15);

        lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        topbar.add(lblLoginer);

        separator = new JSeparator();
        separator.setBounds(10, 35, 534, 2);
        topbar.add(separator);

        frame.getContentPane().add(
                tb.topbarLayout(
                        topbar,
                        memberMenu.getCustomer().getUid() + "",
                        memberMenu.getCustomer().getName())
        );

        btnBack = new JButton("Back");
        btnBack.setBounds(10, 52, 87, 23);
        btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new BackAction());

        lblMovieName = new JLabel("Movie Name");
        lblMovieName.setBounds(66, 72, 138, 35);
        lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        frame.getContentPane().add(lblMovieName);

        comboBox_MovieName = new JComboBox<>();
        comboBox_MovieName.setBounds(109, 117, 307, 40);
        comboBox_MovieName.addActionListener(new MovieNameChangedListener());
        frame.getContentPane().add(comboBox_MovieName);

        lblHouse = new JLabel("House");
        lblHouse.setBounds(66, 167, 138, 35);
        lblHouse.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        frame.getContentPane().add(lblHouse);

        comboBox_House = new JComboBox<String>();
        comboBox_House.setBounds(109, 212, 307, 40);
        comboBox_House.addActionListener(new HouseSelectionChangeListener());
        frame.getContentPane().add(comboBox_House);

        lblTime = new JLabel("Show Time");
        lblTime.setBounds(66, 262, 138, 35);
        lblTime.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        frame.getContentPane().add(lblTime);

        comboBox_Time = new JComboBox<>();
        comboBox_Time.setBounds(109, 307, 307, 40);
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

    private SearchMovie getSelf() {
        return this;
    }

    public void dispose() {
        frame.dispose();
    }

    private class BackAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            memberMenu.setVisible(true);
            frame.dispose();

        }
    }

    private class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (_cacheForSearchResult == null) {
                JOptionPane.showMessageDialog(null, "Start time is not selected", "Search Movie", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String strTimeKey = (String) comboBox_Time.getSelectedItem();
            Movie movie = _cacheForSearchResult.get(strTimeKey);

            new SearchResult(memberMenu, getSelf(), movie);
            setVisible(false);
        }
    }

    private class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            comboBox_MovieName.setSelectedIndex(0);
            comboBox_House.setSelectedIndex(0);
            comboBox_Time.setSelectedIndex(0);

        }
    }

    private class MovieNameChangedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String movieName = (String) comboBox_MovieName.getSelectedItem();
            comboBox_House.removeAllItems();
            _cacheForTimeFilter = moviesList.values().stream().filter(m -> m.getName().equals(movieName));
            _cacheForTimeFilter.map(Movie::getLocation).distinct().forEach(ml -> comboBox_House.addItem(ml));

            comboBox_House.setEnabled(comboBox_MovieName.getSelectedIndex() > -1);
        }
    }

    private class HouseSelectionChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (_cacheForTimeFilter == null) return;

            String movieName = (String) comboBox_MovieName.getSelectedItem();
            String location = (String) comboBox_House.getSelectedItem();
            comboBox_Time.removeAllItems();

            _cacheForSearchResult = _cacheForTimeFilter
                    .filter(m -> m.getLocation().equals(location))
                    .collect(Collectors.toMap(
                            m -> m.getStartHour() + ":" + m.getStartMinute(),
                            m -> m
                    ));

            _cacheForSearchResult.keySet().stream().distinct().forEach(mt -> comboBox_Time.addItem(mt));

            comboBox_Time.setEnabled(comboBox_House.getSelectedIndex() > -1);
        }
    }
}
