package Index;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;

import Login.loginFrame;

public class indexPanel extends JPanel{
	private JPanel loginPanel,controlPanel;
	 public indexPanel()
	   {
		 
		 setLayout(new BorderLayout());
		 //set for login (top bar)
		 loginPanel= new JPanel();
		 addButton("Login", new ChangeFrameAction(),loginPanel);
		 add(loginPanel, BorderLayout.LINE_END);
		 
	   
	   
	   }
	   
	   private void addButton(String label, ActionListener listener,JPanel panel)
	   {
	      JButton button = new JButton(label);
	      button.addActionListener(listener);
	      panel.add(button);
	   }
	   
	   private class ChangeFrameAction implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	    	 new loginFrame();
	      }
	   }

}
