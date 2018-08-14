package com.example.hp.firebasedbdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.Dao.Notification;
import com.example.hp.service.NotificationService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String editText;
    Button button;
    Spinner spinner;

    DatabaseReference databaseReference;
    List<Notification> notificationList;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("Notification");
        editText="Rasindu";
        button=(Button)findViewById(R.id.button_add);
        spinner=(Spinner)findViewById(R.id.spinner_type);
        notificationList=new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });

    }

    private void addNotification(){
        String notificationtxt=editText;
        String selectedType=spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(notificationtxt)){
            String id=databaseReference.push().getKey();
            Notification notification=new Notification(selectedType,notificationtxt);
            databaseReference.child(id).setValue(notification);
            Toast.makeText(this,"Successfully Sent",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please Enter Notification",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        startService(new Intent(this, NotificationService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
