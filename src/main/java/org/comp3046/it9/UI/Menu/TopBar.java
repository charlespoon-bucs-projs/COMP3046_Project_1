package org.comp3046.it9.UI.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TopBar {
    private String id, FullName;
    JSeparator s;
    private JLabel time, loginer;

    public TopBar() {
    }

    public JPanel topbarLayout(JPanel jPanel, String id, String FullName) {
        this.id = id;
        this.FullName = FullName;
        time = new JLabel("Time:	");

        time.setBounds(10, 10, 225, 15);
        time.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
        jPanel.add(time);
        return jPanel;
    }

    public void clock() {
        Thread clock = new Thread() {
            @SuppressWarnings("InfiniteLoopStatement")
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
