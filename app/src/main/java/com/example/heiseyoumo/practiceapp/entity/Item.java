package com.example.heiseyoumo.practiceapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 水果图片的实体类
 */
public class Item{

    private int picId;
    private String picName;

    public Item(String picName, int picId) {
        this.picId = picId;
        this.picName = picName;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }


}
