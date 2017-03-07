package com.example.dong.tracnghiemlaixe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.dong.tracnghiemlaixe.adapter.RecyclerMyAnswerAdapter;
import com.example.dong.tracnghiemlaixe.model.Items;

import java.util.ArrayList;

/**
 * Created by DONG on 07-Mar-17.
 */

public class MyAnswerActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerMyAnswerAdapter adapter;
    private GridLayoutManager lLayout;
    private Button btnSubmit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recycler_layout);

        addControl();
        addEvent();
    }

    private void addEvent() {

    }

    private void addControl() {
        Bundle bundle = getIntent().getExtras();
        ArrayList<Items> listItem = bundle.getParcelableArrayList("list");

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//mũi tên quay về

        btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setVisibility(View.INVISIBLE);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        lLayout=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(lLayout);
        adapter=new RecyclerMyAnswerAdapter(listItem,this);
        recyclerView.setAdapter(adapter);
    }
}
