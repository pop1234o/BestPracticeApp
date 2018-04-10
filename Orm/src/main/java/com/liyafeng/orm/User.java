package com.liyafeng.orm;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import java.util.Date;

/**
 * Created by lenovo on 2017/12/18.
 */

@Entity
public class User {

    @Id(autoincrement = true) //这里的自增主键 必须是Long类型
    private Long id;

    @NotNull
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;
    private java.util.Date date;

    @Generated(hash = 172853687)
    public User(Long id, @NotNull String name, int number, java.util.Date date) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.date = date;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", date=" + date +
                '}';
    }
}
