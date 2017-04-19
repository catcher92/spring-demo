package com.catcher92.demo.springdemo.entity;

import com.catcher92.demo.springdemo.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by caoxuedong on 2017/2/10.
 */
public class User extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 4474301234051645199L;
    /**
    * id
    */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生时间
     */
    private String birthday;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
