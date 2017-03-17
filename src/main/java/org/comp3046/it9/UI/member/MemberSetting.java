package org.comp3046.it9.UI.member;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import UI.Menu.member_menu;
import UI.Menu.staff_menu;
import UI.Menu.topbar;
import UI.Register.JTextFieldLimit;
import javax.swing.JRadioButton;

public class MemberSetting {

	private JFrame frame;
	boolean isAdd, isStaff;
	String isModifiedMember_id;

	private JLabel lblLoginer, lblFullName, lblSalutation, lblUsername, lblBirthday, lblMobileNumber, lblEmail,
			lblPassword;
	private JTextField textField_FullName, textField_Username, textField_Mobile_Number, textField_Email,
			textField_Password;
	private JComboBox comboBox_Salutation;
	private JButton btnSubmit, btnReset, btnBack;
	private JPanel topbar;
	JRadioButton rdbtnStaff;
	topbar tp;
	private JSeparator separator;
	MaskFormatter mf1;
	UtilDateModel model;
	JDatePickerImpl datePicker;

	/**
	 * Launch the application.
	 */

	public MemberSetting(boolean isAdd, boolean isStaff) { // for add new member
		this.isAdd = isAdd;
		this.isStaff = isStaff;

		initialize();
		tp.clock();
	}

	public MemberSetting(boolean isAdd, boolean isStaff, String isModifiedMember_id) { // for
																						// add
																						// new
																						// member
		this.isModifiedMember_id = isModifiedMember_id;
		this.isAdd = isAdd;
		this.isStaff = isStaff;

		initialize();
		tp.clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		if (isAdd)
			frame.setTitle("XXX Cinema - Add New Member");
		else
			frame.setTitle("XXX Cinema - Modify Member");
		frame.setBounds(100, 100, 524, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tp = new topbar();

		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		topbar = new JPanel();
		topbar.setLayout(null);
		topbar.setBounds(0, 0, 504, 40);

		lblLoginer = new JLabel("Login as ");
		if (isStaff)
			lblLoginer.setText(lblLoginer.getText() + tp.FullName + "-Staff");
		else
			lblLoginer.setText(lblLoginer.getText() + tp.FullName + "-Member");

		lblLoginer.setBounds(304, 10, 200, 15);

		lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		topbar.add(lblLoginer);

		separator = new JSeparator();
		separator.setBounds(10, 35, 534, 2);
		topbar.add(separator);

		frame.getContentPane().add(tp.topbarLayout(topbar, tp.id, tp.FullName));

		btnBack = new JButton("Back");
		btnBack.setBounds(10, 52, 87, 23);
		btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new BackAction());

		lblFullName = new JLabel("*Full Name");
		lblFullName.setBounds(66, 72, 138, 35);
		lblFullName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblFullName);

		textField_FullName = new JTextField();
		textField_FullName.setBounds(242, 81, 138, 21);
		textField_FullName.setDocument(new JTextFieldLimit(15));
		frame.getContentPane().add(textField_FullName);
		textField_FullName.setColumns(10);

