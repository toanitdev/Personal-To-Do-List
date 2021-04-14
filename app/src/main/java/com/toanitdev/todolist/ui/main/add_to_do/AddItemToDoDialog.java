package com.toanitdev.todolist.ui.main.add_to_do;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.toanitdev.todolist.R;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.databinding.DialogAddToDoBinding;
import com.toanitdev.todolist.ui.base.BaseBottomSheetDiagloFragment;
import com.toanitdev.todolist.ui.base.BaseNavigator;
import com.toanitdev.todolist.ui.main.MainNavigator;
import com.toanitdev.todolist.ui.main.MainViewModel;

import java.util.Calendar;
import java.util.List;

public class AddItemToDoDialog extends BaseBottomSheetDiagloFragment implements AddToDoNavigator,IAddToDoListener {

  static BaseNavigator navi;

  AddToDoViewModel  addToDoViewModel;
  static List<ToDoItem> data;
  Calendar calendar;
  DialogAddToDoBinding binding;
  boolean isAlarm;
  public static AddItemToDoDialog newInstance(MainNavigator navigator, List<ToDoItem> list){
    navi =  navigator;
    data =  list;
    return new AddItemToDoDialog();
  }


/*  @Override
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
  }*/

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    return super.onCreateDialog(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_to_do,container,false);

    //this.getDialog().
    binding.setAddToDoViewModel(addToDoViewModel == null ? addToDoViewModel = new AddToDoViewModel(getContext(),data) : addToDoViewModel);
    binding.setIOnClick(this);
    addToDoViewModel.setNavigation(this);
    View view =  binding.getRoot();


    return view;
  }

  @Override
  public void addToDoSuccess() {
    if(navi instanceof  MainNavigator){
      navi = (MainNavigator)navi;
      ((MainNavigator) navi).refreshDataList();
    }
    dismiss();
  }

  @Override
  public void addToDoFailed() {

  }

  @Override
  public void onClickAdd() {
    String[] dayToAlarm  = {"","","","","","",""};
    if(binding.ckMon.isChecked()){
      dayToAlarm[0] = ToDoItem.MONDAY;
    }
    if(binding.ckTue.isChecked()){
      dayToAlarm[1] = ToDoItem.TUESDAY;
    }
    if(binding.ckWed.isChecked()){
      dayToAlarm[2] = ToDoItem.WEDNESDAY;
    }
    if(binding.ckThu.isChecked()){
      dayToAlarm[3] = ToDoItem.THURSDAY;
    }
    if(binding.ckFri.isChecked()){
      dayToAlarm[4] = ToDoItem.FRIDAY;
    }
    if(binding.ckSat.isChecked()){
      dayToAlarm[5] = ToDoItem.SATURDAY;
    }
    if(binding.ckSun.isChecked()){
      dayToAlarm[6] = ToDoItem.SUNDAY;
    }

    addToDoViewModel.addToDo(calendar, isAlarm, dayToAlarm);
  }

  @Override
  public void onCheckedChanged(boolean isCheck) {

  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  public void changeTime() {
    new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        binding.tvTime.setText(String.format("%02d:%02d",hourOfDay,minute));
        calendar =  Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        isAlarm =true;
      }
    }, Calendar.getInstance().getTime().getHours(),Calendar.getInstance().getTime().getMinutes(),true).show();
  }
}
