package com.example.heiseyoumo.practiceapp.ui;

/**
 * RecycleView的测试调用
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.heiseyoumo.practiceapp.R;
import com.example.heiseyoumo.practiceapp.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.heiseyoumo.practiceapp.entity.Item;

public class RecycleViewActivity extends AppCompatActivity {

    private List<Item> fruitList = new ArrayList<>();
    private RecyclerView recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initFruit();
        recycle_view = (RecyclerView)findViewById(R.id.recycle_view);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3,StaggeredGridLayoutManager.VERTICAL);
        recycle_view.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(this,fruitList);
        recycle_view.setAdapter(adapter);
    }

    private void initFruit() {
        for(int i = 0; i < 2; i++){
            Item apple = new Item(getRandomLengthName("apple"),R.drawable.apple_pic);
            fruitList.add(apple);
            Item banana = new Item(getRandomLengthName("banana"),R.drawable.banana_pic);
            fruitList.add(banana);
            Item orange = new Item(getRandomLengthName("orange"),R.drawable.orange_pic);
            fruitList.add(orange);
            Item watermelon = new Item(getRandomLengthName("watermelon"),R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Item pear = new Item(getRandomLengthName("pear"),R.drawable.pear_pic);
            fruitList.add(pear);
            Item grape = new Item(getRandomLengthName("grape"),R.drawable.grape_pic);
            fruitList.add(grape);
            Item pineapple = new Item(getRandomLengthName("pineapple"),R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Item strawberry = new Item(getRandomLengthName("strawberry"),R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Item cherry = new Item(getRandomLengthName("cherry"),R.drawable.cherry_pic);
            fruitList.add(cherry);
            Item mango = new Item(getRandomLengthName("mango"),R.drawable.mango_pic);
            fruitList.add(mango);

        }

    }
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(10) + 1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
