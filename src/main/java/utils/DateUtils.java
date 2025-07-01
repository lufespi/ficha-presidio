package utils;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
  public static String getFormattedDate(Date date) {
    if (date == null) {
      return null;
    }
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
  }

  public static String getFormattedDateHour(Date date) {
    if (date == null) {
      return null;
    }
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(date);
  }

  public static Date parseToDate(String dateStr) throws ParseException {
    return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
  }
}
