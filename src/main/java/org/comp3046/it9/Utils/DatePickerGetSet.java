package org.comp3046.it9.Utils;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePickerImpl;

import java.util.Calendar;
import java.util.Date;

public class DatePickerGetSet {
    public static Date getDate(JDatePickerImpl dp) {
        DateModel<?> m = dp.getModel();
        int year = m.getYear();
        int month = m.getMonth();
        int day = m.getDay();

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        return c.getTime();
    }

    public static void setDate(JDatePickerImpl dp, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DateModel<?> m = dp.getModel();

        m.setDate(year, month, day);
    }

    public static void setToday(JDatePickerImpl dp) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DateModel<?> m = dp.getModel();

        m.setDate(year, month, day);
    }
}
