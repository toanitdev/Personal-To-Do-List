package com.toanitdev.todolist;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import cat.ereza.customactivityoncrash.config.CaocConfig;

import static com.toanitdev.todolist.utils.alarm.AlarmService.CHANNEL_ID;

public class App extends Application {


  @Override
  public void onCreate() {

    super.onCreate();
    //CaocConfig.Builder.create().enabled(false).apply();
    setupBugLog();
    //createNotificationChannnel();


  }

  private void setupBugLog() {
      CaocConfig.Builder.create().enabled(BuildConfig.DEBUG).apply();

  }

  private void createNotificationChannnel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel serviceChannel = new NotificationChannel(
          CHANNEL_ID,
          "Alarm Service Channel",
          NotificationManager.IMPORTANCE_HIGH
      );

      NotificationManager manager = getSystemService(NotificationManager.class);
      manager.createNotificationChannel(serviceChannel);
    }
  }
}
