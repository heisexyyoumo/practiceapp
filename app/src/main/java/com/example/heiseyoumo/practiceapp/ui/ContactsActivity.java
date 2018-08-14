package com.example.heiseyoumo.practiceapp.ui;

/**
 * 从连接的手机或者模拟器中读取联系人的信息
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.adapter.ContactsAdapter;
import com.example.heiseyoumo.practiceapp.entity.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private List<Contacts> mList = new ArrayList<>();
    private ListView lv_contacts;
    private ContactsAdapter adapter;
    private Contacts contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initView();
    }

    private void initView() {
        lv_contacts = (ListView)findViewById(R.id.lv_contacts);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            readContacts();
        }

    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,null,null,null);
            if(cursor != null){
                while (cursor.moveToNext()){
                    //姓名
                    String name = cursor.getString(cursor.getColumnIndex
                            (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                    //电话号码
                    String number = cursor.getString(cursor.getColumnIndex
                            (ContactsContract.CommonDataKinds.Phone.NUMBER));

                    contacts = new Contacts();
                    contacts.setName(name);
                    contacts.setNumber(number);
                    mList.add(contacts);

                }
            }
            adapter = new ContactsAdapter(this,mList);
            lv_contacts.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
    }

    //进行运行时危险权限的处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    Toast.makeText(this,
                            "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
