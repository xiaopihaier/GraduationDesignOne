package com.example.baby.graduationdesignone;

import java.io.Serializable;

/**
 * Created by baby on 2017/4/14.
 */

public class UserActivity implements Serializable {
    private String name;
    private int a;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getA() {
        return this.a;
    }

    public String getUser() {
        return name;
    }
}
