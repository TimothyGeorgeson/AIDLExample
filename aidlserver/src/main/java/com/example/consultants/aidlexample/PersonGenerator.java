package com.example.consultants.aidlexample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator {

    static List<Person> personList = new ArrayList<>();
    static String[] names = {"Angela", "Josh", "Brittany", "Courtney", "Ross", "Allen", "Dennis", "Megan"};

    public static List<Person> generate(int count) {
        Random random = new Random();
        Person person;
        String name, age, gender;

        for (int i = 0; i < count; i++) {

            name = names[random.nextInt(names.length)];
            age = String.valueOf(random.nextInt(15) + 20);
            if (name.equals("Angela") || name.equals("Brittany") || name.equals("Courtney") || name.equals("Megan"))
                gender = "Female";
            else
                gender = "Male";

            person = new Person(name, age, gender);
            personList.add(person);
        }

        return personList;
    }
}
