package com.example.heiseyoumo.practiceapp.ui;

/**
 * Material Design 的实战练习
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.adapter.FruitCardAdapter;
import com.example.heiseyoumo.practiceapp.entity.FruitCard;
import com.example.heiseyoumo.practiceapp.entity.User;
import com.example.heiseyoumo.practiceapp.utils.ActivityCollector;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ToolbarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private CircleImageView civ_image;
    private TextView tv_desc;
    private TextView tv_name;
    private FloatingActionButton fab;

    private RecyclerView mRecyclerView;
    private List<FruitCard> mList = new ArrayList<>();
    //适配器
    private FruitCardAdapter adapter;
    private SwipeRefreshLayout sr_refresh;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ActivityCollector.addActivity(this);
        user = User.getCurrentUser(User.class);
        if (user != null){
            initView(user);
        }else {
            Intent intent = new Intent(this,UserLoginActivity.class);
            startActivity(intent);
        }


    }

    private void initView(User user) {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        //将Toolbar的实例传入，保持外观和功能与ActionBar一致
        setSupportActionBar(toolbar);
        //滑动菜单
        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //Material Design的简易实现的滑动菜单
        nav_view = (NavigationView)findViewById(R.id.nav_view);
        //NavigationView的头部headerLayout实现
        View headerLayout = nav_view.inflateHeaderView(R.layout.nav_header);
        civ_image = (CircleImageView)headerLayout.findViewById(R.id.civ_image);

        tv_desc = (TextView)headerLayout.findViewById(R.id.tv_desc);
        tv_name = (TextView)headerLayout.findViewById(R.id.tv_name);

        tv_name.setText(user.getUsername());
        tv_desc.setText(user.getDesc());

        //悬浮按钮
        fab = (FloatingActionButton)findViewById(R.id.fab);
        //菜单默认选中项
        nav_view.setCheckedItem(R.id.nav_call);

        initFruitCard();
        mRecyclerView = (RecyclerView)findViewById(R.id.mRecyclerView);

        //每一行有两列数据
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new FruitCardAdapter(this,mList);
        mRecyclerView.setAdapter(adapter);

        sr_refresh = (SwipeRefreshLayout)findViewById(R.id.sr_refresh);
        //设置下拉刷新进度条的颜色
        sr_refresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,
                R.color.cardview_shadow_start_color);
        sr_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        //headerLayout的监听事件
        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarActivity.this, "you click headerLayout",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //悬浮按钮的监听事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ToolbarActivity.this,
                                        "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });


        //菜单点击事件
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer_layout.closeDrawers();
                return true;
            }
        });
        //主页面设置DrawerLayout的图标
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            //图片需要缩放的，根据缩放的等级放在不同mitmap下
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
    }

    //刷新
    private void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruitCard();
                        adapter.notifyDataSetChanged();
                        sr_refresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    //初始化mList的数据
    private void initFruitCard() {
        FruitCard[] fruitCards = {new FruitCard("Apple", R.drawable.apple),
                new FruitCard("Banana", R.drawable.banana),
                new FruitCard("Orange", R.drawable.orange),
                new FruitCard("Watermelon", R.drawable.watermelon),
                new FruitCard("Pear", R.drawable.pear),
                new FruitCard("Grape", R.drawable.grape),
                new FruitCard("Pineapple", R.drawable.pineapple),
                new FruitCard("Strawberry", R.drawable.strawberry),
                new FruitCard("Cherry", R.drawable.cherry),
                new FruitCard("Mango", R.drawable.mango)};
        mList.clear();
        for (int i = 0; i < 50; i++){
            Random random = new Random();
            int index = random.nextInt(fruitCards.length);
            mList.add(fruitCards[index]);
        }

    }

    //初始化NavigationView的Menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //Menu的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this, "You click Backup",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You click Delete",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "You click Setting",
                        Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            ActivityCollector.finishAllActivity();
        }
        return super.onKeyDown(keyCode, event);
    }
}
