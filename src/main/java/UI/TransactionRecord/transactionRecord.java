package UI.TransactionRecord;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Menu.member_menu;
import Menu.staff_menu;
import Menu.topbar;
import Register.JTextFieldLimit;
import javax.swing.JTable;
import javax.swing.JMenu;

public class transactionRecord {

	private JFrame frame;

	private JPanel topbar;
	private JLabel lblLoginer, lblMobile;
	topbar tb;
	private JButton btnBack, btnSearch;
	private JTextField textField_Mobile;
	private JSeparator separator;
	boolean isStaff;
	private JTable MovieTable;
	private JTable table;

	public transactionRecord(boolean isStaff) {
		tb = new topbar();

		this.isStaff = isStaff;
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
		if (isStaff)
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

		DefaultTableModel model = new DefaultTableModel();

		MovieTable = new JTable();
		MovieTable.setBounds(0, 0, 478, 233);

		model.addColumn("Movie Name");
		model.addColumn("Start Time");
		model.addColumn("Language");
		model.addColumn("Length");
		MovieTable.setModel(model);
		model.addRow(new Object[] { "123", "11:15 pm", "Chinese", "183 mins" });

		JScrollPane scrollPane = new JScrollPane(MovieTable);
		scrollPane.setBounds(10, 102, 488, 233);
		frame.getContentPane().add(scrollPane);

	}

	private class BackAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (isStaff)
				new staff_menu(tb.id, tb.FullName);
			else
				new member_menu(tb.id, tb.FullName);
			frame.dispose();

		}
	}

	private class SearchAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}
}
