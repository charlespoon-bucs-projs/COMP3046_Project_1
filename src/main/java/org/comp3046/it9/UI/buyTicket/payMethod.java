package org.comp3046.it9.UI.buyTicket;

import org.comp3046.it9.UI.Menu.member_menu;
import org.comp3046.it9.UI.Menu.topbar;
import org.comp3046.it9.UI.Register.JTextFieldLimit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payMethod {

	private JFrame frame;
	String[] selectedSeat;
	private JPanel topbar;
	String movie_id, movie_name;
	private JLabel lblLoginer, lblMovieName, lblSelectdSeat, lblpatmentMethod, lblCardNo, lblExpiryDate, lblSecurity,
			lblnew;
	topbar tb;
	private JButton btnCancel, btnPrint;
	private JSeparator separator;
	JRadioButton rdbtnCash, rdbtnCreditCard;
	private JTextField textField_cardNo, textField_YY, textField_MM;
	private JTextField textField_Security;

	public payMethod(String[] selectedSeat, String movieName, String movieId) {
		this.movie_id = movie_id;
		this.movie_name = movie_name;
		this.selectedSeat = selectedSeat;
		tb = new topbar();
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		frame.setBounds(100, 100, 524, 342);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("XXX Cinema - Select Payment Method");

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

		lblMovieName = new JLabel(movie_name + "ih");
		lblMovieName.setBounds(122, 50, 280, 40);
		lblMovieName.setHorizontalAlignment(JLabel.CENTER);
		lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 26));
		frame.getContentPane().add(lblMovieName);

		lblSelectdSeat = new JLabel("Selected Seat: ");
		lblSelectdSeat.setBounds(122, 80, 280, 40);
		lblSelectdSeat.setHorizontalAlignment(JLabel.CENTER);
		lblSelectdSeat.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 24));
		frame.getContentPane().add(lblSelectdSeat);

		for (int i = 0; i < selectedSeat.length; i++) {
			lblSelectdSeat.setText(lblSelectdSeat.getText() + selectedSeat[i] + " ");

		}

		separator = new JSeparator();
		separator.setBounds(10, 120, 534, 2);
		frame.getContentPane().add(separator);

		lblpatmentMethod = new JLabel("Payment Method");
		lblpatmentMethod.setBounds(20, 130, 177, 40);

		lblpatmentMethod.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblpatmentMethod);

		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(205, 130, 107, 40);
		rdbtnCash.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		rdbtnCash.addActionListener(new selectPayMethodAction());
		rdbtnCash.setSelected(true);
		frame.getContentPane().add(rdbtnCash);

		rdbtnCreditCard = new JRadioButton("Credit Card");
		rdbtnCreditCard.setBounds(313, 130, 143, 40);
		rdbtnCreditCard.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		rdbtnCreditCard.addActionListener(new selectPayMethodAction());
		frame.getContentPane().add(rdbtnCreditCard);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCreditCard);
		group.add(rdbtnCash);

		lblCardNo = new JLabel("Card No.");
		lblCardNo.setBounds(20, 160, 177, 40);
		lblCardNo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblCardNo);

		textField_cardNo = new JTextField();
		textField_cardNo.setBounds(174, 173, 138, 21);
		frame.getContentPane().add(textField_cardNo);
		textField_cardNo.setDocument(new JTextFieldLimit(16));
		textField_cardNo.setColumns(16);

		lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setBounds(20, 190, 177, 40);
		lblExpiryDate.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblExpiryDate);

		textField_YY = new JTextField();
		textField_YY.setBounds(174, 204, 33, 21);
		frame.getContentPane().add(textField_YY);
		textField_YY.setDocument(new JTextFieldLimit(2));
		textField_YY.setColumns(2);

		lblnew = new JLabel("/");
		lblnew.setBounds(208, 204, 10, 21);
		lblnew.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblnew);

		textField_MM = new JTextField();
		textField_MM.setBounds(220, 204, 33, 21);
		frame.getContentPane().add(textField_MM);
		textField_MM.setDocument(new JTextFieldLimit(2));
		textField_MM.setColumns(2);

		lblSecurity = new JLabel("Security Code");
		lblSecurity.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblSecurity.setBounds(20, 220, 177, 40);
		frame.getContentPane().add(lblSecurity);

		textField_Security = new JTextField();
		textField_Security.setColumns(3);
		textField_Security.setDocument(new JTextFieldLimit(3));
		textField_Security.setBounds(174, 233, 138, 21);
		frame.getContentPane().add(textField_Security);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(393, 266, 87, 23);
		btnCancel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnCancel); //
		btnCancel.addActionListener(new CancelAction());

		btnPrint = new JButton("Process to payment");
		btnPrint.setBounds(208, 266, 143, 23);
		btnPrint.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnPrint); //
		btnPrint.addActionListener(new PrintAction());
	}

	private class PrintAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new buyTicket(selectedSeat, movie_name);
			frame.dispose();

		}
	}

	private class selectPayMethodAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			JRadioButton btn = (JRadioButton) event.getSource();
			if (btn.getText().equals("Cash")) {

				textField_cardNo.setEditable(false);
				textField_YY.setEditable(false);
				textField_MM.setEditable(false);
				textField_Security.setEditable(false);
				lblCardNo.setEnabled(false);
				lblExpiryDate.setEnabled(false);
				lblSecurity.setEnabled(false);

			} else {
				textField_cardNo.setEditable(true);
				textField_YY.setEditable(true);
				textField_MM.setEditable(true);
				textField_Security.setEditable(true);
				lblCardNo.setEnabled(true);
				lblExpiryDate.setEnabled(true);
				lblSecurity.setEnabled(true);

			}

		}
	}

	private class CancelAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			new member_menu(tb.id, tb.FullName);
			frame.dispose();
		}
	}

}
