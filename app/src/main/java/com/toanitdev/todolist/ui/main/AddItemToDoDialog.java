package com.toanitdev.todolist.ui.main;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.toanitdev.todolist.R;
import com.toanitdev.todolist.databinding.DialogMessageCenterBinding;

public class AddItemToDoDialog extends DialogFragment {

  public static AddItemToDoDialog newInstance(){
    return new AddItemToDoDialog();
  }


  @Override
  public Dialog onCreateDialog(final Bundle savedInstanceState) {

    // the content
    final LinearLayout root = new LinearLayout(getActivity());
    root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    // creating the fullscreen dialog
    final Dialog dialog = new Dialog(getActivity());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(root);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    return dialog;
  }
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    DialogMessageCenterBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_message_center,container,false);

    //this.getDialog().
    View view =  binding.getRoot();


    return view;
  }
}
