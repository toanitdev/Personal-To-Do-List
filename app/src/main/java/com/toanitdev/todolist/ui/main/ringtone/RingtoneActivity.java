package com.toanitdev.todolist.ui.main.ringtone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.toanitdev.todolist.R;
import com.toanitdev.todolist.data.models.ToDoItem;
import com.toanitdev.todolist.databinding.ActivityRingtoneBinding;
import com.toanitdev.todolist.utils.alarm.AlarmService;

import java.text.SimpleDateFormat;

public class RingtoneActivity extends AppCompatActivity {



  public static void startMe(Context context){
    Intent intent = new Intent(context,RingtoneActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
  public static void startMe(Context context,ToDoItem toDoItem){
    Intent intent = new Intent(context,RingtoneActivity.class);
    intent.putExtra("to_do_item", toDoItem);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActivityRingtoneBinding  binding = DataBindingUtil.setContentView(this,R.layout.activity_ringtone);

    ToDoItem toDoItem = (ToDoItem) getIntent().getSerializableExtra("to_do_item");

    SimpleDateFormat df =  new SimpleDateFormat("HH:mm");

    binding.tvTime.setText(""+df.format(toDoItem.getTimeToAlarm()));
    binding.tvTitle.setText(""+toDoItem.getTitle());
    binding.tvContent.setText(""+toDoItem.getContent());
    binding.btnStopAlarm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Tạo một intent từ service đang chạy và tắt nó
        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
        getApplicationContext().stopService(intentService);
        finish();
      }
    });
    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {

      setShowWhenLocked(true);
      setTurnScreenOn(true);
      KeyguardManager keyguardManager = (KeyguardManager) getSystemService(this.KEYGUARD_SERVICE);
      keyguardManager.requestDismissKeyguard(this, null);
    }
    else {

      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
          WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
          WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
          WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }*/

  }
}