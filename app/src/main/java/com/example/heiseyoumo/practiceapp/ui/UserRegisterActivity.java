package com.example.heiseyoumo.practiceapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.entity.User;
import com.example.heiseyoumo.practiceapp.utils.ActivityCollector;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.bean.Init;
import cn.bmob.v3.listener.SaveListener;

public class UserRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private EditText et_desc;
    private Button btn_register;

    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        ActivityCollector.addActivity(this);

        initView();
    }

    private void initView() {
        et_name = (EditText)findViewById(R.id.et_name);
        et_password = (EditText)findViewById(R.id.et_password);
        et_desc = (EditText)findViewById(R.id.et_desc);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();

                user = new User();
                user.setUsername(name);
                user.setPassword(password);
                user.setDesc(desc);

                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null){
                            Toast.makeText(UserRegisterActivity.this, "注册成功",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Intent intent = new Intent(this,UserLoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
