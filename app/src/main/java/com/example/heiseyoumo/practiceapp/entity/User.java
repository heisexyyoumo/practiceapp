package com.example.heiseyoumo.practiceapp.entity;

/**
 * 用户管理 -- 登录/注册的实体类
 */

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {

    //个人描述
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
