package com.example.consultants.aidlexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PersonDatabase personDatabase;
    ListView lvPerson;
    ArrayAdapter<String> personAdapter;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personDatabase = new PersonDatabase(getApplicationContext());
        lvPerson = findViewById(R.id.lvPerson);

        personAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        lvPerson.setAdapter(personAdapter);

        //populate database if empty
        if (personDatabase.getPeople().size() == 0) {
            personList = PersonGenerator.generate(10);
            for (Person person : personList) {
                personDatabase.savePerson(person);
            }
        }

        //set people objects from database to the adapter for the listview
        for (Person person : personDatabase.getPeople()) {
            personAdapter.add(person.toString());
        }
    }
}
