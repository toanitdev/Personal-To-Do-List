package com.toanitdev.todolist.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

public class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

  T getBinding(){ }


}
