package com.example.panda.view.activity.home;

/**
 * Created by yuerq on 2016/5/20.
 */
public class Entity {

    public int code;

    public String error;

    public Entity(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
