package com.toanitdev.todolist.ui.main.add_to_do;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.toanitdev.todolist.data.DataManager;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddToDoViewModel extends BaseViewModel<AddToDoNavigator>{


  List<ToDoItem> toDoItemList;
  Context context;
  DataManager dataManager;

  MutableLiveData<String> title = new MutableLiveData() ;
  MutableLiveData<String> content = new MutableLiveData() ;
  ObservableBoolean isAlarm = new ObservableBoolean() ;

  public AddToDoViewModel(Context context, List<ToDoItem> list) {
    this.context = context;
    this.toDoItemList = list;
    if(toDoItemList == null){
      toDoItemList = new ArrayList<>();
    }

    this.dataManager =  new DataManager(this.context);
  }

  public MutableLiveData<String> getTitle() {
    return title;
  }

  public void setTitle(MutableLiveData<String> title) {
    this.title = title;
  }

  public MutableLiveData<String> getContent() {
    return content;
  }

  public void setContent(MutableLiveData<String> content) {
    this.content = content;
  }

  public ObservableBoolean getIsAlarm() {
    return isAlarm;
  }

  public void setIsAlarm(ObservableBoolean isAlarm) {
    this.isAlarm = isAlarm;
  }

  void addToDo(){
    ToDoItem item = new ToDoItem();


    item.setTitle(title.getValue());
    item.setContent(content.getValue());
    toDoItemList.add(item);

    dataManager.saveToDoList(toDoItemList);
    getNavigator().addToDoSuccess();
  }

}
