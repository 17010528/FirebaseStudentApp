package com.example.firebasestudentapp;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Student implements Serializable {

    private String id;
    private String name;
    private int age;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // needed by firebase

    public Student(){

    }

    public Student(int age , String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
