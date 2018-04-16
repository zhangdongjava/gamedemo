package com.zzz.study.marshalling;

/**
 * Created by zha on 2018/4/14.
 */
public class OneMessage{

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public OneMessage setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public OneMessage setAge(int age) {
        this.age = age;
        return this;
    }
}
