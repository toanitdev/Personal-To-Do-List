package com.toanitdev.todolist.ui.main.add_to_do;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.toanitdev.todolist.data.DataManager;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.ui.base.BaseViewModel;
import com.toanitdev.todolist.utils.alarm.AlarmBroadcastReceiver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class AddToDoViewModel extends BaseViewModel<AddToDoNavigator> {


  List<ToDoItem> toDoItemList;
  Context context;
  DataManager dataManager;

  MutableLiveData<String> title = new MutableLiveData();
  MutableLiveData<String> content = new MutableLiveData();
  ObservableBoolean isAlarm = new ObservableBoolean();
  ObservableBoolean isRepeat = new ObservableBoolean();

  public AddToDoViewModel(Context context, List<ToDoItem> list) {
    this.context = context;
    this.toDoItemList = list;
    if (toDoItemList == null) {
      toDoItemList = new ArrayList<>();
    }

    this.dataManager = new DataManager(this.context);
  }

  public MutableLiveData<String> getTitle() {
    return title;
  }

  public void setTitle(MutableLiveData<String> title) {
    this.title = title;
  }

  public MutableLiveData<String> getContent() {
    return content;
  }

  public void setContent(MutableLiveData<String> content) {
    this.content = content;
  }

  public ObservableBoolean getIsAlarm() {
    return isAlarm;
  }

  public void setIsAlarm(ObservableBoolean isAlarm) {
    this.isAlarm = isAlarm;
  }

  public ObservableBoolean getIsRepeat() {
    return isRepeat;
  }

  public void setIsRepeat(ObservableBoolean isRepeat) {
    this.isRepeat = isRepeat;
  }

  void addToDo(Calendar calendar, boolean isAlarm) {
    ToDoItem item = new ToDoItem();


    item.setTitle(title.getValue());
    item.setContent(content.getValue());
    item.setId(new Random().nextInt(Integer.MAX_VALUE));






    if (isAlarm) {
      if (calendar.before(Calendar.getInstance()))
        calendar.add(Calendar.DATE, 1);

      item.setTimeToAlarm(calendar.getTime());

      AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
      Intent intent = new Intent(context.getApplicationContext(), AlarmBroadcastReceiver.class);


      // END - Note 0001 chuyển obj sang byteArray
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = null;

      try {
        out = new ObjectOutputStream(bos);
        out.writeObject(item);
        out.flush();

        byte[] byteArray =  bos.toByteArray();
        intent.putExtra("data",byteArray);

      } catch (IOException e) {
        e.printStackTrace();
      }
      // END - Note 0001 chuyển obj sang byteArray

      PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), item.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

      if(isRepeat.get()){
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);

      }else {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
      }
    }

    toDoItemList.add(item);
    dataManager.saveToDoList(toDoItemList);

    getNavigator().addToDoSuccess();
  }

}
