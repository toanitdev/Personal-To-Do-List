package com.toanitdev.todolist.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.toanitdev.todolist.data.models.ToDoItem;

import java.util.List;

public class Helper {

  public static boolean isAppRunning(final Context context, final String packageName) {
    final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
    if (procInfos != null)
    {
      for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
        if (processInfo.processName.equals(packageName)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean empltyList(List<ToDoItem> list){
    if(list == null || list.size() <= 0){
      return true;
    }
    return false;
  }

}