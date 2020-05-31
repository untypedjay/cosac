package swe4.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
  public static String formatTime(Date date) {
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    return timeFormat.format(date);
  }
}
