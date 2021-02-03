package com.toanitdev.todolist.ui.main;

public interface MainNavigator {


  void goToDetail();

  void handleError(Throwable throwable);

  void openDialog(String msg);

  void openAddItemDialog();
}
