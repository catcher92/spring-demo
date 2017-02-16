package com.catcher92.demo.springdemo.Entity;

import java.io.Serializable;

/**
 * Created by caoxuedong on 2017/2/10.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 709802288106398607L;

    private int id;

    private short age;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
