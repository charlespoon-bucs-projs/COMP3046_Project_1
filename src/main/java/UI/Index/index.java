package UI.Index;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import UI.ForgotPassword.forgot_password;
import UI.Menu.member_menu;
import UI.Menu.staff_menu;
import UI.Register.register;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class index {

	private JFrame frame;
	private JTextField textField_username;
	private JPasswordField passwordField;
	private JButton btnForgotPassword, btnRegister, btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					index window = new index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public index() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("XXX Cinema - Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(true);
		frame.setVisible(true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JLabel lblXxxCinema = new JLabel("XXX Cinema");
		lblXxxCinema.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
		lblXxxCinema.setBounds(147, 10, 200, 49);
		frame.getContentPane().add(lblXxxCinema);

		JLabel lblId = new JLabel("Username");
		lblId.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		lblId.setBounds(85, 69, 89, 35);
		frame.getContentPane().add(lblId);

		textField_username = new JTextField();
		textField_username.setFont(new Font("�s�ө���", Font.PLAIN, 18));
		textField_username.setBounds(165, 78, 135, 21);
		frame.getContentPane().add(textField_username);
		textField_username.setColumns(15);

		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		lblNewLabel.setBounds(85, 114, 89, 35);
		frame.getContentPane().add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(165, 123, 135, 21);
		frame.getContentPane().add(passwordField);

		btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnForgotPassword.setBounds(10, 209, 135, 23);
		btnForgotPassword.addActionListener(new ToForgotPasswordAction());
		frame.getContentPane().add(btnForgotPassword);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(198, 209, 87, 23);
		btnRegister.addActionListener(new ToRegisterAction());
		btnRegister.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnRegister);

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnLogin.setBounds(306, 210, 87, 23);
		btnLogin.addActionListener(new ToMenuAction());
		frame.getContentPane().add(btnLogin);
	}

	private class ToForgotPasswordAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new forgot_password();
		}
	}

	private class ToRegisterAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new register();
		}
	}

	private class ToMenuAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (!(textField_username.getText().equals("")) && !(passwordField.getText().equals(""))) {

				if ((textField_username.getText().equals("mem")) && (passwordField.getText().equals("123"))) {
					frame.setVisible(false);
					new member_menu("id", "BenLi");
				}
				if ((textField_username.getText().equals("staff")) && (passwordField.getText().equals("123"))) {
					frame.setVisible(false);
					new staff_menu("id", "BenLi");
				}

			} else {
				JOptionPane.showMessageDialog(frame, "Username and Password can not null");
			}
			// textField_username.setText("");
			// passwordField.setText("");

		}
	}
}
