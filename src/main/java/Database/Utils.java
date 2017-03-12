package Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static class Convert {
        private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

        public static String dateToString(Date value) {
            return dateFormat.format(value);
        }
        public static Date stringToDate(String value) throws ParseException {
            return dateFormat.parse(value);
        }
        public static Date stringToDate(String value, Object ignoreException){
            try {
                return dateFormat.parse(value);
            } catch (ParseException ignored) {
                return null;
            }
        }
    }
}
