package com.toanitdev.todolist.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DataUtils {

  Context context;


  public DataUtils(Context context) {
    this.context = context;
  }

  public void writeObject(String name, Object object) {
    try {
      FileOutputStream fos = context.openFileOutput(name, Context.MODE_PRIVATE);
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
      outputStreamWriter.append(new Gson().toJson(object));
      outputStreamWriter.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String readObject(String name) {
    try {
      FileInputStream fis = context.openFileInput(name);
      InputStreamReader inputStreamReader = new InputStreamReader(fis);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String dataRow = "";
      String bufferRow = "";

      while((bufferRow = bufferedReader.readLine()) != null){
        dataRow += bufferRow;
      }
      bufferedReader.close();
      return dataRow;

    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }

  }

}
