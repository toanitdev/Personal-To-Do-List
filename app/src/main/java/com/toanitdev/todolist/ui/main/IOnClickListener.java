package com.toanitdev.todolist.ui.main;

import android.view.View;

public interface IOnClickListener {

  void onItemClick(View view , int position);

  void onDelClick(View view , int position);

}
