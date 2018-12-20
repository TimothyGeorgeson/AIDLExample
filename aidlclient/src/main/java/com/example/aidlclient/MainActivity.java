package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    
    RecyclerView rvPerson;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    MyAIDLInterface myAIDLInterface;
    List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPerson = findViewById(R.id.rvPerson);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            myAIDLInterface = MyAIDLInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    public void onGetPeople(View view) throws RemoteException {
        //get person list, and show data in recyclerview
        personList = myAIDLInterface.getPersonList();
        recyclerViewAdapter = new RecyclerViewAdapter(personList);
        rvPerson.setAdapter(recyclerViewAdapter);
        rvPerson.setLayoutManager(layoutManager);
    }

    public void onBindService(View view) {
        //bind to service
        ComponentName aidlComponent = new ComponentName("com.example.consultants.aidlexample",
                "com.example.consultants.aidlexample.MyAIDLService");
        Intent remoteIntent = new Intent();
        remoteIntent.setComponent(aidlComponent);
        bindService(remoteIntent, serviceConnection, BIND_AUTO_CREATE);
    }
}
