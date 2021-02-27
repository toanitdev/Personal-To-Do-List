package com.toanitdev.todolist.ui.main;

import com.toanitdev.todolist.ui.base.BaseNavigator;

public interface MainNavigator extends BaseNavigator {


  void goToDetail();

  void handleError(Throwable throwable);

  void openDialog(String msg);

  void openAddItemDialog();

  void refreshDataList();
}
