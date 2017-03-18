package org.comp3046.it9.UI.TransactionRecord;

import org.comp3046.it9.Database.Sqlite;
import org.comp3046.it9.Database.TransactionsDb;
import org.comp3046.it9.Entity.Movie;
import org.comp3046.it9.Entity.Transaction;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.StaffMenu;
import org.comp3046.it9.UI.Menu.TopBar;
import org.comp3046.it9.UI.Register.JTextFieldLimit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;

public class TransactionRecord {
    private final MemberMenu memberMenu;
    private final StaffMenu staffMenu;

    private TopBar tb;
    private JFrame frame;
    private JPanel topbar;
    private JLabel lblLoginer, lblMobile;
    private JButton btnBack, btnSearch;
    private JTextField textField_Mobile;
    private JSeparator separator;
    private JTable movieTable;
    private DefaultTableModel movieTableModel;
    private JTable table;

    public TransactionRecord(StaffMenu staffMenu) {
        this.memberMenu = null;
        this.staffMenu = staffMenu;
        _ctor();
    }

    public TransactionRecord(MemberMenu memberMenu) {
        this.memberMenu = memberMenu;
        this.staffMenu = null;
        _ctor();
    }

    private void _ctor() {
        tb = new TopBar();
        initialize();
        tb.clock();

        // async
        if (memberMenu != null) {
            searchTransactionDb(trDb -> trDb.getTransactionsByMemberId(memberMenu.getCustomer().getUid()));
        }
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
        int id = 0;
        String name = "";
        if (staffMenu != null) {
            id = staffMenu.getStaff().getId();
            name = staffMenu.getStaff().getName();
            lblLoginer.setText(lblLoginer.getText() + name + "-Staff");
        } else {
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

        frame.getContentPane().add(tb.topbarLayout(topbar, id + "", name));

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

        if (memberMenu != null) {
            lblMobile.setVisible(false);
            textField_Mobile.setEnabled(false);
            textField_Mobile.setVisible(false);
            btnSearch.setEnabled(false);
            btnSearch.setVisible(false);
        }

        movieTable = new JTable();
        movieTable.setBounds(0, 0, 478, 233);
        this.movieTableModel = generateTableModel();
        movieTable.setModel(this.movieTableModel);

        JScrollPane scrollPane = new JScrollPane(movieTable);
        scrollPane.setBounds(10, 102, 488, 233);
        frame.getContentPane().add(scrollPane);

    }

    private class BackAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (staffMenu != null)
                staffMenu.setVisible(true);
            else
                memberMenu.setVisible(true);
            frame.setVisible(false);
            frame.dispose();
        }
    }

    private class SearchAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            tableModelClearRecords();
            /*
            search Transactions by customer mobile number
            WITH IDENTITY OF staff
            */
            int mobile;
            try {
                mobile = Integer.parseInt(textField_Mobile.getText());
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(null, "Mobile number contains illegal characters.",
                        "Transaction Record Search", JOptionPane.WARNING_MESSAGE);
                return;
            }
            searchTransactionDb(trDb -> trDb.getTransactionsByMemberMobile(mobile));
        }
    }

    private DefaultTableModel generateTableModel() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("#");
        model.addColumn("Movie name");
        model.addColumn("Seat No.");
        model.addColumn("Total $");
        model.addColumn("Cancelled");

        return model;
    }

    private void tableModelAddRecord(int tid, String movieName, String seats, int totalPrice, boolean cancelled) {
        this.movieTableModel.addRow(new Object[]{
                tid + "",
                movieName,
                seats,
                totalPrice + "",
                cancelled ? "Yes" : ""
        });
    }

    private void tableModelAddRecordByEntities(Transaction t) {
        String movieName = "?";
        Movie m = t.getMovie();
        if (m != null)
            movieName = m.getName();
        this.tableModelAddRecord(t.getId(), movieName, t.getSeat(), t.getTotal(), t.isCancelled());
    }

    private void tableModelClearRecords() {
        this.movieTableModel.getDataVector().removeAllElements();
        this.movieTableModel.fireTableDataChanged();
    }

    private void searchTransactionDb(Function<TransactionsDb, Map<Integer, Transaction>> opr) {
        new Thread(() -> {
            try (Sqlite sqlite = new Sqlite()) {
                TransactionsDb tr = new TransactionsDb(sqlite);
                opr.apply(tr).forEach((k, t) -> tableModelAddRecordByEntities(t));
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
}
