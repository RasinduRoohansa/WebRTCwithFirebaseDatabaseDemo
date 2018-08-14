package com.example.hp.service;

import android.app.IntentService;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.hp.firebasedbdemo.CallActivity;
import com.example.hp.Dao.Notification;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationService extends IntentService {

    DatabaseReference databaseReference;
    public NotificationService() {
        super("Demo");
        databaseReference= FirebaseDatabase.getInstance().getReference("Notification");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Gets data from the incoming Intent
        String dataString = intent.getDataString();
        final Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("Child Added");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println("Child Changed");
                Intent myIntent = new Intent(getBaseContext(), CallActivity.class);
                myIntent.setAction("android.intent.action.MAIN");
                myIntent.addCategory("android.intent.category.LAUNCHER");
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getApplication().startActivity(myIntent);
                /**
                 * Setting up ring in tone when incoming call
                 */
                r.play();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("Child Removed");
                Intent myIntent = new Intent(getBaseContext(), CallActivity.class);
                myIntent.setAction("android.intent.action.MAIN");
                myIntent.addCategory("android.intent.category.LAUNCHER");
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                getApplication().startActivity(myIntent);
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                /**
                 * Setting up ring in tone when incoming call
                 */
                r.play();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                System.out.println("Child Moved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Child Canceled");
            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                System.out.println("Data Received");
//                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    Notification notification=snapshot.getValue(Notification.class);
////                    notificationList.add(notification);
//                }
//
//
////                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
////                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
////                r.play();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("Rasindu ROohansa");
        Toast.makeText(this,"Start Notification Monitor",Toast.LENGTH_LONG).show();
    }
}
