package org.comp3046.it9.UI.Menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.comp3046.it9.UI.MovieAction.movieSetting;
import org.comp3046.it9.UI.TransactionRecord.transactionRecord;
import org.comp3046.it9.UI.Index.index;
import org.comp3046.it9.UI.member.MemberSetting;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class staff_menu {

	private JFrame frame;
	JLabel lblLoginer;
	private JSeparator separator;
	private JButton btnAddMember, btnAddMovie, btnModifyMember, btnModifyMovie, btnTransactionRecord;
	private JButton btnLogout;
	private String id, FullName;
	private JPanel topbar;
	topbar tb;

	/**
	 * Create the application.
	 */
	public staff_menu(String id, String FullName) {
		this.id = id;
		this.FullName = FullName;
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("XXX Cinema - Menu");
		frame.getContentPane().setLayout(null);
		tb = new topbar();
		initialize();
		tb.clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setVisible(true);

		topbar = new JPanel();
		topbar.setLayout(null);
		topbar.setBounds(0, 0, 504, 40);

		lblLoginer = new JLabel("Login as ");
		lblLoginer.setText(lblLoginer.getText() + FullName + "-Staff");
		lblLoginer.setBounds(308, 10, 200, 15);

		lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		topbar.add(lblLoginer);

		separator = new JSeparator();
		separator.setBounds(10, 35, 534, 2);
		topbar.add(separator);

		frame.getContentPane().add(tb.topbarLayout(topbar, id, FullName));

		btnAddMember = new JButton("Add Member");
		btnAddMember.setBounds(20, 47, 215, 93);
		btnAddMember.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		btnAddMember.addActionListener(new AddMemberAction());
		frame.getContentPane().add(btnAddMember);

		btnAddMovie = new JButton("Add Movie");
		btnAddMovie.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		btnAddMovie.setBounds(272, 47, 215, 93);
		btnAddMovie.addActionListener(new AddMovieAction());
		frame.getContentPane().add(btnAddMovie);

		btnModifyMember = new JButton("Modify Member");
		btnModifyMember.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		btnModifyMember.setBounds(20, 163, 215, 93);
		btnModifyMember.addActionListener(new ModifyMemberAction());
		frame.getContentPane().add(btnModifyMember);

		btnModifyMovie = new JButton("Modify Movie");
		btnModifyMovie.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		btnModifyMovie.setBounds(272, 163, 215, 93);
		btnModifyMovie.addActionListener(new ModifyMovieAction());
		frame.getContentPane().add(btnModifyMovie);

		btnTransactionRecord = new JButton("Transaction Record");
		btnTransactionRecord.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		btnTransactionRecord.addActionListener(new TransactionRecordAction());
		btnTransactionRecord.setBounds(129, 276, 266, 93);
		frame.getContentPane().add(btnTransactionRecord);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(218, 385, 88, 23);
		btnLogout.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnLogout.addActionListener(new LogoutAction());
		frame.getContentPane().add(btnLogout);

	}

	private class AddMemberAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new MemberSetting(true, true);
			frame.setVisible(false);
		}
	}

	private class AddMovieAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new movieSetting(true);
			frame.dispose();
		}
	}

	private class ModifyMemberAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			String member_id = JOptionPane.showInputDialog(frame, "Enter the Member ID");

			// check the member id is true

			new MemberSetting(false, true, member_id);

			frame.setVisible(false);

		}
	}

	private class ModifyMovieAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String movie_id = JOptionPane.showInputDialog(frame, "Enter the Movie ID");

			// check the movie id is true

			new movieSetting(false);

			frame.dispose();
		}
	}

	private class TransactionRecordAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new transactionRecord(true);
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
