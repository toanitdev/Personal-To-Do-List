package com.toanitdev.todolist.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.toanitdev.todolist.BR;
import com.toanitdev.todolist.R;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.databinding.ActivityMainBinding;
import com.toanitdev.todolist.ui.base.BaseActivity;
import com.toanitdev.todolist.ui.main.add_to_do.AddItemToDoDialog;
import com.toanitdev.todolist.utils.Helper;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

  MainViewModel mainViewModel;

  @Override
  public MainViewModel getViewModel() {
    return mainViewModel == null ? mainViewModel = new MainViewModel(this) : mainViewModel;
  }

  @Override
  public int getBindingVariable() {
    return BR.viewmodel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW,
        Manifest.permission.WAKE_LOCK}, 99);
    getViewModel().setNavigation(this);
    getDataBinding().setViewmodel(getViewModel());

    /**
     * Toan Tran  - 2021 02 03
     *  change to night mode - > must be set up on resource folder night/colors.xml
     *
     *  use code below to change night mode.
     *  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
     */
    //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    getViewModel().toDoItems.observe(this, new Observer<List<ToDoItem>>() {
      @Override
      public void onChanged(List<ToDoItem> toDoItems) {
        getViewModel().getToDoAdapter().getValue().setData(toDoItems);
        getViewModel().setIsEmpty(Helper.empltyList(toDoItems));

      }
    });
  }


  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 99) {
      // If request is cancelled, the result arrays are empty.
      if (grantResults.length > 0
          && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        // permission was granted, yay! Do the
        // contacts-related task you need to do.
      } else {

        // permission denied, boo! Disable the
        // functionality that depends on this permission.
        Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
      }
      return;
    }
  }

  @Override
  public void goToDetail() {

  }

  @Override
  public void handleError(Throwable throwable) {

  }

  @Override
  public void openDialog(String msg) {

    new AlertDialog.Builder(this).setMessage("Open Dialog : " + msg).show();
  }

  @Override
  public void openAddItemDialog() {

    //new AlertDialog.Builder(this).setMessage("Clicked : " + msg).show();
    //MyAlertDialog.newInstance("My dialog").show(getSupportFragmentManager(),"Dialog");
    AddItemToDoDialog.newInstance(this, getViewModel().getToDoItems().getValue()).show(getSupportFragmentManager(), "add_dialog");
  }

  @Override
  public void refreshDataList() {
    getViewModel().refeshToDoList();
    if (getViewModel().getToDoItems().getValue() != null && getViewModel().toDoItems.getValue().size() > 0)
      getDataBinding().rvToDo.smoothScrollToPosition(getViewModel().toDoItems.getValue().size() + 1);
  }
}