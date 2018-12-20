// MyAIDLInterface.aidl
package com.example.consultants.aidlexample;

// Declare any non-default types here with import statements
import com.example.consultants.aidlexample.Person;

interface MyAIDLInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Person> getPersonList();
}
