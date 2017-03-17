package org.comp3046.it9.UI.Login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginPanel extends JPanel {

	private JTextField username;
	private JLabel label_username,label_password;
	private JPasswordField password;
	private JPanel inputPanel,usernamePanel,passwordPanel,buttonPanel;
	private JButton btn_cancel,btn_login;
	public loginPanel(){
		setLayout(new BorderLayout());
		inputPanel = new JPanel(new GridLayout(2,1));

		// for user name;
		usernamePanel = new JPanel(new FlowLayout());
		
		label_username = new JLabel("User Name ");
		label_username.setPreferredSize(new Dimension(90,40));
		label_username.setLabelFor(username);
		
		username = new JTextField();
		username.setColumns(18);
		//add to panel
		usernamePanel.add(label_username);
		usernamePanel.add(username);
		
		
		
		// for password;
		passwordPanel = new JPanel(new FlowLayout());
		
		label_password = new JLabel("Password  ");
		label_password.setPreferredSize(new Dimension(90,40));
		label_password.setLabelFor(password);
		
		password = new  JPasswordField();
		password.setColumns(18);
		
		passwordPanel.add(label_password);
		passwordPanel.add(password);
		
		inputPanel.add(usernamePanel);
		inputPanel.add(passwordPanel);

		//set cancel button and login button
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,30));
	
		btn_cancel = new JButton("Cancel");
		btn_login = new JButton("UI/Login");
		addButton("Cancel",new CancelAction(),buttonPanel);
		addButton("UI/Login",new LoginAction(),buttonPanel);
		
	
		add(inputPanel,BorderLayout.NORTH);
		add(buttonPanel,BorderLayout.SOUTH);
		
	
		
	}
	
	
	  private void addButton(String label, ActionListener listener,JPanel panel)
	   {
	      JButton button = new JButton(label);
	      button.addActionListener(listener);
	      panel.add(button);
	   }
	  
	  
	  private class LoginAction implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	    	// todo login function
	      }
	   }

	  private class CancelAction implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	    	 username.setText("");
	    	 password.setText("");
	      }
	   }
	
	
}
