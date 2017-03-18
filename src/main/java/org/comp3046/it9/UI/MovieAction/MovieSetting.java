package org.comp3046.it9.UI.MovieAction;

import org.comp3046.it9.Database.MovieDb;
import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Entity.Movie;
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
import java.util.Map;
import java.util.Properties;

public class MovieSetting {
    private final StaffMenu staffMenu;
    private boolean editMovie;
    private Map<Integer, Movie> cachedMoviesToEdit;
    private Movie movieEditing;

    private TopBar tb;
    //    boolean isAdd;
    MaskFormatter mf1;
    private UtilDateModel model;
    private JFrame frame;
    private JPanel topbar;
    private JLabel lblLoginer, lblMovieImage, lblMovieName, lblDate, lblType, lblClass, lblLang, lblLeng, lblDirector,
            lblCast, lblHouse, lblTime, lblPrice;
    private JButton btnBack, btnSubmit, btnReset, btnDelete;
    private JSeparator separator;
    private JTextField textField_MovieName, textField_Director, textField_Price, textField_Leng, textField_Cast;
    private JComboBox<String> comboBox_Type;
    private JComboBox<String> comboBox_Class;
    private JComboBox<String> comboBox_Lang;
    private JComboBox<String> comboBox_House;
    private JComboBox<String> comboBox_hours;
    private JComboBox<String> comboBox_apm;
    private JComboBox<String> comboBox_minutes;
    private JComboBox<Integer> comboBox_ID;
    private JLabel lblId;

    private JDatePickerImpl datePicker;

    public MovieSetting(StaffMenu staffMenu) {
        this.staffMenu = staffMenu;
        _ctor();
    }

    public MovieSetting(StaffMenu staffMenu,
                        @SuppressWarnings({"SameParameterValue", "UnusedParameters"}) Object editMovie) {
        this.staffMenu = staffMenu;
        this.editMovie = true;
        _ctor();
    }

    /*
    // not possible
    public movieSetting(boolean isAdd, Customer customer) {
        this.customer = customer;
        _ctor(isAdd);
    }
    */

    private void _ctor() {
//        this.isAdd = isAdd;
        initialize();
        tb.clock();

        // async
        if (this.editMovie)
            new Thread(() -> {
                try (Sqlite sqlite = new Sqlite()) {
                    MovieDb movieDb = new MovieDb(sqlite);
                    this.cachedMoviesToEdit = movieDb.getMoviesList();
                    this.cachedMoviesToEdit.keySet().forEach(k -> comboBox_ID.addItem(k));
                } catch (FileNotFoundException ignored) {
                    JOptionPane.showMessageDialog(null, "Error: \r\n\r\nMissing database file.", "Movie setting", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(
                            null,
                            "Cannot fetch movie data from database: " + e.getMessage(),
                            "Movie setting",
                            JOptionPane.ERROR_MESSAGE);
                }
            }).start();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();

        tb = new TopBar();
        frame.setBounds(100, 100, 524, 465);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        topbar = new JPanel();
        topbar.setLayout(null);
        topbar.setBounds(0, 0, 504, 40);

        lblLoginer = new JLabel("Login as ");
        lblLoginer.setText(lblLoginer.getText() + staffMenu.getStaff().getName() + "-Staff");
        lblLoginer.setBounds(304, 10, 200, 15);

        lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        topbar.add(lblLoginer);

        separator = new JSeparator();
        separator.setBounds(10, 35, 534, 2);
        topbar.add(separator);

        frame.getContentPane().add(
                tb.topbarLayout(
                        topbar,
                        staffMenu.getStaff().getId() + "",
                        staffMenu.getStaff().getName()
                )
        );

        btnBack = new JButton("Back");
        btnBack.setBounds(10, 52, 87, 23);
        btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new BackAction());

        JLabel lblMovieImage = new JLabel("Click  For  Movie Image"); // may
        // has
        // click
        // event
        lblMovieImage.setOpaque(true);
        lblMovieImage.setBackground(Color.LIGHT_GRAY);
        lblMovieImage.setBounds(10, 85, 148, 188);

        frame.getContentPane().add(lblMovieImage);

        lblMovieName = new JLabel("Movie Name");
        lblMovieName.setBounds(130, 50, 138, 35);
        lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        frame.getContentPane().add(lblMovieName);

        textField_MovieName = new JTextField();
        textField_MovieName.setBounds(235, 50, 263, 31);
        textField_MovieName.setDocument(new JTextFieldLimit(50));

        frame.getContentPane().add(textField_MovieName);

        textField_MovieName.setColumns(15);

        lblDate = new JLabel("Date");
        lblDate.setBounds(168, 149, 62, 35);
        lblDate.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblDate);

