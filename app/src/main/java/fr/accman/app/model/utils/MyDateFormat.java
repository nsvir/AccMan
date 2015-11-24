package fr.accman.app.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by nanosvir on 24 Nov 15.
 */
public class MyDateFormat extends SimpleDateFormat {

    public MyDateFormat() {
        super("dd/MM/yyyy-H:mm", Locale.ENGLISH);
    }

    public static String getDate(long time) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.SECOND, (int) time);
        return new MyDateFormat().format(calendar.getTime());
    }

    public static long getTime(String date) {
        try {
            return new MyDateFormat().parse(date).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }
}
