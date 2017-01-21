package com.example.testingexample.presistence;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by ZhangYiGe on 2017/1/20 0020.
 */

public class Person extends RealmObject {
    private String name;
    private String sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
