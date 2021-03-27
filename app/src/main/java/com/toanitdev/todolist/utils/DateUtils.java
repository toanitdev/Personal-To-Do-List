package com.toanitdev.todolist.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

  public static String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm";

  public static String toDateTimeString(Date date, String pattern) {
    if(date == null){
      return "";
    }

    String dateTime = "";

    SimpleDateFormat df = new SimpleDateFormat(pattern);

    dateTime = df.format(date);

    return dateTime;
  }
}
