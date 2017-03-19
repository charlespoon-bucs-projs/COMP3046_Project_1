package org.comp3046.it9.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Convert {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(("dd/MM/yyyy"), Locale.US);

    public static String dateToString(Date value) {
        return dateFormat.format(value);
    }

    private static Date stringToDate(String value) throws ParseException {
        return dateFormat.parse(value);
    }

    public static Date stringToDate(String value,
                                    @SuppressWarnings({"SameParameterValue", "UnusedParameters"}) Object ignoreException) {
        try {
            return stringToDate(value);
        } catch (ParseException ignored) {
            return null;
        }
    }
}