        model = new UtilDateModel();

        // model.setDate(20,04,2014);
        // Need this...
        model.setSelected(true);
        model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        datePicker.setBounds(212, 149, 138, 28);
        datePicker.addActionListener(new CheckDateAction());
        frame.getContentPane().add(datePicker);

        lblClass = new JLabel("Class");
        lblClass.setBounds(360, 149, 62, 35);
        lblClass.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblClass);

        comboBox_Class = new JComboBox<>();
        comboBox_Class.setBounds(411, 149, 87, 28);
        comboBox_Class.addItem("I");
        comboBox_Class.addItem("II");
        comboBox_Class.addItem("IIA");
        comboBox_Class.addItem("IIB");
        comboBox_Class.addItem("III");
        frame.getContentPane().add(comboBox_Class);

        lblType = new JLabel("Type");
        lblType.setBounds(360, 194, 62, 35);
        lblType.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblType);

        comboBox_Type = new JComboBox<>();
        comboBox_Type.setBounds(411, 194, 87, 28);

        comboBox_Type.addItem("Action");
        comboBox_Type.addItem("Adventure");
        comboBox_Type.addItem("Romance");
        frame.getContentPane().add(comboBox_Type);

        lblLang = new JLabel("Language");
        lblLang.setBounds(168, 194, 87, 35);
        lblLang.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblLang);

        comboBox_Lang = new JComboBox<>();
        comboBox_Lang.setBounds(258, 194, 92, 28);
        comboBox_Lang.addItem("Chinese");
        comboBox_Lang.addItem("English");
        comboBox_Lang.addItem("Cantonese");

        frame.getContentPane().add(comboBox_Lang);

        lblDirector = new JLabel("Director");
        lblDirector.setBounds(168, 239, 87, 35);
        lblDirector.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblDirector);

        textField_Director = new JTextField();
        textField_Director.setBounds(223, 239, 127, 28);
        textField_Director.setDocument(new JTextFieldLimit(50));
        textField_Director.setToolTipText("Director");
        frame.getContentPane().add(textField_Director);

        lblLeng = new JLabel("Length");
        lblLeng.setBounds(360, 239, 56, 35);
        lblLeng.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblLeng);

        textField_Leng = new JTextField();
        textField_Leng.setBounds(411, 239, 87, 28);

        frame.getContentPane().add(textField_Leng);

        lblPrice = new JLabel("Price");
        lblPrice.setBounds(168, 278, 87, 35);
        lblPrice.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblPrice);

        textField_Price = new JTextField();
        textField_Price.setBounds(228, 278, 122, 28);
        textField_Price.setDocument(new JTextFieldLimit(10));
        frame.getContentPane().add(textField_Price);

        lblHouse = new JLabel("House");
        lblHouse.setBounds(360, 278, 56, 35);
        lblHouse.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblHouse);

        comboBox_House = new JComboBox<>();
        comboBox_House.setBounds(411, 278, 87, 28);
        comboBox_House.addItem("House A");
        comboBox_House.addItem("House B");
        comboBox_House.addItem("House C");
        comboBox_House.addItem("IMAX");
        frame.getContentPane().add(comboBox_House);

        lblTime = new JLabel("Start Time");
        lblTime.setBounds(168, 314, 87, 35);
        lblTime.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblTime);

        comboBox_hours = new JComboBox<String>();
        comboBox_hours.setBounds(262, 314, 62, 28);
        comboBox_hours.addItem("01");
        comboBox_hours.addItem("02");
        comboBox_hours.addItem("03");
        comboBox_hours.addItem("04");
        comboBox_hours.addItem("05");
        comboBox_hours.addItem("06");
        comboBox_hours.addItem("07");
        comboBox_hours.addItem("08");
        comboBox_hours.addItem("09");
        comboBox_hours.addItem("10");
        comboBox_hours.addItem("11");
        comboBox_hours.addItem("12");
        comboBox_hours.addItem("13");
        comboBox_hours.addItem("14");
        comboBox_hours.addItem("15");
        comboBox_hours.addItem("16");
        comboBox_hours.addItem("17");
        comboBox_hours.addItem("18");
        comboBox_hours.addItem("19");
        comboBox_hours.addItem("20");
        comboBox_hours.addItem("21");
        comboBox_hours.addItem("22");
        comboBox_hours.addItem("23");
        comboBox_hours.addItem("24");
        frame.getContentPane().add(comboBox_hours);

        /*
        comboBox_apm = new JComboBox<>();
        comboBox_apm.setBounds(452, 314, 46, 28);
        comboBox_apm.addItem("am");
        comboBox_apm.addItem("pm");
        frame.getContentPane().add(comboBox_apm);
        */

        comboBox_minutes = new JComboBox<String>();
        comboBox_minutes.setBounds(334, 314, 87, 28);
        comboBox_minutes.addItem("00 min");
        comboBox_minutes.addItem("15 min");
        comboBox_minutes.addItem("30 min");
        comboBox_minutes.addItem("45 min");
        frame.getContentPane().add(comboBox_minutes);

        lblCast = new JLabel("Cast");
        lblCast.setBounds(168, 348, 87, 35);
        lblCast.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        frame.getContentPane().add(lblCast);

        textField_Cast = new JTextField();
        textField_Cast.setBounds(228, 348, 270, 28);
        textField_Cast.setDocument(new JTextFieldLimit(10));
        frame.getContentPane().add(textField_Cast);

        btnDelete = new JButton("Delete Movie");
        btnDelete.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnDelete.setBounds(10, 393, 122, 23);
        btnDelete.addActionListener(new DeleteAction());
        btnDelete.setEnabled(this.editMovie); // only editing movie can use this button
        frame.getContentPane().add(btnDelete);

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnSubmit.setBounds(299, 393, 87, 23);
        btnDelete.addActionListener(new SubmitAction());
        frame.getContentPane().add(btnSubmit);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        btnReset.setBounds(411, 393, 87, 23);
        btnDelete.addActionListener(new ResetAction());
        frame.getContentPane().add(btnReset);

        comboBox_ID = new JComboBox<>();
        comboBox_ID.setBounds(235, 85, 263, 31);
        comboBox_ID.setEnabled(this.editMovie);
        comboBox_ID.addActionListener(new IDSelectionListener());
        frame.getContentPane().add(comboBox_ID);

        lblId = new JLabel("ID");
        lblId.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        lblId.setBounds(168, 85, 62, 35);
        frame.getContentPane().add(lblId);

        if (this.editMovie) // check the staff want to add or modify
        {
            frame.setTitle("XXX Cinema - Modify Movie");
            textField_MovieName.setText("ACB Movie");
        } else {
            frame.setTitle("XXX Cinema - Add New Movie");
        }

    }

    private class BackAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            frame.setVisible(false);
            staffMenu.setVisible(true);
            frame.dispose();
        }
    }

    private class DeleteAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String msg = "";
            msg += String.format("Are you sure you want to remove movie \"%s\"? \r\n", movieEditing.getName());
            msg += "This action cannot be undone.";

            int ans = JOptionPane.showConfirmDialog(null, msg, "Remove movie", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (ans != 0) return;

            try (Sqlite sqlite = new Sqlite()) {
                MovieDb movieDb = new MovieDb(sqlite);
                boolean dbOk = movieDb.removeMovie(movieEditing.getId());
                if (!dbOk) {
                    JOptionPane.showMessageDialog(null, "Failed to modify data on database, unknown reason.", "Movie Settings", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, String.format("Successful to delete data of movie \"%s\".", movieEditing.getName()), "Movie Settings", JOptionPane.INFORMATION_MESSAGE);

                // cleanup
                cachedMoviesToEdit.remove(movieEditing.getId()); // delete cache first, or may affected by new selection below
                comboBox_ID.setSelectedIndex(comboBox_ID.getSelectedIndex() - 1); // may set as -1. see doc
            } catch (FileNotFoundException ignored) {
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\nMissing database file.", "Error deleting movie", JOptionPane.ERROR_MESSAGE);

            } catch (SQLException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Error: \r\n\r\n" + e.getMessage(),
                        "Error deleting movie",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Movie m;
            try {
                m = fetchMovieFromFields();
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(null, "Not a number.", "Movie setting", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean isAdd = !editMovie;

            try (Sqlite sqlite = new Sqlite()) {
                MovieDb movieDb = new MovieDb(sqlite);

                boolean dbOk = false;

                if (isAdd) {
                    dbOk = movieDb.createMovie(m);
                } else {
                    dbOk = movieDb.updateMovie(m);
                }

                if (!dbOk) {
                    JOptionPane.showMessageDialog(null, "Failed to modify data on database, unknown reason.", "Movie Settings", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(null, "Successful to " + (isAdd ? "add" : ("modify movie " + m.getId())) + " data.", "Movie Settings", JOptionPane.INFORMATION_MESSAGE);

                // back
                btnBack.doClick();
            } catch (FileNotFoundException ignored) {
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\nMissing database file.", "Modify member", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: \r\n\r\n" + e.getMessage(), "Modify member", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ResetAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (editMovie) {
                new IDSelectionListener().actionPerformed(event);
            } else {
                pasteMovieToFields(null);
            }
        }
    }

    private class CheckDateAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (model.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
                if (model.getMonth() == Calendar.getInstance().get(Calendar.MONTH)) {
                    if (model.getDay() <= Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                        model.setDate(Calendar.getInstance().get(Calendar.YEAR),
                                Calendar.getInstance().get(Calendar.MONTH),
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

                    }
                } else {
                    if (model.getMonth() < Calendar.getInstance().get(Calendar.MONTH)) {
                        model.setDate(Calendar.getInstance().get(Calendar.YEAR),
                                Calendar.getInstance().get(Calendar.MONTH),
                                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    }

                }

            } else {
                if (model.getYear() >= Calendar.getInstance().get(Calendar.YEAR)) {
                    model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                }

            }
        }
    }

    private class IDSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // selection changes
            int selectedMovieId = (int) comboBox_ID.getSelectedItem();
            movieEditing = cachedMoviesToEdit.get(selectedMovieId);
            pasteMovieToFields(movieEditing);
        }
    }

    private void pasteMovieToFields(Movie m) {
        if (m == null) {
            textField_MovieName.setText("");
            // no use for ID
            datePicker.getModel().setDate(
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            comboBox_Class.setSelectedIndex(0);
            comboBox_Lang.setSelectedIndex(0);
            comboBox_Type.setSelectedIndex(0);
            textField_Director.setText("");
            textField_Leng.setText("");
            textField_Price.setText("");
            comboBox_House.setSelectedIndex(0);
            comboBox_hours.setSelectedIndex(0);
            comboBox_minutes.setSelectedIndex(0);
            comboBox_apm.setSelectedIndex(0);
            textField_Cast.setText("");
        } else {
            textField_MovieName.setText(m.getName());
            // shall not edit ID to make infinite loop
            ((UtilDateModel) datePicker.getModel()).setValue(m.getDate());
            comboBox_Class.setSelectedItem(m.getTypeClass());
            comboBox_Lang.setSelectedItem(m.getLanguage());
            comboBox_Type.setSelectedItem(m.getType());
            textField_Director.setText(m.getDirector());
            textField_Leng.setText(m.getLength() + "");
            textField_Price.setText(m.getPrice() + "");
            comboBox_House.setSelectedItem(m.getLocation());
            comboBox_hours.setSelectedIndex(m.getStartHour());
            comboBox_minutes.setSelectedIndex(m.getStartMinute() / 15);
            textField_Cast.setText(m.getCast());
        }
    }

    private Movie fetchMovieFromFields() throws NumberFormatException {
        int id;
        if (editMovie) // HACK
            id = (int) comboBox_ID.getSelectedItem();
        else
            id = Integer.MIN_VALUE;
        String name = textField_MovieName.getText();
        String type = (String) comboBox_Type.getSelectedItem();
        Date date = (Date) datePicker.getModel().getValue();
        String typeClass = (String) comboBox_Class.getSelectedItem();
        String language = (String) comboBox_Lang.getSelectedItem();
        String director = textField_Director.getText();
        int length = Integer.parseInt(textField_Leng.getText());
        int price = Integer.parseInt(textField_Price.getText());
        String location = (String) comboBox_House.getSelectedItem();
        int startHour = Integer.parseInt((String) comboBox_hours.getSelectedItem());
        int startMinute = Integer.parseInt(((String) comboBox_minutes.getSelectedItem()).substring(0, 2));
        String cast = textField_Cast.getText();

        return new Movie(id, name, type, date, typeClass, language, length, director, cast, location, price, startHour, startMinute);
    }
}
