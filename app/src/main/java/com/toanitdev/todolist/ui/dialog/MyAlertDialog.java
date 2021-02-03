package com.toanitdev.todolist.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyAlertDialog extends DialogFragment {



  public static MyAlertDialog newInstance(String msg){
    MyAlertDialog myAlertDialog = new MyAlertDialog();
    Bundle bundle = new Bundle();
    bundle.putString("MSG", msg);
    myAlertDialog.setArguments(bundle);
    return myAlertDialog;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder aBuilder =  new AlertDialog.Builder(getContext());
    aBuilder.setMessage(getArguments().get("MSG").toString());
    aBuilder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {

      }
    });
    return aBuilder.create();
  }
}
