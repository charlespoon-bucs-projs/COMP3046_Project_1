package org.comp3046.it9.UI.MovieAction;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;

import org.comp3046.it9.UI.Menu.*;
import org.comp3046.it9.UI.Register.JTextFieldLimit;

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

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SpringLayout;
import javax.swing.JFormattedTextField;

public class movieSetting {

	private JFrame frame;
	private JPanel topbar;
	private JLabel lblLoginer, lblMovieImage, lblMovieName, lblDate, lblType, lblClass, lblLang, lblLeng, lblDirector,
			lblCast, lblHouse, lblTime, lblPrice;
	topbar tb;
	private JButton btnBack, btnSubmit, btnReset, btnDelete;
	private JSeparator separator;

	private JTextField textField_MovieName, textField_Director, textField_Price, textField_Leng, textField_Cast;
	boolean isAdd;
	private JComboBox comboBox_Type, comboBox_Class, comboBox_Lang, comboBox_House, comboBox_hours, comboBox_apm,
			comboBox_minutes, comboBox_ID;
	MaskFormatter mf1;
	UtilDateModel model;
	private JLabel lblId;

	public movieSetting(boolean isAdd) {
		this.isAdd = isAdd;
		initialize();
		tb.clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		tb = new topbar();
		frame.setBounds(100, 100, 524, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		topbar = new JPanel();
		topbar.setLayout(null);
		topbar.setBounds(0, 0, 504, 40);

		lblLoginer = new JLabel("Login as ");
		lblLoginer.setText(lblLoginer.getText() + tb.FullName + "-Staff");
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
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

		datePicker.setBounds(212, 149, 138, 28);
		datePicker.addActionListener(new CheckDateAction());
		frame.getContentPane().add(datePicker);

		lblClass = new JLabel("Class");
		lblClass.setBounds(360, 149, 62, 35);
		lblClass.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblClass);

		comboBox_Class = new JComboBox();
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

		comboBox_Type = new JComboBox();
		comboBox_Type.setBounds(411, 194, 87, 28);

		comboBox_Type.addItem("Action");
		comboBox_Type.addItem("Adventure");
		comboBox_Type.addItem("Romance");
		frame.getContentPane().add(comboBox_Type);

		lblLang = new JLabel("Language");
		lblLang.setBounds(168, 194, 87, 35);
		lblLang.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		frame.getContentPane().add(lblLang);

		comboBox_Lang = new JComboBox();
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

		comboBox_House = new JComboBox();
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

		comboBox_hours = new JComboBox();
		comboBox_hours.setBounds(262, 314, 62, 28);
		comboBox_hours.addItem("00");
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

		comboBox_apm = new JComboBox();
		comboBox_apm.setBounds(452, 314, 46, 28);
		comboBox_apm.addItem("am");
		comboBox_apm.addItem("pm");
		frame.getContentPane().add(comboBox_apm);

		comboBox_minutes = new JComboBox();
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

		comboBox_ID = new JComboBox();
		comboBox_ID.setBounds(235, 85, 263, 31);
		frame.getContentPane().add(comboBox_ID);

		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		lblId.setBounds(168, 85, 62, 35);
		frame.getContentPane().add(lblId);

		if (isAdd) // check the staff want to add or modify
			frame.setTitle("XXX Cinema - Add New Movie");
		else {
			frame.setTitle("XXX Cinema - Modify Movie");
			textField_MovieName.setText("ACB Movie");

		}

	}

	private class BackAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			new staff_menu(tb.id, tb.FullName);
			frame.dispose();

		}
	}

	private class DeleteAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

	private class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

		}
	}

	private class ResetAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

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
}
