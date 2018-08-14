package com.example.heiseyoumo.practiceapp.entity;
/**
 *  FruitCardAdapter  对应的实体类
 */
public class FruitCard {
    private String name;
    private int imgId;

    public FruitCard(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
