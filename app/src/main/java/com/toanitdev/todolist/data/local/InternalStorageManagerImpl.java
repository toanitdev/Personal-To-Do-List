package com.toanitdev.todolist.data.local;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.utils.DataUtils;

import java.util.List;

public class InternalStorageManagerImpl implements InternalStorageManager {

  DataUtils dataUtils;

  public InternalStorageManagerImpl(Context context) {
    this.dataUtils = new DataUtils(context);
  }

  @Override
  public void saveToDoList(List<ToDoItem> toDoItemList) {
    dataUtils.writeObject("ToDoList", toDoItemList);
  }

  @Override
  public List<ToDoItem> loadToDoList() {
    return new Gson().fromJson(dataUtils.readObject("ToDoList"), new TypeToken<List<ToDoItem>>() {
    }.getType());
  }
}
