package org.comp3046.it9.UI.buyTicket;

import org.comp3046.it9.UI.Menu.member_menu;
import org.comp3046.it9.UI.Menu.topbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buyTicket {

	private JFrame frame;

	String[] selectedSeat;
	String movieName;
	private JPanel topbar;
	private JLabel lblLoginer, lblID, lblMovieName, lblTime, lblLocation, lblSeat, lblTotal;
	topbar tb;
	private JButton btnBack, btnPrint;
	private JSeparator separator;
	private JLabel lblNewLabel;

	/**
	 * Create the application.
	 */
	public buyTicket(String[] selectedSeat, String movieName) {
		this.selectedSeat = selectedSeat;
		tb = new topbar();
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		frame.setBounds(100, 100, 524, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("XXX Cinema - buy Ticket");

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

		lblNewLabel = new JLabel("Thank you for putchase");
		lblNewLabel.setBounds(122, 50, 280, 40);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 26));
		frame.getContentPane().add(lblNewLabel);

		lblID = new JLabel("ID");
		lblID.setBounds(122, 90, 300, 28);
		lblID.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(lblID);

		lblMovieName = new JLabel("Movie Name");
		lblMovieName.setBounds(122, 130, 300, 28);
		lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(lblMovieName);

		lblTime = new JLabel("Time");
		lblTime.setBounds(122, 170, 300, 28);
		lblTime.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(lblTime);

		lblLocation = new JLabel("Location");
		lblLocation.setBounds(122, 210, 300, 28);
		lblLocation.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(lblLocation);

		lblSeat = new JLabel("Seat");
		lblSeat.setBounds(122, 250, 300, 28);
		lblSeat.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(lblSeat);

		for (int i = 0; i < selectedSeat.length; i++) {
			lblSeat.setText(lblSeat.getText() + " " + selectedSeat[i]);
		}

		btnBack = new JButton("Back");
		btnBack.setBounds(393, 315, 87, 23);
		btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new BackAction());

		btnPrint = new JButton("Print Receipt");
		btnPrint.setBounds(179, 315, 146, 23);
		btnPrint.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnPrint);
		btnPrint.addActionListener(new PrintAction());

	}

	private class PrintAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			new member_menu(tb.id, tb.FullName);
			frame.dispose();

		}
	}

	private class BackAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			new member_menu(tb.id, tb.FullName);
			frame.dispose();

		}
	}
}
