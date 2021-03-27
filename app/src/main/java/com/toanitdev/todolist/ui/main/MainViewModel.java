package com.toanitdev.todolist.ui.main;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.toanitdev.todolist.data.DataManager;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends BaseViewModel<MainNavigator> implements IOnClickListener {


  MutableLiveData<List<ToDoItem>> toDoItems;
  MutableLiveData<ToDoAdapter> toDoAdapter;
  ObservableBoolean isEmpty;
  Context context;

  public MainViewModel(Context context) {
    this.toDoItems = new MutableLiveData<>();
    this.toDoAdapter = new MutableLiveData<>();
    this.isEmpty = new ObservableBoolean();
    this.context = context;
    initViewModel();
  }



  private void initViewModel() {
    fetchToDoList();

  }

  public ObservableBoolean getIsEmpty() {
    return isEmpty;
  }

  public void setIsEmpty(boolean isEmpty) {
    this.isEmpty.set(isEmpty);
  }

  public void fetchToDoList() {
    toDoItems.setValue(getToDoListFromLocal(context));
    ToDoAdapter adapter = new ToDoAdapter(this);
    adapter.setData(toDoItems.getValue());
    toDoAdapter.setValue(adapter);

  }

  public void refeshToDoList(){
    toDoAdapter.getValue().setData(getToDoListFromLocal(context));
    toDoItems.setValue(getToDoListFromLocal(context));
  }

  public void addToDoList(ToDoItem newItem) {

  }

  public LiveData<List<ToDoItem>> getToDoItems() {
    return toDoItems;
  }

  public void setToDoItems(MutableLiveData<List<ToDoItem>> toDoItems) {
    this.toDoItems = toDoItems;
  }

  public LiveData<ToDoAdapter> getToDoAdapter() {
    return toDoAdapter;
  }

  public void setToDoAdapter(MutableLiveData<ToDoAdapter> toDoAdapter) {
    this.toDoAdapter = toDoAdapter;
  }

  private List<ToDoItem> getToDoListFromLocal(Context context) {
    DataManager dataManager = new DataManager(context);
    return dataManager.loadToDoList();
    //return  getTemplateData();
  }


  public List<ToDoItem> getTemplateData(){
    List<ToDoItem> toDoItems = new ArrayList<>();

    toDoItems.add(new ToDoItem("t1","c1"));
    toDoItems.add(new ToDoItem("t2","c2"));
    toDoItems.add(new ToDoItem("t3","c3"));
    toDoItems.add(new ToDoItem("t4","c4"));
    toDoItems.add(new ToDoItem("t5","c5"));
    toDoItems.add(new ToDoItem("t6","c6"));
    toDoItems.add(new ToDoItem("t7","c7"));
    toDoItems.add(new ToDoItem("t8","c8"));
    toDoItems.add(new ToDoItem("t9","c9"));
    toDoItems.add(new ToDoItem("t10","c10"));
    toDoItems.add(new ToDoItem("t11","c11"));
    return toDoItems;
  }

  private void saveToDoListToLocal(Context context, List<ToDoItem> toDoItemList) {
    DataManager dataManager = new DataManager(context);
    dataManager.saveToDoList(toDoItemList);
  }

  @Override
  public void onItemClick(View itemView , int position) {
    getNavigator().openDialog(this.toDoItems.getValue().get(position).getContent());
  }

  @Override
  public void onDelClick(View view, int position) {
    List<ToDoItem> list =  getToDoListFromLocal(view.getContext());
    list.remove(position);

    saveToDoListToLocal(view.getContext(),list);
    toDoItems.setValue(list);
  }
}
