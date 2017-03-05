package Login;

import javax.swing.JFrame;

import Index.indexPanel;

public class loginFrame extends JFrame {

	public loginFrame(){
		setTitle("Movie Ticket System - Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
		add(new loginPanel());
		
	}
}
