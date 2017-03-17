package UI.search;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import UI.Menu.member_menu;
import UI.Menu.topbar;
import UI.Register.JTextFieldLimit;

public class searchMovie {

	private JFrame frame;
	private JPanel topbar;
	private JLabel lblLoginer, lblMovieName, lblHouse, lblTime;
	topbar tb;
	private JButton btnBack, btnSubmit, btnReset;
	private JSeparator separator;
	private JComboBox comboBox_MovieName, comboBox_House, comboBox_Time;

	/**
	 * Create the application.
	 */
	public searchMovie() {
		tb = new topbar();
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("XXX Cinema - Search Movie");
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

		btnBack = new JButton("Back");
		btnBack.setBounds(10, 52, 87, 23);
		btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new BackAction());

		lblMovieName = new JLabel("Movie Name");
		lblMovieName.setBounds(66, 72, 138, 35);
		lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblMovieName);

		comboBox_MovieName = new JComboBox();
		comboBox_MovieName.setBounds(109, 117, 307, 40);
		comboBox_MovieName.addItem("Movie 1");
		comboBox_MovieName.addItem("Movie 2");
		comboBox_MovieName.addItem("Movie 3");
		frame.getContentPane().add(comboBox_MovieName);

		lblHouse = new JLabel("House");
		lblHouse.setBounds(66, 167, 138, 35);
		lblHouse.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblHouse);

		comboBox_House = new JComboBox();
		comboBox_House.setBounds(109, 212, 307, 40);
		comboBox_House.addItem("A");
		comboBox_House.addItem("B");
		comboBox_House.addItem("C");
		frame.getContentPane().add(comboBox_House);

		lblTime = new JLabel("Show Time");
		lblTime.setBounds(66, 262, 138, 35);
		lblTime.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblTime);

		comboBox_Time = new JComboBox();
		comboBox_Time.setBounds(109, 307, 307, 40);
		comboBox_Time.addItem("time 1");
		comboBox_Time.addItem("time 2");
		comboBox_Time.addItem("time 3");
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

	private class BackAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			new member_menu(tb.id, tb.FullName);
			frame.dispose();

		}
	}

	private class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String movie_id = " ";
			String movie_name = "Name";
			new searchResult(movie_id, movie_name);
			frame.dispose();
		}
	}

	private class ResetAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			comboBox_MovieName.setSelectedIndex(0);
			comboBox_House.setSelectedIndex(0);
			comboBox_Time.setSelectedIndex(0);

		}
	}

}
