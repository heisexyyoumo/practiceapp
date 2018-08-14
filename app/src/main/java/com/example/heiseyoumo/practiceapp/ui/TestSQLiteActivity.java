package com.example.heiseyoumo.practiceapp.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.utils.MyDatabaseHelper;

public class TestSQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    private MyDatabaseHelper myDatabaseHelper;
    private Button btn_create;
    private Button btn_add;
    private Button btn_update;
    private Button btn_delete;
    private Button btn_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sqlite);

        initView();

    }

    private void initView() {
        myDatabaseHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);
        btn_create = (Button)findViewById(R.id.btn_create);
        btn_create.setOnClickListener(this);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_query = (Button)findViewById(R.id.btn_query);
        btn_query.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                myDatabaseHelper.getWritableDatabase();
                break;
            case R.id.btn_add:
                SQLiteDatabase db_a = myDatabaseHelper.getWritableDatabase();
                ContentValues values_a = new ContentValues();
                values_a.put("name", "The Da Vinci Code");
                values_a.put("author", "Dan Brown");
                values_a.put("pages", 454);
                values_a.put("price", 16.96);
                db_a.insert("Book ", null, values_a);
                values_a.clear();
                values_a.put("name", "The Lost Symbol");
                values_a.put("author", "Dan Brown");
                values_a.put("pages", 510);
                values_a.put("price", 19.95);
                db_a.insert("Book ", null, values_a);
                break;
            case R.id.btn_update:
                SQLiteDatabase db_u = myDatabaseHelper.getWritableDatabase();
                ContentValues values_u = new ContentValues();
                values_u.put("price", 10.99);
                db_u.update("Book",values_u,"name = ?",new String[]
                        {"The Da Vinci Code"});
                break;
            case R.id.btn_delete:
                SQLiteDatabase db_d = myDatabaseHelper.getWritableDatabase();
                db_d.delete("Book","pages > ?",new String[]{"500"});
                break;
            case R.id.btn_query:
                SQLiteDatabase db_q = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = db_q.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;

        }
    }
}
