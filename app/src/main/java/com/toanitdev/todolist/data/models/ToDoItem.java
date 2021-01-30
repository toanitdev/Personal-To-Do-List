package com.toanitdev.todolist.data.models;

import java.util.Date;

public class ToDoItem {


  String title;
  String content;
  Date timeToAlarm;


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
