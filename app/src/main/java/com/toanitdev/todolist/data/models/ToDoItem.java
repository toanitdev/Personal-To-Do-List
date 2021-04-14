package com.toanitdev.todolist.data.models;

import java.io.Serializable;
import java.util.Date;

public class ToDoItem implements Serializable {

  public final static String MONDAY = "1";
  public final static String TUESDAY = "2";
  public final static String WEDNESDAY = "3";
  public final static String THURSDAY = "4";
  public final static String FRIDAY = "5";
  public final static String SATURDAY = "6";
  public final static String SUNDAY = "0";

  int id;
  String title;
  String content;
  Date timeToAlarm;
  String[] day;
  boolean isRepeat;

  public ToDoItem() {
  }


  public boolean isRepeat() {
    return isRepeat;
  }

  public void setRepeat(boolean repeat) {
    isRepeat = repeat;
  }

  public String[] getDay() {
    return day;
  }

  public void setDay(String[] day) {
    this.day = day;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ToDoItem(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getTimeToAlarm() {
    return timeToAlarm;
  }

  public void setTimeToAlarm(Date timeToAlarm) {
    this.timeToAlarm = timeToAlarm;
  }

}
