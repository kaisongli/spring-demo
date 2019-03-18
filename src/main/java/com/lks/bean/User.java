package com.lks.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by likaisong on 2019/3/3.
 */
public class User {
    @Autowired
    private String name;
    private String sex;
    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name=").append(name);
        sb.append(",sex=").append(sex);
        sb.append(",age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
