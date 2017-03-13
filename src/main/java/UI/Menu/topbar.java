package Menu;

import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class topbar {
	private JPanel topbar;
	private JLabel time,loginer;
	public static String id,FullName;

	JSeparator s;
	public topbar(){}

	public JPanel topbarLayout(JPanel p,String id,String FullName){
		topbar=p;
		this.id = id;
		this.FullName = FullName;
		time =new JLabel("Time:	");
		
		time.setBounds(10, 10, 225, 15);
		time.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		topbar.add(time);
	return p;	
	}
	
	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					while (true) {
						Calendar cal = new GregorianCalendar();
						int year = cal.get(Calendar.YEAR);
						int month = cal.get(Calendar.MONTH) + 1;
						int day = cal.get(Calendar.DAY_OF_MONTH);

						int hour = cal.get(Calendar.HOUR);
						int minute = cal.get(Calendar.MINUTE);
						int second = cal.get(Calendar.SECOND);
						time.setText(
								"Time: " + year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second);

						sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		clock.start();
	}
	
}
