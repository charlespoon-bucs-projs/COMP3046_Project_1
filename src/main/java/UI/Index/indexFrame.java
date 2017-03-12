package UI.Index;

import javax.swing.JFrame;

public class indexFrame extends JFrame{
	public indexFrame(){
		setTitle("Movie Ticket System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
		add(new indexPanel());
	   
	}
	
}