		lblSalutation = new JLabel("*Salutation");
		lblSalutation.setBounds(66, 107, 138, 35);
		lblSalutation.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblSalutation);

		comboBox_Salutation = new JComboBox();
		comboBox_Salutation.setBounds(310, 112, 70, 21);
		comboBox_Salutation.addItem("Mr.");
		comboBox_Salutation.addItem("Ms.");
		comboBox_Salutation.addItem("Mrs.");
		frame.getContentPane().add(comboBox_Salutation);

		lblUsername = new JLabel("*Username");
		lblUsername.setBounds(66, 142, 114, 35);
		lblUsername.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblUsername);

		textField_Username = new JTextField();
		textField_Username.setBounds(242, 147, 138, 21);
		textField_Username.setDocument(new JTextFieldLimit(15));
		frame.getContentPane().add(textField_Username);
		textField_Username.setColumns(15);

		lblPassword = new JLabel("*Password");
		lblPassword.setBounds(66, 177, 114, 35);
		lblPassword.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblPassword);

		textField_Password = new JTextField();
		textField_Password.setBounds(242, 182, 138, 21);
		frame.getContentPane().add(textField_Password);
		textField_Password.setDocument(new JTextFieldLimit(20));
		textField_Password.setColumns(20);

		lblMobileNumber = new JLabel("*Mobile Number");
		lblMobileNumber.setBounds(66, 212, 114, 35);
		lblMobileNumber.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblMobileNumber);

		textField_Mobile_Number = new JTextField();
		textField_Mobile_Number.setBounds(242, 217, 138, 21);
		frame.getContentPane().add(textField_Mobile_Number);
		textField_Mobile_Number.setDocument(new JTextFieldLimit(8));
		textField_Mobile_Number.setColumns(8);

		lblEmail = new JLabel("*Email");
		lblEmail.setBounds(66, 247, 114, 35);
		lblEmail.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblEmail);

		textField_Email = new JTextField();
		textField_Email.setBounds(242, 252, 138, 21);
		frame.getContentPane().add(textField_Email);
		textField_Email.setDocument(new JTextFieldLimit(20));
		textField_Email.setColumns(20);

		lblBirthday = new

		JLabel("*Birthday");
		lblBirthday.setBounds(66, 282, 114, 35);
		lblBirthday.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblBirthday);

		model = new UtilDateModel();

		model.setSelected(true);
		model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(242, 287, 138, 28);
		datePicker.addActionListener(new CheckDateAction());
		frame.getContentPane().add(datePicker);

		rdbtnStaff = new JRadioButton("Staff");
		rdbtnStaff.setBounds(66, 323, 107, 23);
		rdbtnStaff.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(rdbtnStaff);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(276, 366, 87, 23);
		btnSubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnSubmit); //
		btnSubmit.addActionListener(new SubmitAction());

		btnReset = new JButton("Reset");
		btnReset.setBounds(397, 366, 87, 23);
		btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnReset); //

		JButton button = new JButton("Delete Member");
		button.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		button.setBounds(27, 367, 122, 23);
		frame.getContentPane().add(button);

		if (isAdd == false && isStaff == true) { // staff modify mem

			textField_FullName.setText("BenLi");
			textField_FullName.setEditable(true);
			lblFullName.setEnabled(true);

			comboBox_Salutation.setSelectedIndex(0);
			lblSalutation.setEnabled(false);

			textField_Username.setText("bbqwe");
			textField_Username.setEditable(true);
			lblUsername.setEnabled(true);

			textField_Password.setText("123445556");

			textField_Mobile_Number.setText("12345676");
			textField_Mobile_Number.setEditable(false);
			lblMobileNumber.setEnabled(false);

			textField_Email.setText("dsfgn@sdgfhj");
			textField_Email.setEditable(false);
			lblEmail.setEnabled(false);

			lblBirthday.setEnabled(false);

			datePicker.getComponent(1).setEnabled(false);

		} else if (isStaff) {
			rdbtnStaff.setVisible(true);
			rdbtnStaff.setSelected(true);
			rdbtnStaff.setEnabled(false);

		} else {
			rdbtnStaff.setVisible(false);

		}

	}

	private class BackAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (isStaff) {
				new staff_menu(tp.id, tp.FullName);
				frame.dispose();
			} else {
				new member_menu(tp.id, tp.FullName);
				frame.dispose();
			}

		}
	}

	private class ResetAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

	private class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

	private class CheckDateAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (model.getYear() == Calendar.getInstance().get(Calendar.YEAR)) {
				if (model.getMonth() == Calendar.getInstance().get(Calendar.MONTH)) {
					if (model.getDay() >= Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1) {
						model.setDate(Calendar.getInstance().get(Calendar.YEAR),
								Calendar.getInstance().get(Calendar.MONTH),
								Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);

					}
				} else {
					if (model.getMonth() > Calendar.getInstance().get(Calendar.MONTH)) {
						model.setDate(Calendar.getInstance().get(Calendar.YEAR),
								Calendar.getInstance().get(Calendar.MONTH),
								Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
					}

				}

			} else {
				if (model.getYear() >= Calendar.getInstance().get(Calendar.YEAR)) {
					model.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),
							Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
				}

			}
		}
	}
}
