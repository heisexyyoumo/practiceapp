package com.example.heiseyoumo.practiceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.MainActivity;
import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.utils.T;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_password;
    private Button btn_login;
    private CheckBox keep_info;
    private SharedPreferences pref;
    private boolean isChecked;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_password = (EditText)findViewById(R.id.edt_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        keep_info  =(CheckBox)findViewById(R.id.keep_info);
        pref = getSharedPreferences("data",0);
        isChecked = pref.getBoolean("keep_information",false);

        if(isChecked){
            String name = pref.getString("name","");
            String password = pref.getString("password","");
            edt_name.setText(name);
            edt_name.setSelection(name.length());
            edt_password.setText(password);
            edt_password.setSelection(name.length());
            keep_info.setChecked(true);

        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString();
                String password = edt_password.getText().toString();
                if(name.equals("admin") && password.equals("123456")){
                    //editor = pref.edit();
                    if (keep_info.isChecked()){
                        pref.edit().putString("name",name).commit();
                        pref.edit().putString("password",password).commit();
                        pref.edit().putBoolean("keep_information",true).commit();
                    }else {
                        pref.edit().clear().commit();
                    }
                    //editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "name or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
