package com.example.heiseyoumo.practiceapp.ui;

/**
 * 用户登录注册界面
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.entity.User;
import com.example.heiseyoumo.practiceapp.utils.ActivityCollector;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;


    
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ActivityCollector.addActivity(this);

        initView();
    }

    private void initView() {

        et_name = (EditText)findViewById(R.id.et_name);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);


        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){
                    user = new User();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e == null){
                                Intent i = new Intent(UserLoginActivity.this,
                                        ToolbarActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }else {
                    Toast.makeText(this, "输入框不能为空", 
                            Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.btn_register:
                Intent intent = new Intent(this,UserRegisterActivity.class);
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
