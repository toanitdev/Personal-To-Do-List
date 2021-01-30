package com.toanitdev.todolist.data.local;

import com.toanitdev.todolist.data.models.ToDoItem;

import java.util.List;

public interface InternalStorageManager {

  public void saveToDoList(List<ToDoItem> toDoItemList);

  public List<ToDoItem> loadToDoList();
}
