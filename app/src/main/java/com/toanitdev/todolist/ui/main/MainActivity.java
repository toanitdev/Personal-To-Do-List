package com.toanitdev.todolist.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.toanitdev.todolist.BR;
import com.toanitdev.todolist.R;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.databinding.ActivityMainBinding;
import com.toanitdev.todolist.ui.base.BaseActivity;
import com.toanitdev.todolist.ui.base.BaseViewModel;
import com.toanitdev.todolist.ui.dialog.MyAlertDialog;

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

    getViewModel().setNavigation(this);
    getDataBinding().setViewmodel(getViewModel());
  }

  @Override
  public void goToDetail() {

  }

  @Override
  public void handleError(Throwable throwable) {

  }

  @Override
  public void openDialog(String msg) {
    //new AlertDialog.Builder(this).setMessage("Clicked : " + msg).show();
    //MyAlertDialog.newInstance("My dialog").show(getSupportFragmentManager(),"Dialog");
    AddItemToDoDialog.newInstance().show(getSupportFragmentManager(),"add_dialog");
  }

  @Override
  public void openAddItemDialog() {
    new AlertDialog.Builder(this).setMessage("Open Dialog Add").show();
  }
}