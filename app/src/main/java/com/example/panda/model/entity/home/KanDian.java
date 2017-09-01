package com.example.panda.model.entity.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by admin on 2017/8/31.
 */
@Entity
public class KanDian {
    @Id
    Long id;
    @Property
    String title;
    int data;
    String img;
    String url;
    @Generated(hash = 605204551)
    public KanDian(Long id, String title, int data, String img, String url) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.img = img;
        this.url = url;
    }
    @Generated(hash = 1994106233)
    public KanDian() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getData() {
        return this.data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
 
    
}
