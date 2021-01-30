package com.toanitdev.todolist.ui.main;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.toanitdev.todolist.data.DataManager;
import com.toanitdev.todolist.data.models.ToDoItem;

import java.util.List;

public class MainViewModel extends ViewModel {




  public void fetchToDoList(){

  }

  public void addToDoList(ToDoItem newItem){

  }






  private List<ToDoItem> getToDoListFromLocal(Context context){
    DataManager dataManager = new DataManager(context);
    return  dataManager.loadToDoList();
  }

  private void saveToDoListToLocal(Context context ,List<ToDoItem> toDoItemList){
    DataManager dataManager = new DataManager(context);
    dataManager.saveToDoList(toDoItemList);
  }

}
