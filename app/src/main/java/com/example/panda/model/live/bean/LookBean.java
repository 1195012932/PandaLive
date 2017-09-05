package com.example.panda.model.live.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by admin on 2017/8/29.
 */
@Entity
public class LookBean {
    @Id
    Long id;
    @Property
    String name;
    String title;
    String time;
    @Generated(hash = 1832221149)
    public LookBean(Long id, String name, String title, String time) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.time = time;
    }
    @Generated(hash = 1887585709)
    public LookBean() {
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
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    
}
