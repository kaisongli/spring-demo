package com.lks.bean;

/**
 * Created by likaisong on 2019/3/3.
 */
public class User {
    private String name;
    private String sex;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
