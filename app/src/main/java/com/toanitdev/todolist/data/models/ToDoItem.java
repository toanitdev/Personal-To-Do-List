package com.toanitdev.todolist.data.models;

import java.io.Serializable;
import java.util.Date;

public class ToDoItem implements Serializable {

  int id;
  String title;
  String content;
  Date timeToAlarm;

  public ToDoItem() {
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
