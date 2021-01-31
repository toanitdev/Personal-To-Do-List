package com.toanitdev.todolist.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.toanitdev.todolist.ui.main.ToDoAdapter;

public class BindingUtils {

  @BindingAdapter({"app:toDoAdapter"})
  public static void setAdapter(RecyclerView recyclerView, ToDoAdapter toDoAdapter){
    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
        RecyclerView.VERTICAL,false));
    recyclerView.setAdapter(toDoAdapter);

  }
}
