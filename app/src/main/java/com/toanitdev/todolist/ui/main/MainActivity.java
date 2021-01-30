package com.toanitdev.todolist.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.toanitdev.todolist.R;
import com.toanitdev.todolist.databinding.ActivityMainBinding;
import com.toanitdev.todolist.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}