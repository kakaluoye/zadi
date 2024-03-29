package com.lin.android50;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.lin.android50.R.id.recyclerviewactivity_recycleview;

public class RecycleViewActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    //定义一个对象。   RecyclerViewItem theobject;
    List<RecyclerViewItem> data = new ArrayList<>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aaa","onCreate");
        setContentView(R.layout.activity_recycle_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewactivity_recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecycleViewActivity.this);
        //得到RecyclerView需要的LinearLayoutManager，设置控件类型放方芳芳
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(linearLayoutManager);//设置一个管理者。可以是LinearLayoutManage，或者GridLayoutManege。

        //添加分割线。
        recyclerView.addItemDecoration(new MyItemDecoration());//需要传入一个ItemDecoration。新建子类。
        //数据
        initDate();
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getApplicationContext(), data);
        //没有监听，需要自己定义，不能在recyclerview里定义吧，整好定义的自定义适配器，在自定义适配器里定义监听接口。
        recyclerView.setAdapter(adapter);
        Log.i("aaa","setAdapter");
        //这里不同的是，因为没有监听，自己在adapter里写的接口监听，对每一项item添加监听要adapter调用自己内部的监听才行。
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, "你点击了第" + position + "个", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void initDate() {
        for (int i = 0; i < 20; i++) {
            Log.i("aaa","initDate");
            RecyclerViewItem theObject = new RecyclerViewItem(R.drawable.ad, "message" + i);
            data.add(theObject);
        }
    }
}
