package com.toanitdev.todolist.utils;

public final class TextUtils {



  public static boolean isEmpty(String string){
    if(string == null || string.equals(""))
      return true;
    return false;
  }
}
