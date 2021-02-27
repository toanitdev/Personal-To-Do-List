package com.toanitdev.todolist.ui.base;

import androidx.lifecycle.ViewModel;

public class BaseViewModel<T extends BaseNavigator> extends ViewModel {

  T navigator;


  public T getNavigator(){
    return navigator;
  }

  public void setNavigation(T navigation){
    this.navigator = navigation;
  }


}
