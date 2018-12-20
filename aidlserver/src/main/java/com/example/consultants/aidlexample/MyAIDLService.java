package com.example.consultants.aidlexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class MyAIDLService extends Service {

    List<Person> personList = new ArrayList<>();
    PersonDatabase personDatabase;

    public MyAIDLService() {
        personDatabase = new PersonDatabase(null);
        personList.addAll(personDatabase.getPeople());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private final MyAIDLInterface.Stub binder = new MyAIDLInterface.Stub() {

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return personDatabase.getPeople();
        }
    };
}
