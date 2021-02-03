package com.toanitdev.todolist.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.toanitdev.todolist.R;
import com.toanitdev.todolist.data.models.ToDoItem;

import java.util.List;
import java.util.zip.Inflater;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

  List<ToDoItem> data;
  IOnClickListener listener;

  public ToDoAdapter(IOnClickListener listener) {
    this.listener = listener;
  }

  @NonNull
  @Override
  public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view  = layoutInflater.inflate(R.layout.item_to_do, parent,false);
    ToDoViewHolder holder = new ToDoViewHolder(view,listener);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
    holder.bind(data.get(position));
  }

  public void setData(List<ToDoItem> data){
    this.data = data;
    notifyDataSetChanged();
  }
  @Override
  public int getItemCount() {
    return data !=null ?  data.size() : 0;
  }


}
