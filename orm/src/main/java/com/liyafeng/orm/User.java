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

    @Id
    private Long id;

    @NotNull
    private String name;

    private java.util.Date date;

    @Generated(hash = 1208562441)
    public User(Long id, @NotNull String name, java.util.Date date) {
        this.id = id;
        this.name = name;
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
}
