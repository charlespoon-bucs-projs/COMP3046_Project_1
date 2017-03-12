package UI.Login;

import javax.swing.JFrame;

public class loginFrame extends JFrame {

	public loginFrame(){
		setTitle("Movie Ticket System - UI.Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
		add(new loginPanel());
		
	}
}
