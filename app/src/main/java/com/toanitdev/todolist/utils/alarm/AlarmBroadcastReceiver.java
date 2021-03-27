package com.toanitdev.todolist.utils.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.toanitdev.todolist.R;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.ui.main.ringtone.RingtoneActivity;
import com.toanitdev.todolist.utils.Helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Random;

import static com.toanitdev.todolist.utils.alarm.AlarmService.CHANNEL_ID;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

  byte[] bytes;
  ToDoItem toDoItem;
  @Override
  public void onReceive(Context context, Intent intent) {
    bytes =  intent.getByteArrayExtra("data");

    ByteArrayInputStream bis  = new ByteArrayInputStream(bytes);
    ObjectInput in = null;
    toDoItem = null;

    try {
      in = new ObjectInputStream(bis);
      toDoItem = (ToDoItem) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    //toDoItem = (ToDoItem) intent.getBundleExtra("data").getSerializable("data");
    if(!Helper.isAppRunning(context,context.getPackageName())){
      startActivity(context);
    }else {

      RingtoneActivity.startMe(context.getApplicationContext(),toDoItem);
    }
    showNotificationOnService(context);


  }

  void startActivity(Context context) {
    Intent intent = new Intent(context, RingtoneActivity.class);
    if (toDoItem != null) {
      intent.putExtra("to_do_item", toDoItem);
    }
    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
        Intent.FLAG_ACTIVITY_NEW_TASK |
        Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
    context.startActivity(intent);
  }

  void showNotificationOnService(Context context) {
    Intent intentService = new Intent(context, AlarmService.class);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      context.startForegroundService(intentService);
    } else {
      context.startService(intentService);
    }
  }


  void showNotificationOnBroadcastReceiver(Context context) {
    Intent notificationIntent = new Intent(context, RingtoneActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);


    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
    builder.setContentTitle("Alarm Title");
    builder.setContentText("Content Alarm Ring Ring");
    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
    builder.setPriority(Notification.PRIORITY_MAX);
    builder.setContentIntent(pendingIntent);


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel serviceChannel = new NotificationChannel(
          CHANNEL_ID,
          "Alarm Service Channel",
          NotificationManager.IMPORTANCE_HIGH
      );

      NotificationManager manager = context.getSystemService(NotificationManager.class);
      manager.createNotificationChannel(serviceChannel);
      manager.notify(new Random().nextInt(Integer.MAX_VALUE), builder.build());
    } else {
      NotificationManagerCompat manager = NotificationManagerCompat.from(context);
      manager.notify(new Random().nextInt(Integer.MAX_VALUE), builder.build());
    }
  }


}