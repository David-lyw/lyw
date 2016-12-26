package com.example.david.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.david.adapter.holdertestAdapter;
import com.example.david.lyw.AppActivity;
import com.example.david.lyw.R;
import com.lyw.adapter.HolderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 16/12/26.
 */
public class HolderActivity extends AppActivity {
    public RecyclerView datalist;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        datalist = (RecyclerView) findViewById(R.id.datalist);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        datalist.setLayoutManager(manager);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        holdertestAdapter adapter = new holdertestAdapter(this, list);
        adapter.setOnItemClickListener(new HolderAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String var2, int position) {
                Toast.makeText(getApplicationContext(),"dlfeifs",Toast.LENGTH_SHORT).show();
            }
        });
        datalist.setAdapter(adapter);
    }

}
