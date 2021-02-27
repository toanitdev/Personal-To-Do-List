package com.toanitdev.todolist.utils;

import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.toanitdev.todolist.ui.main.ToDoAdapter;

public class BindingUtils {

  @BindingAdapter({"app:toDoAdapter"})
  public static void setAdapter(RecyclerView recyclerView, ToDoAdapter toDoAdapter){
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext(),
        RecyclerView.VERTICAL,true);
    linearLayoutManager.setStackFromEnd(true);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(toDoAdapter);

  }
  @BindingAdapter("textChangedListener")
  public static void bindTextWatcher(EditText editText, TextWatcher textWatcher) {
    editText.addTextChangedListener(textWatcher);
  }


}
