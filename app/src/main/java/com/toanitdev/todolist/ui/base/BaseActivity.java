package com.toanitdev.todolist.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.toanitdev.todolist.R;

public abstract class BaseActivity<T extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

  T dataBinding;
  VM viewModel;

  public T getDataBinding() {
    return dataBinding;
  }

  public abstract VM getViewModel();

  public abstract int getBindingVariable();

  public abstract int getLayoutId();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    performDataBinding();
  }


  public void performDataBinding() {
    dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    this.viewModel = viewModel == null ? getViewModel() : viewModel;
    dataBinding.setVariable(getBindingVariable(), viewModel);
    //dataBinding.executePendingBindings();
  }
}
