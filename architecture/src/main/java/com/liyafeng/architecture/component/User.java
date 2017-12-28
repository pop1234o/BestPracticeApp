package com.liyafeng.architecture.component;

/**
 * Created by liyafeng on 2017/12/27.
 */

public class User {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long id;
    private String name;
}
