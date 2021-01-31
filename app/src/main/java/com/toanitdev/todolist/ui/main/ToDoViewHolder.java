package com.toanitdev.todolist.ui.main;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.databinding.ItemToDoBinding;

public class ToDoViewHolder extends RecyclerView.ViewHolder {
  ItemToDoBinding itemToDoBinding;

  public void bind(ToDoItem item){
    itemToDoBinding.setItem(item);
  }
  public ToDoViewHolder(@NonNull View itemView, final IOnClickListener listener) {
    super(itemView);
    itemToDoBinding = DataBindingUtil.bind(itemView);
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.OnItemClick(v,getAdapterPosition());
      }
    });
  }
}
