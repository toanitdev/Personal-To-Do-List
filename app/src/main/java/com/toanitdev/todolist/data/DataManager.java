package com.toanitdev.todolist.data;

import android.content.Context;

import com.toanitdev.todolist.data.local.InternalStorageManager;
import com.toanitdev.todolist.data.local.InternalStorageManagerImpl;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.utils.DataUtils;

import java.util.List;

public class DataManager {

  Context context;
  InternalStorageManager internalStorageManager;
  public DataManager(Context context) {
    this.context = context;
    this.internalStorageManager=  new  InternalStorageManagerImpl(context);
  }

  public void saveToDoList(List<ToDoItem> toDoItemList){
    internalStorageManager.saveToDoList(toDoItemList);
  }

  public List<ToDoItem> loadToDoList(){
    return internalStorageManager.loadToDoList();
  }
}
