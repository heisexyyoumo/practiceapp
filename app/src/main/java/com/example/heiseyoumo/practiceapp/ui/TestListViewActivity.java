package com.example.heiseyoumo.practiceapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.heiseyoumo.practiceapp.R;

import java.util.ArrayList;
import java.util.List;

import adapter.FruitAdapter;
import com.example.heiseyoumo.practiceapp.entity.Item;

public class TestListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Item> fruitList = new ArrayList<>();
    private ListView fruit_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list_view);

        initFruit();
        FruitAdapter adapter = new FruitAdapter(this,fruitList);
        fruit_list = (ListView)findViewById(R.id.fruit_list);
        fruit_list.setAdapter(adapter);
        fruit_list.setOnItemClickListener(this);

    }

    private void initFruit() {
        for(int i = 0; i < 2; i++){
            Item apple = new Item("apple",R.drawable.apple_pic);
            fruitList.add(apple);
            Item banana = new Item("banana",R.drawable.banana_pic);
            fruitList.add(banana);
            Item orange = new Item("orange",R.drawable.orange_pic);
            fruitList.add(orange);
            Item watermelon = new Item("watermelon",R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Item pear = new Item("pear",R.drawable.pear_pic);
            fruitList.add(pear);
            Item grape = new Item("grape",R.drawable.grape_pic);
            fruitList.add(grape);
            Item pineapple = new Item("pineapple",R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Item strawberry = new Item("strawberry",R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Item cherry = new Item("cherry",R.drawable.cherry_pic);
            fruitList.add(cherry);
            Item mango = new Item("mango",R.drawable.mango_pic);
            fruitList.add(mango);

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = fruitList.get(position);
        Toast.makeText(this,item.getPicName(),Toast.LENGTH_SHORT).show();
    }
}
