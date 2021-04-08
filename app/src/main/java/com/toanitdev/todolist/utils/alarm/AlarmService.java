  package com.toanitdev.todolist.utils.alarm;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.toanitdev.todolist.R;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.ui.main.ringtone.RingtoneActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Random;


public class AlarmService extends Service {
  private MediaPlayer mediaPlayer;
  private Vibrator vibrator;
  public static final String CHANNEL_ID = "MY_CHANNEL";

  @Override
  public void onCreate() {
    super.onCreate();
    // tạo media player để chơi nhạc bao thức
    mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
    mediaPlayer.setLooping(true);

    // tao rung nhớ xin quyền
    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

  }

  void showNotificationOnBroadcastReceiver(Context context) {
    Intent notificationIntent = new Intent(context, RingtoneActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);


    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
    builder.setContentTitle("Alarm Title");
    builder.setContentText("Content Alarm Ring Ring");
    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
    builder.setPriority(Notification.PRIORITY_MAX);
    //builder.setContentIntent(pendingIntent);

    startForeground(123, builder.getNotification());

    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
    }*/
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {


    mediaPlayer.start();
    long[] pattern = {0, 100, 1000};
    vibrator.vibrate(pattern, 0);
    //showNotification();

    //startActivity();
    ToDoItem toDoItem = (ToDoItem) intent.getSerializableExtra("to_do_item");

    showNotification(toDoItem);
    return START_STICKY;
  }

  void startActivity() {
    Intent intent = new Intent(this,RingtoneActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
        Intent.FLAG_ACTIVITY_NEW_TASK |
        Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
    this.startActivity(intent);
  }

  void showNotification(ToDoItem toDoItem) {


    Intent notificationIntent = new Intent(this, RingtoneActivity.class);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream out = null;
    toDoItem.setContent("we qwe qwe qwe qwe qwe wqe qwe qwe qwe wqe ");
    /*try {
      out = new ObjectOutputStream(bos);
      out.writeObject(toDoItem);
      out.flush();

      byte[] byteArray =  bos.toByteArray();
      notificationIntent.putExtra("data",byteArray);

    } catch (IOException e) {
      e.printStackTrace();
    }*/
    //notificationIntent.putExtra("to_do_item",toDoItem);

    notificationIntent.putExtra("to_do_item",toDoItem.getId());
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
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

      NotificationManager manager = getSystemService(NotificationManager.class);
      manager.createNotificationChannel(serviceChannel);
    }

    startForeground(1, builder.build());
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    mediaPlayer.stop();
    vibrator.cancel();
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
