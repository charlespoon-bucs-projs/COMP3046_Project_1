package UI.ForgotPassword;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class forgot_password {

	private JFrame frame;
	private JTextField textField_email;
	private JButton btnSubmit;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { forgot_password window = new
	 * forgot_password(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 */
	public forgot_password() {
		frame = new JFrame();
		frame.setBounds(100, 100, 402, 202);
		frame.setTitle("XXX Cinema - Forgot Password");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(51, 30, 46, 15);
		frame.getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));

		textField_email = new JTextField();
		textField_email.setBounds(124, 27, 200, 18);
		textField_email.setFont(new Font("·s²Ó©úÅé", Font.PLAIN, 18));
		frame.getContentPane().add(textField_email);
		textField_email.setColumns(30);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(51, 96, 87, 23);
		btnSubmit.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnSubmit);

		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnReset.setBounds(202, 96, 87, 23);
		btnReset.addActionListener(new ResetAction());
		frame.getContentPane().add(btnReset);
	}

	private class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// todo
		}
	}

	private class ResetAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			textField_email.setText("");
		}
	}

}
